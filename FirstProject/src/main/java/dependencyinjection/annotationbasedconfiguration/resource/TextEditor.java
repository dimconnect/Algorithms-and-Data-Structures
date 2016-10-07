package dependencyinjection.annotationbasedconfiguration.resource;

import javax.annotation.Resource;

public class TextEditor {
	private SpellChecker spellChecker;
	private SpellChecker checker;
	
	/* ******************************************************************
	 * @Resource annotation without name attribute first search for match 
	 * field name in setter method and bean id, if no match found then
	 * search for match setter method argument name and bean id, 
	 * if no math found then search for match field type and bean type 
	 * (specified by bean's class attribute), if more than one bean 
	 * of that type exist, @Qualifier annotation is needed
	 ********************************************************************/
	@Resource
	public void setChecker(SpellChecker check){
		System.out.println("Set checker!");
		this.checker = check;
	}
	
	public SpellChecker getChecker(){
		return checker;
	}
	
	public SpellChecker getSpellChecker() {
		return spellChecker;
	}
	
	@Resource(name="spellChecker")
	public void setSpellChecker(SpellChecker spellChecker) {
		System.out.println("Set spellChecker!");
		this.spellChecker = spellChecker;
	}
	
	public void spellCheck(){
		spellChecker.checkeSpelling("spellChecker!");
	}
	
	public void check(){
		checker.checkeSpelling("checker!");
	}
}
