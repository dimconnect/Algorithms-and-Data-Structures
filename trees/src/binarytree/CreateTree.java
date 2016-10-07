package binarytree;

import java.util.Vector;

class CreateTree {
	
	private static Vector<Vector<Node>> tree;
	private static Node[] array;
	private static Vector<Node> lastRow;
	
	static void createTree(Node[] arrayForTree){
		
		tree = new Tree().getTree();
		array =  arrayForTree;		
		treeFromArray(0, array.length-1, 0);		
		
		lastRow = tree.get(tree.size()-1);
		rotateInLastRow(1);
	}
	
	private static Node treeFromArray(int beggining, int end, int index){						
		
		/* ****************************************************
		 * creating balanced binary tree from array recursively
		 ******************************************************/
		
		Vector<Node> treeLeaf = new Vector<Node>();		
		
		if(beggining < end){
			
			int mid = (int)(beggining + end)/2;
			
			if(index == 0){
				
				/* **************************************
				 * Set root of the tree and tree's height
				 ****************************************/
				setHeight();
				Vector<Node> root = new Vector<Node>();
				root.add(array[mid]);
				tree.add(index, root);
			}									
			index++;
			
			/* ******************************************************************************
			 * value of the variable index is the number of the tree's layer in which
			 * the elements in the Vector object referred by variable treeLeaf will be stored
			 ********************************************************************************/
			
			treeLeaf.add(treeFromArray(beggining, mid-1, index));
			treeLeaf.add(treeFromArray(mid+1, end, index));
			
			for(int i = 0; i < treeLeaf.size(); i++)
				tree.get(index).add(treeLeaf.get(i));			
			
			return array[mid];
			
		}else if(beggining == end){
			return array[end];
		
		}else{			
			return null;
		}
	}				
	
	private static void rotateInLastRow(int indexOfLeaf){
		
		/* **************************************
		 * if leaf, placed in the last row 
		 * have no left sibling, make left rotate
		 ****************************************/
		
		if(lastRow.get(indexOfLeaf) != null && lastRow.get(indexOfLeaf-1) == null){
			lastRow.set(indexOfLeaf-1, tree.get(tree.size()-2).get(indexOfLeaf/2));
			tree.get(tree.size()-2).set(indexOfLeaf/2, lastRow.get(indexOfLeaf));
			lastRow.set(indexOfLeaf, null);
			
			rotateInLastRow(indexOfLeaf+2);
		}
	}
	
	private static void setHeight(){
		
		/* **********************************************
		 * Determine height of the tree and set size 
		 * of the Vector object referred by variable tree
		 ************************************************/
		
		int sum = 0, i = 0;		
		while(array.length-1 > sum){
			i++;
			tree.add(new Vector<Node>());
			sum += Math.pow(2, i);
		}		
	}	
}
