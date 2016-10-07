package validate.jdbc;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import validate.mapper.IBANEntry;
import validate.mapper.IBANLengthMapper;
import validate.mapper.SwiftMapper;

public class IBANJDBCTemplate implements IBANDAO {
	
	private DataSource dataSource;
	private JdbcTemplate jdbc;
	
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbc = new JdbcTemplate(this.dataSource);
	}

	@Override
	public void loadFromDB(Map<String, String> swiftMap, 
							Map<String, Integer> lengthMap) {
		String swift = "select swift_code, bank_name from swift";
		String length = "select country_code, length from length_by_country";
		
		List<IBANEntry<String, String>> swifts = jdbc.query(swift, 
				new SwiftMapper());
		for(IBANEntry<String, String> swiftEntry : swifts){
			swiftMap.put(swiftEntry.getKey(), swiftEntry.getValue());
		}
		
		List<IBANEntry<String, Integer>> listOfLength = 
				jdbc.query(length, new IBANLengthMapper());
		for(IBANEntry<String, Integer> lengthEntry : listOfLength){
			lengthMap.put(lengthEntry.getKey(), lengthEntry.getValue());
		}
	}

	@Override
	public boolean updateFromDB(Map<String, String> swiftMap, 
										String timestamp) {
		boolean isUpdated = false;
		String swift = "SELECT swift_code, bank_name FROM"
				+ " swift WHERE update_time > ?";
		
		List<IBANEntry<String, String>> swifts = 
				jdbc.query(swift, new Object[]{timestamp}, 
						new SwiftMapper());
		
		if(swifts.size() != 0){
			isUpdated = true;
			for(IBANEntry<String, String> swiftEntry : swifts){
				swiftMap.put(swiftEntry.getKey(), swiftEntry.getValue());
			}
		}
		return isUpdated;
	}

}
