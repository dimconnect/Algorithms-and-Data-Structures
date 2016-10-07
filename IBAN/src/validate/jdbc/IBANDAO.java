package validate.jdbc;

import java.util.Map;
import javax.sql.DataSource;

public interface IBANDAO {
	
	void setDataSource(DataSource dataSource);
	
	void loadFromDB(Map<String, String> swiftMap, 
					Map<String, Integer> lengthMap);
	
	boolean updateFromDB(Map<String, String> swiftMap, String timestamp);
}
