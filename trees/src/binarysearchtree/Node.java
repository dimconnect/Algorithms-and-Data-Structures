package binarysearchtree;

public class Node {
	
	private static int counter = 0;
	private final int id = ++counter;
	private int parentID, leftChildID, rightChildID;	
	private Double value;

	public Node(double value){
		this.value = value;
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
	
	public int getRightChildID() {
		return rightChildID;
	}

	public void setRightChildID(int rightChildID) {
		this.rightChildID = rightChildID;
	}

	public int getLeftChildID() {
		return leftChildID;
	}

	public void setLeftChildID(int leftChildID) {
		this.leftChildID = leftChildID;
	}
	
	public double getValue(){
		return value;
	}
	
	public void setValue(double value){
		this.value = value;
	}
	
	public String toString(){
		return value.toString();
	}
}
