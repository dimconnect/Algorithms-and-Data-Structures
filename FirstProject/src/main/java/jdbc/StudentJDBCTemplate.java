package jdbc;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class StudentJDBCTemplate implements StudentDAO{
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void insertInDB(String name, Integer age) {
		String sql = "insert into Student(name, age) values(?, ?)";
		jdbcTemplateObject.update(sql, name, age);
		System.out.println("Create record of student with name: "
				+ name + "and age: " + age);
		return;
	}

	@Override
	public Student getStudent(Integer id) {
		String sql = "select * from Student where id=?";
		Student student = 
				jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new StudentMapper());
		return student;
	}

	@Override
	public List<Student> listStudents() {
		String sql = "select * from Student";
		List<Student> students = 
				jdbcTemplateObject.query(sql, new StudentMapper());
		return students;
	}

	@Override
	public void update(Integer id, Integer age) {
		String sql = "update Student set age = ? where id = ?";
		jdbcTemplateObject.update(sql, age, id);
		System.out.println("Update record with id = " + id);
		return;
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from Student where id = ?";
		jdbcTemplateObject.update(sql, id);
		return;
	}

}
