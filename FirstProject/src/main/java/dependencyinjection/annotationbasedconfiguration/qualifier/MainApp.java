package dependencyinjection.annotationbasedconfiguration.qualifier;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context;
		
		try{
			context = new FileSystemXmlApplicationContext(
					"D:/Spring/workspace/FirstProject/src/main/java/dependencyinjection/annotationbasedconfiguration/qualifier/Beans.xml");
			Profile profile = (Profile) context.getBean("profile");
			profile.printName();
			profile.printAge();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
