package binarytree;

import java.util.Vector;

class InsertInTree {
	
	private static  Vector<Vector<Node>> tree;	
	
	static void insert(Node newLeaf){
		
		tree = new Tree().getTree();
		int[] coordinates = insertValue(newLeaf);		
		
		Adjustment.adjustValues(coordinates[0], coordinates[1], 0);		
	}
	
	private static int[] insertValue(Node newLeaf){
		
		/* *****************************************
		 * insert new element in the first empty
		 * slot and return element's new coordinates
		 *******************************************/
		
		int[] coordinates = new int[2];
		
		done:
		for(int j = 0; j < tree.size(); j++){
			for(int i = 0; i < tree.get(j).size(); i++){
				if(tree.get(j).get(i) == null){ 
					tree.get(j).set(i, newLeaf);
					if(Tree.getAdjustment() > -1) newLeaf.setAdjustment(Tree.getAdjustment());
					if(Tree.getMathOperations() != null) newLeaf.setMathOperation(Tree.getMathOperations());
					coordinates[0] = j;
					coordinates[1] = i;
					break done;
				}
			}
		}
		return coordinates;
	}
	
	static void attachTo(int layer, int indexOfParent, Node newLeaf){
		
		/* ***************************************************
		 * attach new element (newLeaf) to existing one
		 * (placed in row = layer and position = indexOfParent)
		 *****************************************************/
		
		tree = new Tree().getTree();		
		
		if(layer < tree.size() && layer >= 0 && newLeaf != null){
			
			if(Tree.getAdjustment() > -1) newLeaf.setAdjustment(Tree.getAdjustment());
			if(Tree.getMathOperations() != null) newLeaf.setMathOperation(Tree.getMathOperations());
			
			if(tree.get(layer).get(indexOfParent) == null){
				
				/* ************************************************************
				 * if there is no element with coordinates [layer][indexOfLeaf] 
				 * to attach, the new element is placed at these coordinates
				 **************************************************************/
				
				tree.get(layer).set(indexOfParent, newLeaf);
				Adjustment.adjustValues(layer, indexOfParent, 0);
			
			}else if(layer < tree.size() - 1){
				
				findPlace(layer+1, 2*indexOfParent, 2*indexOfParent+1, newLeaf);
			
			}else{
				
				/* *********************************************************
				 * if the element to which attach is placed in the last row,
				 * create new row to make space for the new element
				 ***********************************************************/
				
				addLayer();
				tree.get(layer+1).set(2*indexOfParent, newLeaf);
				Adjustment.adjustValues(layer+1, 2*indexOfParent, 0);
			}
		}
	}
	
	private static void findPlace(int layer, int indexOfFirstChild, int indexOfLastChild, Node newLeaf){
		
		/* *************************************************************************
		 * searching for empty slot in subtree with root the element to which attach
		 * *************************************************************************
		 * method parameters indexOfFirstChild and indexOfLastChild determine
		 * the range in which the elements of subtree for row = layer are placed
		 ***************************************************************************/
		
		int emptySlot = -1;
		
		for(int i = indexOfFirstChild; i <= indexOfLastChild; ++i){
			if(tree.get(layer).get(i) == null){
				emptySlot = i;
				break;
			}
		}
		
		if(emptySlot > -1){
			
			/* **********************************************************************
			 * if there is empty slot in subtree in row = layer place the new element
			 * and adjust all related to it elements up to the  tree's root
			 ************************************************************************/
			
			tree.get(layer).set(emptySlot, newLeaf);
			Adjustment.adjustValues(layer, emptySlot, 0);				
		}else{
			
			/* **********************************************************************
			 * if there is no empty slot in subtree in row = layer search in next row
			 ************************************************************************/
			
			if(layer < tree.size()-1)
				
				findPlace(layer+1, 2*indexOfFirstChild, 2*indexOfLastChild+1, newLeaf);
			
			else{	
				attachTo(layer, indexOfFirstChild, newLeaf);
			}
		}				
	}
	
	private static void addLayer(){
		
		Vector<Node> newLayer = new Vector<Node>();
		
		for(int i = 0; i < Math.pow(2, tree.size()); i++){
			newLayer.add(null);
		}
		
		tree.add(newLayer);
	} 			
}
