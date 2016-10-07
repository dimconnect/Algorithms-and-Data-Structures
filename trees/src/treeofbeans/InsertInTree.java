package treeofbeans;

import java.util.Map;

class InsertInTree {
	
	private static  Map<Integer, Node> nodes;	
	
	static void insert(double newLeaf){
		
		/* ***************************************
		 * insert new element as tree's root child
		 *****************************************/
		
		nodes = new Tree().getNodes();
		Node newNode = new Node(newLeaf);

		newNode.setRules(Tree.getMathOperations(), Tree.getAdjustment());
	
		if(nodes.size() > 0){								//if the structure already has elements 
			
			Node treeRoot = nodes.get(0);
			
			treeRoot.getChildrenID().add(newNode.getId());	//adding the new node's id in the tree root's children id list
			newNode.setParentID(treeRoot.getId());			//set tree root's id as parent id of the new node
			nodes.put(newNode.getId(), newNode);		
		}else{
			nodes.put(newNode.getId(), newNode);
		}	
	}
	
	static void attachTo(int parentID, double newLeaf){
		
		/* ***********************************************
		 * attach new node, with value equals to value
		 * of parameter newLeaf, to node with id = parenID
		 *************************************************/
		
		nodes = new Tree().getNodes();
		Node 
			newNode = new Node(newLeaf),
			parent;
			
		newNode.setRules(Tree.getMathOperations(), Tree.getAdjustment());
		
		if(nodes.containsKey(parentID)){
			
			parent = nodes.get(parentID);				
			parent.getChildrenID().add(newNode.getId());	//adding the new node's id in its parent children id list
			newNode.setParentID(parent.getId());			//set parent id of the new node
			nodes.put(newNode.getId(), newNode);	
		}
	}			
}
