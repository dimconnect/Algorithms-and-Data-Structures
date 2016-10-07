package hellospring;

public class HelloBulgaria {
	private String 
		hello = "Hello, %s!",
		message1, message2, message3;

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

	public String getMessage3() {
		return message3;
	}

	public void setMessage3(String nickName) {
		message3 = String.format(hello, nickName);
	}
	
	public void init(){
		System.out.println("Initalizing!");
	}
	
	public void destroy(){
		System.out.println("Destroy!");
	}
}
