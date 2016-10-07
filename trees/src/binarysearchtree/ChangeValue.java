package binarysearchtree;

import java.util.Map;

class ChangeValue {
	
	private static Map<Integer, Node> nodes = new Tree().getNodes();
	
	static void changeValue(int nodeID, double newValue){
		
		/* *************************************
		 * change value of node with id = nodeID 
		 ***************************************/
		
		if(!CreateTree.containValueAlready(newValue) && nodes.containsKey(nodeID)){
			
			Node node = RemoveNode.remove(nodeID);
			node.setValue(newValue);
			node.setLeftChildID(0);
			node.setRightChildID(0);
			InsertInTree.insert(node);
		}
	}
}
