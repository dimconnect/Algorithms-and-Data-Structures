package eventhandling.customevent;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext context;
		
		try{
			context = new FileSystemXmlApplicationContext(
					"D:/Spring/workspace/FirstProject/src/main/java/eventhandling/customevent/Beans.xml");
			CustomEventPublisher publisher = (CustomEventPublisher) context.getBean("publisher");
			publisher.publisher();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
