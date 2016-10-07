package binarysearchtree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tree {
	
	/* ********************************
	 * tree of nodes with unique values
	 **********************************/
	
	private static Map<Integer, Node> nodes = new HashMap<Integer, Node>();
	
	public Tree(){}		
	
	public Tree(Double[] array){
		
		/* **************************************
		 * create tree based structure from array
		 ****************************************/
		
		QuickSort sort = new QuickSort(array);
		sort.quickSort(0, array.length-1);
		CreateTree.createTree(array);		
	}
	
	public Tree(List<Double> li){
		
		/* *************************************
		 * create tree based structure from List
		 ***************************************/
		this ((Double[])li.toArray());
				
	} 

	public void add(double newLeaf){
		
		/* *****************************************
		 * create new node with value = newNode
		 * and insert it in the tree based structure
		 *******************************************/
		
		if(!CreateTree.containValueAlready(newLeaf)){
			Node newNode = new Node(newLeaf);
			InsertInTree.insert(newNode);
		}		
	}
	
	public void addAll(double[] newNodes){
		
		/* ***************************************************************
		 * create nodes from array's elements, referred by method 
		 * parameter newNodes, and insert them in the tree based structure
		 *****************************************************************/	
		
		for(double i : newNodes){
			Node newNode = new Node(i);
			InsertInTree.insert(newNode);
		}
	}
	
	public void addAll(List<Double> newNodes){
		
		/* ***************************************************************
		 * create nodes from list's elements, referred by method 
		 * parameter newNodes, and insert them in the tree based structure
		 *****************************************************************/	
		
		for(Double i : newNodes){
			Node newNode = new Node(i);
			InsertInTree.insert(newNode);
		}
	}			
	
	public void changeValue(int nodeID, double newValue){
		
		/* *************************************************
		 * change the value of node with id = nodeID
		 * to be equals to value of method parameter newLeaf
		 ***************************************************/	
		ChangeValue.changeValue(nodeID, newValue);
	}
	
	public void changeValue(double nodeValue, double newValue){
		
		/* *************************************************
		 * change the value of node with value = nodeValue
		 * to be equals to value of method parameter newLeaf
		 ***************************************************/
		
		Node node = searchByValue(nodeValue);
		if(node != null)
			ChangeValue.changeValue(node.getId(), newValue);
	}
	
	public Node remove(int nodeID){
		
		/* **********************************************************
		 * remove node with id = nodeID from the tree based structure
		 ************************************************************/
		return RemoveNode.remove(nodeID);
	}
	
	public Node remove(double nodeValue){
		
		/* ****************************************************************
		 * remove node with value = nodeValue from the tree based structure
		 ******************************************************************/
		
		Node node = searchByValue(nodeValue);
		if(node != null)
			return RemoveNode.remove(node.getId());
		else
			return null;
	}
	
	public Node searchByID(int nodeID){
		return Search.searchByID(nodeID);
	}
	
	public Node searchByValue(double value){
		return Search.searchByValue(value);
	}
	
	public Node findRoot(){
		return Search.findRoot();
	}
	
	public double findBiggestValue(){
		return Search.findBiggestValue();
	}
	
	public double findSmallestValue(){
		return Search.findSmallestValue();
	}
	
	public Map<Integer, Node> getNodes(){
		
		/* *************************************
		 * return map with keys node's ids and 
		 * corresponding to them nodes as values 
		 ***************************************/
		return nodes;
	}
	
	public List<List<Double>> getTree(){
		return CreateTree.getTree();
	}
	
	public List<List<Double>> getSubTree(int nodeID){
		if(nodes.containsKey(nodeID))
			return CreateTree.getTree(nodeID);
		else
			return CreateTree.getTree();
	}
	
	public List<List<Double>> getSubTree(double value){
		Node node = searchByValue(value);
		if(node != null)
			return CreateTree.getTree(node.getId());
		else
			return CreateTree.getTree();
	}
	
	public Node[] toArray(){
		return TreeToArray.toArray();
	}
	
	public List<List<Double>> balance(){
		CreateTree.balanceTree();
		return CreateTree.getTree();
	}
}
