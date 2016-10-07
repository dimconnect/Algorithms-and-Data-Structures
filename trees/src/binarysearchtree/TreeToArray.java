package binarysearchtree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class TreeToArray {
	
	private static Map<Integer, Node> nodes;
	private static List<Node> list;
	
	static Node[] toArray(){		
		
		/* *************************************
		 * return sorted array from tree's nodes
		 ***************************************/
		
		nodes = new Tree().getNodes();
		
		if(!nodes.isEmpty()){
			list = new ArrayList<Node>();
			Node[] array = new Node[nodes.keySet().size()];
			treeToArray(Search.findRoot());
			return list.toArray(array);
		}else{
			return null;
		}
	}
	
	private static void treeToArray(Node node){
		
		/* *******************************************************
		 * walk through the tree's nodes starting from tree's root
		 *********************************************************/
		
		if(node.getLeftChildID() > 0){							//node has left child
			treeToArray(nodes.get(node.getLeftChildID()));		//go to left
		}
		
		list.add(node);											//adding node to the list
		
		if(node.getRightChildID() > 0){							//node has right child
			treeToArray(nodes.get(node.getRightChildID()));		//go to right
		}
	}
}
