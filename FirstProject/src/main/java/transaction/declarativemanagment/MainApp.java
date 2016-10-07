package transaction.declarativemanagment;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context;
		
		try{
			context = new FileSystemXmlApplicationContext(
					"D:/Spring/workspace/FirstProject/src/main/java/transaction/declarativemanagment/Beans.xml");
			
			StudentDAO student = (StudentDAO)context.getBean("studentTemplate");
			
			student.createRecord("Michael", 28, 97, 2011);
			student.createRecord("Tomas", 24, 98, 2012);
			student.createRecord("Zara", 18, 94, 2016);
			
			List<StudentMarks> list = student.studentList();
			
			for(StudentMarks sm : list){
				System.out.println("Record > \n"
						+ "id: "+sm.getId()+"\n"
						+"Student name: " + sm.getName()+"\n"
						+ "age: " + sm.getAge() + "\n"
						+ "marks: " + sm.getMarks() + "\n"
						+ "year: " + sm.getYear());
			}
			
			student.updateStudentAge(22, 3);
			student.updateStudentMarks(96, 3);
			
			StudentMarks updatedStudent = student.getStudent(3);
			
			System.out.println("Record > \n"
					+ "id: "+updatedStudent.getId()+"\n"
					+"Student name: " + updatedStudent.getName()+"\n"
					+ "age: " + updatedStudent.getAge() + "\n"
					+ "marks: " + updatedStudent.getMarks() + "\n"
					+ "year: " + updatedStudent.getYear());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
