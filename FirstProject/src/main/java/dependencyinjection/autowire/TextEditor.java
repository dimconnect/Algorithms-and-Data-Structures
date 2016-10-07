package dependencyinjection.autowire;

public class TextEditor {
	
	private SpellChecker spellChecker;
	private String name;
	
	public TextEditor(){
		System.out.println("TextEditor non-args constructor!");
	}
	
	public TextEditor(SpellChecker spellChecker, String name){
		System.out.println("TextEditor constructor with args!");
		System.out.println(name);
		this.spellChecker = spellChecker;
		this.name = name;
	}
	
	public SpellChecker getSpellChecker() {
		return spellChecker;
	}
	public void setSpellChecker(SpellChecker spellChecker) {
		System.out.println(name);
		this.spellChecker = spellChecker;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void spellCheck(){
		spellChecker.checkSpelling();
	}
}
