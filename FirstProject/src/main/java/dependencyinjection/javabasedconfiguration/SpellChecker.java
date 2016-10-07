package dependencyinjection.javabasedconfiguration;

public class SpellChecker {
	
	public SpellChecker(){
		System.out.println("SpellChecker constructor!");
	}
	
	public void checkSpelling(){
		System.out.println("Check spelling!");
	}
	
	public void init(){
		System.out.println("SpellChecker init()!");
	}
	
	public void destroy(){
		System.out.println("SpellChecker destroy()!");
	}
}
