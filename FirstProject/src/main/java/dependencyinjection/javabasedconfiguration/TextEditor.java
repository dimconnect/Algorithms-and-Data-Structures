package dependencyinjection.javabasedconfiguration;

public class TextEditor {
	
	private SpellChecker spellChecker;

	public TextEditor(SpellChecker spellChecker){
		System.out.println("TextEditor constructor!");
		this.spellChecker = spellChecker;
	}
	
	public void spellCheck(){
		spellChecker.checkSpelling();
	}
	
	public void init(){
		System.out.println("TextEditor init()!");
	}
	
	public void destroy(){
		System.out.println("TextEditor destroy()!");
	}
}
