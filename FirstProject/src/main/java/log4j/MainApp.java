package log4j;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp {

	static Logger log = Logger.getLogger(MainApp.class.getName());
	
	public static void main(String[] args) {
		ApplicationContext context;
		
		try{
			context = new FileSystemXmlApplicationContext(
					"D:/Spring/workspace/FirstProject/src/main/java/log4j/Beans.xml");
			
			log.info("Going to create HelloWorld object!");
			
			HelloWorld hello = (HelloWorld) context.getBean("helloWorld");
			hello.getMsg();
			
			log.info("Exiting the program!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
