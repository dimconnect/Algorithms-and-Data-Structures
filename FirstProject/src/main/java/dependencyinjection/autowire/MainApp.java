package dependencyinjection.autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context;
		
		try{
			context = new FileSystemXmlApplicationContext(
					"D:/Spring/workspace/FirstProject/src/main/java/dependencyinjection/autowire/Beans.xml");
			TextEditor editor = (TextEditor) context.getBean("autowireByName");
			editor.spellCheck();
			
			editor = (TextEditor) context.getBean("autowireByType");
			editor.spellCheck();
			
			editor = (TextEditor) context.getBean("autowireconstructor");
			editor.spellCheck();
		}catch(Exception e){
			e.printStackTrace();}
	}

}
