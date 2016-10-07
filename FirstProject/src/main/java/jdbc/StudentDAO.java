package jdbc;

import java.util.List;

import javax.sql.DataSource;

public interface StudentDAO {
	
	void setDataSource(DataSource ds);
	
	void insertInDB(String name, Integer age);
	
	Student getStudent(Integer id);
	
	List<Student> listStudents();
	
	void update(Integer id, Integer age);
	
	void delete(Integer id);
}
