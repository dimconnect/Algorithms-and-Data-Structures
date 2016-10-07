package binarytree;

public class Node {
	
	private double value;
	private double adjustment;
	private MathOperations mathOperation = MathOperations.SUM;
	
	public Node(double value){
		this.value = value;
	}
	
	public Node(double value, double adjustment, MathOperations mathOperation){
		this.value = value;
		this.adjustment = adjustment/100;
		this.mathOperation = mathOperation;
	}
	
	public void setValue(double value){
		this.value = value;
	}
	
	public double getValue(){
		return value;
	}
	
	public void setAdjustment(double adjustment){
		this.adjustment = adjustment/100;
	}
	
	public double getAdjustment(){
		return adjustment;
	}
	
	public void setMathOperation(MathOperations mathOperation){
		this.mathOperation = mathOperation;
	}
	
	public MathOperations getMathOperation(){
		return mathOperation;
	}
	
	public void setRules(MathOperations mathOperation, double adjustment){
		this.mathOperation = mathOperation;
		this.adjustment = adjustment / 100;
	}
	
	public String toString(){
		return ""+value;
	}
}
