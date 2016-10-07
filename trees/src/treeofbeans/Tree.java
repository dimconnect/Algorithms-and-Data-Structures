package treeofbeans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tree {
	
	private static Map<Integer, Node> nodes = new HashMap<Integer, Node>();
	private static double adjustment  = -1;
	private static MathOperations mathOperation = null;
	
	public Tree(){}		
	
	public Tree(Double[] array){
		
		/* **************************************
		 * create tree based structure from array
		 ****************************************/
		CreateTree.createTree(array);		
	}
	
	public Tree(List<Double> li){
		
		/* *************************************
		 * create tree based structure from List
		 ***************************************/	
		this ((Double[])li.toArray());
				
	} 

	public void insert(double newNode){
		
		/* *****************************************
		 * create new node with value = newNode
		 * and insert it in the tree based structure
		 *******************************************/		
		InsertInTree.insert(newNode);
	}
	
	public void insert(double[] newNodes){
		
		/* ***********************************************
		 * insert elements of array, referred by method 
		 * parameter newNodes, in the tree based structure
		 *************************************************/	
		for(double i : newNodes)
			InsertInTree.insert(i);
	}
	
	public void insert(List<Double> newNodes){
		
		/* ***********************************************
		 * insert elements of list, referred by method 
		 * parameter newNodes, in the tree based structure
		 *************************************************/	
		for(Double i : newNodes)
			InsertInTree.insert(i);
	}
	
	public void attachTo( int parentID, double newNode){
		
		/* **********************************************
		 * attach newly created node with value = newNode
		 * to existing node with id  = parentID
		 ************************************************/		
		InsertInTree.attachTo( parentID, newNode);
	}			
	
	public void changeValue(int nodeID, double newValue){
		
		/* *************************************************
		 * change the value of node with id = nodeID
		 * to be equals to value of method parameter newLeaf
		 ***************************************************/
		
		if(nodes.containsKey(nodeID)){
			nodes.get(nodeID).setValue(newValue);
		}
	}
	
	public void moveElementTo(int nodeID, int newParentID){
		
		/* ************************************
		 * move node with id = nodeID attach it
		 * to node with id = newParentID
		 **************************************/
		ShiftNodes.moveElementTo(nodeID, newParentID);
	}
	
	public void exchangeElementsPosition(int nodeID1, int nodeID2){
		
		/* ******************************** *****************
		 * exchange parents of node with id = nodeID1 and 
		 * node with id = nodeID2 and their children id lists
		 ****************************************************/
		ShiftNodes.exchangeNodes(nodeID1, nodeID2);
	} 
	
	public void exchangeSubtreesPosition(int nodeID1, int nodeID2){
		
		/* *********************************************************************
		 * exchange parents of node with id = nodeID1 and node with id = nodeID2
		 ***********************************************************************/		
		ShiftNodes.exchangeSubtrees(nodeID1, nodeID2);
	} 
	
	public void remove(int nodeID){
		RemoveNode.remove(nodeID);
	}
	
	public void setRules(int nodeID, MathOperations operation, double adjustment){			
		
		/* **********************************
		 * set rules to node with id = nodeID
		 * regardless of common rules
		 ************************************/
		if(nodes.containsKey(nodeID)){
			nodes.get(nodeID).setRules(operation, adjustment);
		}
	}
	
	public void setRulesToAll(MathOperations operation, double adjust){		
		
		/* ****************************
		 * set common rules to all node
		 ******************************/
		adjustment = adjust;
		mathOperation = operation;
		for(Integer id : nodes.keySet()){
			nodes.get(id).setRules(operation, adjust);
		}
	}
	
	public void setAdjustment(int nodeID, double adjustment){
		if(nodes.containsKey(nodeID)){
			nodes.get(nodeID).setAdjustment(adjustment);
		}
	}
	
	public void setAdjustmentToAll(double adjust){		
		adjustment = adjust;
		for(Integer id : nodes.keySet()){
			nodes.get(id).setAdjustment(adjust);
		}
	}
	
	public void setMathOperation (int nodeID, MathOperations operation){		
		if(nodes.containsKey(nodeID)){
			nodes.get(nodeID).setMathOperation(operation);
		}
	}
	
	public void setMathOperationToAll (MathOperations operation){		
		for(Integer id : nodes.keySet()){
			nodes.get(id).setMathOperation(operation);
		}
		mathOperation = operation;
	}
	
	static double getAdjustment(){
		return adjustment;
	}
	
	static MathOperations getMathOperations(){
		return mathOperation;
	}
	
	public Map<Integer, Node> getNodes(){
		return nodes;
	}
	
	public List<List<Double>> getTree(){
		return CreateTree.getTree();
	}
	
	public List<List<Double>> getSubTree(int nodeID){
		return CreateTree.getTree(nodeID);
	}
}
