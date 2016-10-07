package eventhandling.helloworld;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext context;
		
		try{
			context = new FileSystemXmlApplicationContext(
					"D:/Spring/workspace/FirstProject/src/main/java/eventhandling/helloworld/Beans.xml");
			context.start();
			HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
			helloWorld.getMessage();
			context.stop();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
