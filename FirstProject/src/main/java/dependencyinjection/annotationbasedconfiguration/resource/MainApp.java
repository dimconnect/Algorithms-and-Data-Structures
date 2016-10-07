package dependencyinjection.annotationbasedconfiguration.resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context;
		
		try{
			context = new FileSystemXmlApplicationContext(
					"D:/Spring/workspace/FirstProject/src/main/java/dependencyinjection/annotationbasedconfiguration/resource/Beans.xml");
			TextEditor editor = (TextEditor) context.getBean("textEditor");
			editor.spellCheck();
			editor.check();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
