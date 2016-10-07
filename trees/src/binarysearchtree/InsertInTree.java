package binarysearchtree;

import java.util.Map;

class InsertInTree {
	
	private static  Map<Integer, Node> nodes;	
	
	static void insert(Node newNode){
		
		/* ***************************
		 * insert new node in the tree
		 *****************************/
		
		nodes = new Tree().getNodes();
		nodes.put(newNode.getId(), newNode);
		findPlace(newNode, Search.findRoot());
		
	}
	
	private static void findPlace(Node newNode, Node node){
		
		/* *****************************************************************
		 * searching appropriate place for newly added node (newNode) 
		 * compare it with tree's node (node), starting from the tree's root 
		 * moving to the bottom of the tree
		 *******************************************************************/
		
		if(newNode.getValue() < node.getValue()){						//newNode value is smaller than node value
			
			if(node.getLeftChildID() > 0){								//node already has left child
				findPlace(newNode, nodes.get(node.getLeftChildID()));	//move to left
			}else{														//node hasn't left child
				newNode.setParentID(node.getId());						//set node as parent of newNode
				node.setLeftChildID(newNode.getId());					//set newNode as left child of node
			}
		}else{															//newNode value is greater than node value
			if(node.getRightChildID() > 0){								//node already has right child
				findPlace(newNode, nodes.get(node.getRightChildID()));	//move to right
			}else{														//node hasn't right child
				newNode.setParentID(node.getId());						//set node as parent of newNode
				node.setRightChildID(newNode.getId());					//set newNode as right child of node
			}
		}
	}			
}
