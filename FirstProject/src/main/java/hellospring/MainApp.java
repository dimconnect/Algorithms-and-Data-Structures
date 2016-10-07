package hellospring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp {
	public static void main(String[] args){
		AbstractApplicationContext context;
		try{
			context = new ClassPathXmlApplicationContext("Beans.xml");
			HelloWorld greeting = (HelloWorld)context.getBean("helloWorld");
			
			System.out.println(greeting.getMessage1());
			System.out.println(greeting.getMessage2());
			greeting.setMessage1("Michael");
			greeting.setMessage2("Gadzhev");
			System.out.println(greeting.getMessage1());
			System.out.println(greeting.getMessage2());
			
			context = new FileSystemXmlApplicationContext(
					"D:/Spring/workspace/FirstProject/src/main/java/Beans.xml");
			HelloBulgaria greeting2 = (HelloBulgaria)context.getBean("helloBulgaria");
			
			System.out.println(greeting2.getMessage1());
			System.out.println(greeting2.getMessage2());
			System.out.println(greeting2.getMessage3());
			greeting2.setMessage1("Mike");
			greeting2.setMessage2("Perez");
			greeting2.setMessage3("Thunder");
			System.out.println(greeting2.getMessage1());
			System.out.println(greeting2.getMessage2());
			System.out.println(greeting2.getMessage3());
			
			context.registerShutdownHook();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
