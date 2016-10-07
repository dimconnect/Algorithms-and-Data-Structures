package dependencyinjection.annotationbasedconfiguration.autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context;
		
		try{
			context = new FileSystemXmlApplicationContext(
					"D:/Spring/workspace/FirstProject/src/main/java/dependencyinjection/annotationbasedconfiguration/autowired/Beans.xml");
			TextEditor editor = (TextEditor) context.getBean("textEditor");
			editor.spellChecker();
			editor.checker();
			editor.checkSpelling();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
