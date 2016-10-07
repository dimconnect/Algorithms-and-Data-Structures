package transaction.programmaticmanagment;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class StudentJDBCTemplate implements StudentDAO {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private PlatformTransactionManager transactionManager;
	
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void setTransactionManager(PlatformTransactionManager transactionManager){
		this.transactionManager = transactionManager;
	}
	
	@Override
	public void createRecord(String name, Integer age, Integer marks, Integer year) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try{
			String sql1 = "insert into Student (name, age) values(?, ?)";
			jdbcTemplate.update(sql1, name, age);
			
			String sql2 = "select max(id) from Student";
			Integer studentID = jdbcTemplate.queryForInt(sql2);
			
			String sql3 = "insert into Marks(studentID, marks, year) values(?, ?, ?)";
			jdbcTemplate.update(sql3, studentID, marks, year);
			
			System.out.println("Create record with name " + name + "and age "+ age);
			transactionManager.commit(status);
		}catch(DataAccessException dae){
			transactionManager.rollback(status);
		}
		
		return;
	}

	@Override
	public List<StudentMarks> studentList() {
		String sql = "select * from Student, Marks where Student.id = Marks.studentID";
		List<StudentMarks> studentList = jdbcTemplate.query(sql, new StudentMarksMapper());
		return studentList;
	}
	
	public void updateAge(Integer id, Integer age){
		String sql = "update student set age=? where id=?";
		jdbcTemplate.update(sql, age, id);
		return;
	}
	
	public void updateMarks(Integer id, Integer marks){
		String sql = "update Marks set marks=? where studentID=?";
		jdbcTemplate.update(sql, marks, id);
		return;
	}
	
	public StudentMarks getStudent(Integer id){
		String sql = "select * from Student, Marks where Student.id=? and Marks.studentID=Student.id";
		StudentMarks st = jdbcTemplate.queryForObject(sql, new Object[]{id}, new StudentMarksMapper());
		return st;
	}
}
