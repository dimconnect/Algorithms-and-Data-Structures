package dependencyinjection.annotationbasedconfiguration.required;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context;
		
		try{
			context = new FileSystemXmlApplicationContext(
					"D:/Spring/workspace/FirstProject/src/main/java/dependencyinjection/annotationbasedconfiguration/required/Beans.xml");
			Student student = (Student) context.getBean("student");
			System.out.println("Name: " + student.getName());
			System.out.println("Age: " + student.getAge());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
