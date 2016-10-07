package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

class TreeToArray {
	
	private static Vector<Vector<Node>> tree;
	private static List<Node> list;
	
	static Node[] toArray(Vector<Vector<Node>> t){		
		
		/* ********************************************************
		 * t - the tree from which the array is going to be derived
		 *********************************************************/
		
		tree = t;
		list = new ArrayList<Node>();
		treeToArray(0, 0);		
		return (Node[])list.toArray();
	}
	
	private static void treeToArray(int layer, int indexOfLeaf){
		
		/* *******************************************************************
		 * layer - number of the layer in the tree (index of the Vector object 
		 * referred by variable tree) in which the root of the subtree is
		 * indexOfLeaf - index of the root of subtree
		 *********************************************************************/
		
		Node root = tree.get(layer).get(indexOfLeaf);
		int 
			leftLeaf = 2*indexOfLeaf,
			rightLeaf = 2*indexOfLeaf+1;
		
		if(root != null){	
			
			/* ***************************************
			 * if root of the subtree exist (not null)
			 *****************************************/
			
			if(layer < tree.size()-1){
				
				/* ****************************************
				 * if the tree have at least one more layer
				 ******************************************/
				
				treeToArray(layer+1, leftLeaf);			
				list.add(root);
				treeToArray(layer+1, rightLeaf);	
			}else{
				
				/* ******************************************************
				 * if layer is the index of the last "row" and the leaf 
				 * referred by variable root is not null (the leaf exist)
				 ********************************************************/
				
				list.add(root);
			}	
		}
	}
}
