package dependencyinjection.constructorbased;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context;
		
		try{
			context = new FileSystemXmlApplicationContext(
					"D:/Spring/workspace/FirstProject/src/main/java/dependencyinjection/constructorbased/Beans.xml");
			TextEditor editor = (TextEditor)context.getBean("textEditor");
			editor.spellCheck();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
