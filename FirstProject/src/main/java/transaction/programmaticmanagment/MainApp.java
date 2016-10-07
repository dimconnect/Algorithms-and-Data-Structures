package transaction.programmaticmanagment;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context;
		
		try{
			context = new FileSystemXmlApplicationContext(
					"D:/Spring/workspace/FirstProject/src/main/java/transaction/programmaticmanagment/Beans.xml");
			StudentJDBCTemplate student = (StudentJDBCTemplate) context.getBean("student");
			
			//student.createRecord("Michael", 28, 98, 2011);
			//student.createRecord("Tomas", 22, 99, 2012);
			//student.createRecord("Zara", 18, 95, 2014);
			
			List<StudentMarks> list = student.studentList();
			for(StudentMarks sm : list){
				System.out.println("Record > \n"
						+ "id: "+sm.getId()+"\n"
						+"Student name: " + sm.getName()+"\n"
						+ "age: " + sm.getAge() + "\n"
						+ "marks: " + sm.getMarks() + "\n"
						+ "year: " + sm.getYear());
			}
			
			student.updateAge(3, 21);
			
			StudentMarks updatedStudentAge = student.getStudent(3);
			System.out.println("Record > \n"
					+ "id: "+updatedStudentAge.getId()+"\n"
					+"Student name: " + updatedStudentAge.getName()+"\n"
					+ "age: " + updatedStudentAge.getAge() + "\n"
					+ "marks: " + updatedStudentAge.getMarks() + "\n"
					+ "year: " + updatedStudentAge.getYear());
			
			student.updateMarks(3, 96);
			
			StudentMarks updatedStudentMarks = student.getStudent(3);
			System.out.println("Record > \n"
					+ "id: "+updatedStudentAge.getId()+"\n"
					+"Student name: " + updatedStudentMarks.getName()+"\n"
					+ "age: " + updatedStudentMarks.getAge() + "\n"
					+ "marks: " + updatedStudentMarks.getMarks() + "\n"
					+ "year: " + updatedStudentMarks.getYear());
 		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
