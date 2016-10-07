package dependencyinjection.annotationbasedconfiguration.autowired;

import org.springframework.beans.factory.annotation.Autowired;

public class TextEditor {
	
	private SpellChecker spellChecker;
	private SpellChecker checkSpelling;
	@Autowired
	private SpellChecker checker;
	
	@Autowired
	public TextEditor(SpellChecker spellChecker){
		System.out.println("TextEditor constructor!");
		checkSpelling = spellChecker;
	}
	
	public SpellChecker getSpellChecker() {
		return spellChecker;
	}
	
	@Autowired
	public void setSpellChecker(SpellChecker spellChecker) {
		System.out.println("Set spellChecker!");
		this.spellChecker = spellChecker;
	}
	
	public SpellChecker getChecker(){
		return checker;
	}
	
	public void spellChecker(){
		spellChecker.checkSpelling("setter!");
	}
	
	public void checker(){
		checker.checkSpelling("field!");
	}
	
	public void checkSpelling(){
		checkSpelling.checkSpelling("constructor!");
	}
}
