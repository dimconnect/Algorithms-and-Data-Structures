package aop.xmlschemabased;

public class Student {
	private Integer age;
	private String name;
	
	public Student(){}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void printException(){
		System.out.println("Exception raised!");
		throw new IllegalArgumentException();
	}
}
