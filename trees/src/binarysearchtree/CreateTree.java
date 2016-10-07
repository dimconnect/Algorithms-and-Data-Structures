package binarysearchtree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class CreateTree {
	
	private static Map<Integer, Node> nodes = new Tree().getNodes();
	private static Node[] array;
	private static List<List<Double>> tree;
	
	static void createTree(Double[] arrayForTree){
		
		List<Node> nodeList = new ArrayList<Node>();
		
		for(Double value : arrayForTree){
			if(!containValueAlready(value)){
				Node node = new Node(value);
				nodes.put(node.getId(), node);
				nodeList.add(node);
			}
		}
		array = new Node[nodeList.size()];
		nodeList.toArray(array);
		
		treeFromArray(0, array.length-1, 0);		
	}
	
	private static void treeFromArray(int beggining, int end, int parentID){						
		
		/* ****************************************
		 * creating balanced binary tree from array
		 ******************************************/
		
		if(beggining <= end){
			
			int mid = (int)(beggining + end)/2;
			
			Node node = array[mid];
			int nodeID = node.getId();
			
			if(parentID > 0){								
				
				/* *************************************
				 * set node's parent id and determine if 
				 * it's either left child or right child
				 ***************************************/
				Node parent = nodes.get(parentID);
				node.setParentID(parentID);
				
				if(node.getValue() < parent.getValue()){
					parent.setLeftChildID(nodeID);
				}else{
					parent.setRightChildID(nodeID);
				}
			}
			
			if(beggining < end){
				treeFromArray(beggining, mid-1, nodeID);
				treeFromArray(mid+1, end, nodeID);
			}
		}
	}	
	
	static boolean containValueAlready(double value){
		
		/* ***************************************************
		 * check if the value is already contained in the tree 
		 *****************************************************/
		boolean valueExist = false;
		
		for(Integer id : nodes.keySet()){
			if(nodes.get(id).getValue() == value){
				valueExist = true;
				break;
			}
		}
		return valueExist;
	}
	
	static List<List<Double>> getTree(){
		
		/* **************************************
		 * return multidimensional ArrayList in 
		 * tree like form containing all elements
		 ****************************************/
		
		tree = new ArrayList<List<Double>>();
		buildTree(Search.findRoot().getId(), 0);
		return tree;
	}
	
	static List<List<Double>> getTree(int nodeID){
		
		/* ******************************************
		 * return multidimensional ArrayList in 
		 * tree like form containing all elements 
		 * in subtree rooted by node with id = nodeID
		 ********************************************/
		
		tree = new ArrayList<List<Double>>();		
		buildTree(nodeID, 0);
		return tree;
	}
	
	private static void buildTree(int nodeID, int layer){
		
		/* *********************************************
		 * building tree of values of all nodes in 
		 * tree(subtree) rooted by node with id = nodeID
		 ***********************************************/
		
		Node node = nodes.get(nodeID);
		
		if(layer > tree.size()-1){
			tree.add(new ArrayList<Double>());
		}		
		
		tree.get(layer).add(node.getValue());
		
		if(node.getLeftChildID() > 0){		//if node has children list
			int id = node.getLeftChildID();				
			buildTree(id, layer+1);
		}
		
		if(node.getRightChildID() > 0){		//if node has children list	
			int id = node.getRightChildID();					
			buildTree(id, layer+1);		
		}
	}	
	
	static void balanceTree(){
		
		/* *******************************
		 * rebuild the tree to be balanced
		 *********************************/
		
		array = TreeToArray.toArray();
		for(Node node : array){
			node.setParentID(0);
			node.setLeftChildID(0);
			node.setRightChildID(0);
		}
		treeFromArray(0, array.length-1, 0);
	}
}
