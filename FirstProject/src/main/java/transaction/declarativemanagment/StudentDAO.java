package transaction.declarativemanagment;

import java.util.List;

import javax.sql.DataSource;

public interface StudentDAO {
	
	public void setDataSource(DataSource dataSource);
	
	public void createRecord(String name, Integer age, Integer marks, Integer year);
	
	public List<StudentMarks> studentList();
	
	public StudentMarks getStudent(Integer id);
	
	public void updateStudentAge(Integer age, Integer id);
	
	public void updateStudentMarks(Integer marks, Integer id);
}
