package transaction.declarativemanagment;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class StudentJDBCTemplate implements StudentDAO {

	private JdbcTemplate template; 
	
	@Override
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public void createRecord(String name, Integer age, Integer marks,Integer year) {
		
		try{
			String sql = "insert into student(name, age) values(?, ?)";
			template.update(sql, name, age);
			
			sql = "select max(id) from student";
			Integer studentID = template.queryForInt(sql);
			
			sql = "insert into marks(studentID, marks, year) values(?, ?, ?)";
			template.update(sql, studentID, marks, year);
		}catch(DataAccessException dae){
			dae.printStackTrace();
		}
		
		return;
	}

	@Override
	public List<StudentMarks> studentList() {
		String sql = "select * from student, marks where student.id = marks.studentID";
		List<StudentMarks> list = template.query(sql, new StudentMarksMapper());
		
		return list;
	}

	@Override
	public StudentMarks getStudent(Integer id) {
		String sql = "select * from student, marks where student.id = ? and marks.studentID = student.id";
		StudentMarks student = template.queryForObject(sql, new Object[]{id}, new StudentMarksMapper());
		
		return student;
	}

	@Override
	public void updateStudentAge(Integer age, Integer id) {
		String sql = "update student set age = ? where id = ?";
		template.update(sql, age, id);
		
		return;
	}

	@Override
	public void updateStudentMarks(Integer marks, Integer id) {
		String sql = "update marks set marks = ? where studentId = ?";
		template.update(sql, marks, id);
		
		return;
	}

}
