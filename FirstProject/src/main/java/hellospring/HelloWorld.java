package hellospring;

public class HelloWorld {
	
	private String hello = "Hello, %s!";
	private String message1, message2;
	
	public String getMessage1() {
		return message1;
	}

	public void setMessage1(String name) {
		message1 = String.format(hello, name);
	}
	
	public String getMessage2() {
		return message2;
	}

	public void setMessage2(String name) {
		message2 = String.format(hello, name);
	}
	
	public void init(){
		System.out.println("Initalizing!");
	}
	
	public void destroy(){
		System.out.println("Destroy!");
	}
}
