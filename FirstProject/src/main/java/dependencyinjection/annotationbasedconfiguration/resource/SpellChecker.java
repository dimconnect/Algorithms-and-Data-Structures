package dependencyinjection.annotationbasedconfiguration.resource;

public class SpellChecker {
	
	public SpellChecker(){
		System.out.println("SpellChecker contructor!");
	}
	
	public void checkeSpelling(String method){
		System.out.println("Check spelling for " + method);
	}
}
