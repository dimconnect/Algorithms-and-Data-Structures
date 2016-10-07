package treeofbeans;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private static int counter = 0;
	private final int id = ++counter;
	private int parentID;
	private List<Integer> childrenID = new ArrayList<Integer>();
	private Double value;
	private double adjustment = -1;
	private MathOperations mathOperation = null;

	public Node(double value){
		this.value = value;
	}
	
	public Node(double value, double adjustment, MathOperations mathOperation){
		this.value = value;
		this.adjustment = adjustment/100;
		this.mathOperation = mathOperation;
	}

	public int getId() {
		return id;
	}
	
	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	
	public List<Integer> getChildrenID() {
		return childrenID;
	}
	
	public double getValue(){
		return value;
	}
	
	public void setValue(double value){
		this.value = value;
	}
	
	public double getAdjustment(){
		return adjustment;
	}

	public void setAdjustment(double adjustment){
		this.adjustment = adjustment/100;
	}

	public MathOperations getMathOperation(){
		return mathOperation;
	}
	
	public void setMathOperation(MathOperations mathOperation){
		this.mathOperation = mathOperation;
	}
	
	public void setRules(MathOperations mathOperation, double adjustment){
		this.mathOperation = mathOperation;
		this.adjustment = adjustment / 100;
	}
	
	public String toString(){
		return value.toString();
	}
}
