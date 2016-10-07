package jdbc;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		
		ApplicationContext context;
		
		try{
			context = new FileSystemXmlApplicationContext(
					"D:/Spring/workspace/FirstProject/src/main/java/jdbc/Beans.xml");
			StudentJDBCTemplate studentTemplate = (StudentJDBCTemplate) context.getBean("studentTemplate");
			//studentTemplate.insertInDB("Michael", 28);
			//studentTemplate.insertInDB("Tomas", 21);
			//studentTemplate.insertInDB("Zara", 18);
			
			Student student = studentTemplate.getStudent(1);
			
			System.out.println("Record > \n"
					+ "id: "+student.getId()+"\n"
					+"Student name: " + student.getName()+"\n"
					+ "age: " + student.getAge());
			System.out.println("List of studentts:");
			
			List<Student> students = studentTemplate.listStudents();
			
			for(Student record : students){
				System.out.println("Record > \n"
						+ "id: "+record.getId()+"\n"
						+"Student name: " + record.getName()+"\n"
						+ "age: " + record.getAge());
			}
			
			studentTemplate.update(3, 21);
			student = studentTemplate.getStudent(3);
			
			System.out.println("Record > \n"
					+ "id: "+student.getId()+"\n"
					+"Student name: " + student.getName()+"\n"
					+ "age: " + student.getAge());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
