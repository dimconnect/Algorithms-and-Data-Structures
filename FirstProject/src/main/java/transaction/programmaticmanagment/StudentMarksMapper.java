package transaction.programmaticmanagment;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentMarksMapper implements RowMapper<StudentMarks> {

	@Override
	public StudentMarks mapRow(ResultSet rs, int rowNum) throws SQLException {
		StudentMarks sm = new StudentMarks();
		sm.setId(rs.getInt("id"));
		sm.setName(rs.getString("name"));
		sm.setAge(rs.getInt("age"));
		sm.setMarks(rs.getInt("marks"));
		sm.setYear(rs.getInt("year"));
		
		return sm;
	}

}
