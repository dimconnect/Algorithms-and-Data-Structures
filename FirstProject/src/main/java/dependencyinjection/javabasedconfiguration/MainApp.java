package dependencyinjection.javabasedconfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context;
		
		try{
			context = new AnnotationConfigApplicationContext(TextEditorConfig.class);
			System.out.println("Context created!");
			
			TextEditor editor = context.getBean(TextEditor.class);
			editor.spellCheck();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
