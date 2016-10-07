package aop.xmlschemabased;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp {
	
	public static void main(String[] args){
		ApplicationContext context;
		
		try{
			context = new FileSystemXmlApplicationContext(
					"D:/Spring/workspace/FirstProject/src/main/java/aop/xmlschemabased/Beans.xml");
			
			Student student = (Student) context.getBean("student");
			
			student.getAge();
			student.getName();
			student.printException();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
