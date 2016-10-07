package transaction.programmaticmanagment;

import java.util.List;

import javax.sql.DataSource;

public interface StudentDAO {
	
	public void setDataSource(DataSource dataSource);
	
	public void createRecord(String name, Integer age, Integer marks, Integer years);
	
	public List<StudentMarks> studentList(); 
}
