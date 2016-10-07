package dependencyinjection.annotationbasedconfiguration.autowired;

public class SpellChecker {
	
	public SpellChecker(){
		System.out.println("SpellChecker constructor!");
	}
	
	public void checkSpelling(String autowired){
		System.out.println("Check spelling for autowired " +autowired);
	}
}
