package binarysearchtree;

import java.util.Map;

public class Search {
	
	private static Map<Integer, Node> nodes = new Tree().getNodes();
	
	static Node searchByID(int nodeID){
		
		/* ***********************************
		 * search node by id and return it 
		 * if found such otherwise return null 
		 *************************************/
		
		Node node = null;	
		if(nodes.containsKey(nodeID)){
			node = nodes.get(nodeID);
		}
		return node;
	}
	
	static Node searchByValue(double value){
		
		/* ***********************************
		 * search node by value and return it 
		 * if found such otherwise return null 
		 *************************************/
		
		Node node = null;	
		for(Integer id : nodes.keySet()){
			node = nodes.get(id);
			if(node.getValue() == value)
				break;
		}
		return node;
	}
	
	static Node findRoot(){
		
		/* **********************************************
		 * search tree's root and return it if found such 
		 * otherwise if the tree is empty return null 
		 ************************************************/
		
		Node root = null;
		for(Integer id : nodes.keySet()){
			if(nodes.get(id).getParentID() == 0){
				root = nodes.get(id);
				break;
			}
		}
		return root;
	}
	
	static Double findBiggestValue(){
		
		/* ******************************************
		 * if the tree is empty return null otherwise
		 * search tree's biggest value 
		 * (value of most right node) and return it
		 ********************************************/
		
		Double biggestValue = null;
		Node node = findRoot();
		
		if(node != null){
			while(node.getRightChildID() > 0){
				node = nodes.get(node.getRightChildID());
			}
			biggestValue = node.getValue();
		}
		return biggestValue;
	}
	
	static double findSmallestValue(){

		/* ******************************************
		 * if the tree is empty return null otherwise
		 * search tree's smallest value 
		 * (value of most left node) and return it
		 ********************************************/
		
		Double smallestValue = null;
		Node node = findRoot();	
		
		if(node != null){
			while(node.getLeftChildID() > 0){
				node = nodes.get(node.getLeftChildID());
			}
			smallestValue = node.getValue();
		}
		return smallestValue;
	}
}
