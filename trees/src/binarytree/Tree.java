package binarytree;

import java.util.List;
import java.util.Vector;

public class Tree {
	
	private static Vector<Vector<Node>> tree = new Vector<Vector<Node>>();		
	private static double adjustment  = -1;
	private static MathOperations mathOperation = null;  
	
	public Tree(){}		
	
	public Tree(Node[] array){
		
		/* *****************************
		 * create binary tree from array
		 *******************************/						
		CreateTree.createTree(array);		
	}
	
	public Tree(List<Node> li){
		
		/* *****************************
		 * create binary tree from List
		 *******************************/	
		this((Node[])li.toArray());
				
	} 

	public void insert(Node newLeaf){
		
		/* ****************************************
		 * insert new element (newLeaf) in the 
		 * Vector object referred by variable tree)
		 ******************************************/		
		InsertInTree.insert(newLeaf);
	}
	
	public void insert(Node[] newLeaves){
		
		/* *******************************************
		 * insert elements of array (newLeaves) in the 
		 * Vector object referred by variable tree)
		 *********************************************/	
		for(Node i : newLeaves)
			InsertInTree.insert(i);
	}
	
	public void insert(List<Node> newLeaves){
		
		/* *******************************************
		 * insert elements of List (newLeaves) in the 
		 * Vector object referred by variable tree)
		 *********************************************/
		for(Node i : newLeaves)
			InsertInTree.insert(i);
	}
	
	public void attachTo(int layer, int indexOfParent, Node newLeaf){
		
		/* ***************************************************
		 * attach new element (newLeaf) to existing one
		 * (placed in row = layer and position = indexOfParent)
		 *****************************************************/		
		InsertInTree.attachTo(layer, indexOfParent, newLeaf);
	}		

	public Node[] toArray(){	
		
		/* *************************************************
		 * return sorted array from the elements of the tree
		 ***************************************************/
		return TreeToArray.toArray(tree);
	}	
	
	public void changeValue(int layer, int indexOfLeaf, Double newLeaf){
		
		/* **************************************************************************
		 * change the value of an leaf (Node's object), set value of newLeaf variable
		 ****************************************************************************/
		ChangeValue.changeValue(tree, layer, indexOfLeaf, newLeaf);
	}
	
	public void moveElementTo(int layer, int indexOfLeaf, int layerOfParent, int indexOfParent){
		
		/* *************************************************
		 * move element with coordinates[layer][indexOfLeaf]
		 * to coordinates[layerOfParent][indexOfParent]
		 ***************************************************/
		ChangeValue.moveElementTo(layer, indexOfLeaf, layerOfParent, indexOfParent);
	}
	
	public void exchangeElementsPosition(int layer1, int indexOfElement1, int layer2, int indexOfElement2){
		
		/* *********************************************************************************************
		 * place element with coordinates[layer1][indexOfLeaf1] to coordinate [layer2][indexOfLeaf2] and
		 * place element with coordinates[layer2][indexOfLeaf2] to coordinate [layer1][indexOfLeaf1]
		 ***********************************************************************************************/
		ChangeValue.exchangeElementsPosition(layer1, indexOfElement1, layer2, indexOfElement2);
	} 
	
	public void removeLeaf(int layer, int indexOfLeaf){
		RemoveNode.remove(layer, indexOfLeaf);
	}
	
	public void setRules(int layer, int indexOfLeaf, MathOperations operation, double adjustment){			
		
		/* *********************************************************
		 * set rules to element with coordinates[layer][indexOfLeaf]
		 * regardless of common rules
		 ***********************************************************/
		Adjustment.setNewRules(layer, indexOfLeaf, adjustment, operation);
	}
	
	public void setRulesToAll(MathOperations operation, double adjust){		
		
		/* ******************************************
		 * set common rules to all elements in the tree
		 ********************************************/
		adjustment = adjust;
		mathOperation = operation;	
		Adjustment.reAdjustAllValues();
	}
	
	public void setAdjustment(int layer, int indexOfLeaf, double adjustment){		
		Adjustment.setNewAdjustment(layer, indexOfLeaf, adjustment);
	}
	
	public void setAdjustmentToAll(double adjust){		
		adjustment = adjust;
		Adjustment.reAdjustAllValues();
	}
	
	public void setMathOperation (int layer, int indexOfLeaf, MathOperations operation){		
		Adjustment.setNewMathOperation(layer, indexOfLeaf, operation);
	}
	
	public void setMathOperationToAll (MathOperations operation){		
		mathOperation = operation;
		Adjustment.reAdjustAllValues();
	}
	
	static double getAdjustment(){
		return adjustment;
	}
	
	static MathOperations getMathOperations(){
		return mathOperation;
	}
	
	public Vector<Vector<Node>> getTree(){
		return tree;
	}
	
}
