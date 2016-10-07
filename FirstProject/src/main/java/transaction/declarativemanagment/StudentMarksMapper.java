package transaction.declarativemanagment;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentMarksMapper implements RowMapper<StudentMarks> {

	@Override
	public StudentMarks mapRow(ResultSet rs, int numRow) throws SQLException {
		StudentMarks student = new StudentMarks();
		student.setId(rs.getInt("id"));
		student.setName(rs.getString("name"));
		student.setAge(rs.getInt("age"));
		student.setMarks(rs.getInt("marks"));
		student.setYear(rs.getInt("year"));
		return student;
	}

}
