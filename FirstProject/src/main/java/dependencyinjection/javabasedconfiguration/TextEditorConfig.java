package dependencyinjection.javabasedconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TextEditorConfig {
	
	@Bean(initMethod="init", destroyMethod="destroy")
	public TextEditor textEditor(){
		return new TextEditor(spellChecker());
	}
	
	@Bean(initMethod="init", destroyMethod="destroy")
	public SpellChecker spellChecker(){
		return new SpellChecker();
	}
}
