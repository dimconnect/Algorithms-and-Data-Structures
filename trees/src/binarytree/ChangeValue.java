package binarytree;

import java.util.Vector;

class ChangeValue {
	
	private static Vector<Vector<Node>> tree;	
	
		

	static void changeValue(Vector<Vector<Node>> tree, int layer, int indexOfLeaf, double newValue){
		
		/* ************************************************
		 * change value of existing element, and adjust 
		 * all related to it elements up to the tree's root
		 **************************************************/
		
		if(layer < tree.size() && layer >=0){			
			Node leaf = tree.get(layer).get(indexOfLeaf);
			double oldValue;
			
			if(leaf != null){
				oldValue = leaf.getValue();
			}else{
				oldValue = 0;
			}
			
			leaf.setValue(newValue);				
			Adjustment.adjustValue(layer, indexOfLeaf);
			Adjustment.adjustValues(layer, indexOfLeaf, oldValue);
		}
	}
	
	static void moveElementTo(int layer, int indexOfLeaf, int layerOfParent, int indexOfParent){
		
		/* *************************************
		 * move element to new position, 
		 * attaching it to another exist element 
		 ***************************************/ 
		
		tree = new Tree().getTree();
		
		if(layer < tree.size() && layer >= 0){
						
			Node leafToMove = tree.get(layer).get(indexOfLeaf);
			double originalValue = leafToMove.getValue();
			Adjustment.restoreToOriginalValue(layer, indexOfLeaf);			
			Adjustment.adjustValues(layer, indexOfLeaf, originalValue);
			
			if(leafToMove != null && layerOfParent < tree.size() && layerOfParent >= 0){
				RemoveNode.remove(layer, indexOfLeaf);					
				InsertInTree.attachTo(layerOfParent, indexOfParent, leafToMove);
			}
		}
	}
	
	static void exchangeElementsPosition(int layer1, int indexOfLeaf1, int layer2, int indexOfLeaf2){
		
		/* ******************************************
		 * exchange position of two existing elements 
		 ********************************************/
		tree = new Tree().getTree();
		
		if(layer1 < tree.size() && layer1 >= 0 && layer2 < tree.size() && layer2 >= 0){
			
			/* ************************************************************
			 * if elements are placed in different rows, change position of 
			 * the element which reside in the row with lower index first 
			 **************************************************************/
			
			if(layer1 < layer2){
				
				/* **************************************
				 * set layer1 to be row with higher index 
				 * and layer2 to be row with lower index
				 ****************************************/
				
				int temp = layer1;
				layer1 = layer2;
				layer2 = temp;
				temp = indexOfLeaf1;
				indexOfLeaf1 = indexOfLeaf2;
				indexOfLeaf2 = temp;
			}
			
			Node 
				leaf1 = tree.get(layer1).get(indexOfLeaf1),
				leaf2 = tree.get(layer2).get(indexOfLeaf2),
				tempLeaf = null;
			
			double 
				valueOfLeaf1 = 0,
				valueOfLeaf2 = 0;
			
			if(leaf1 != null){
				
				/* *****************************************
				 * create new Node object with same states
				 * as Node object referred by variable leaf1 
				 *******************************************/
				
											
			}
			
			if(leaf2 != null){				
				
				valueOfLeaf2 = tree.get(layer2).get(indexOfLeaf2).getValue();
				Adjustment.restoreToOriginalValue(layer2, indexOfLeaf2);
				
				if(leaf1 == null){
					
					/* ******************************************************
					 * remove element with coordinates [layer2][indexOfLeaf2]
					 * if there is no existing element to replace it
					 ********************************************************/
					RemoveNode.remove(layer2, indexOfLeaf2);
					
					/* ********************************************************************
					 * check if nonexistent element with coordinates [layer1][indexOfLeaf1]
					 * has parent, if has no parent move up in the subtree 
					 * searching for existing element to attach
					 **********************************************************************/
					int[] newCoordinates = skipNulls(layer1, indexOfLeaf1);
					layer1 = newCoordinates[0];
					indexOfLeaf1 = newCoordinates[1];
					
				}else{		
										
					/* *****************************************
					 * create new Node object with same states
					 * as Node object referred by variable leaf1 
					 *******************************************/
					valueOfLeaf1 = tree.get(layer1).get(indexOfLeaf1).getValue();				
					Adjustment.restoreToOriginalValue(layer1, indexOfLeaf1);					
					tempLeaf = new Node(leaf1.getValue());
					tempLeaf.setAdjustment(leaf1.getAdjustment()*100);
					tempLeaf.setMathOperation(leaf1.getMathOperation());
					
					/* *****************************************************************************
					 * set object reference at coordinates [layer2][indexOfLeaf2] to null 
					 * after setting object reference at coordinates [layer1][indexOfLeaf1] to refer 
					 * to the same object preventing changes of the object's state after adjustment  
					 *******************************************************************************/
					tree.get(layer2).set(indexOfLeaf2, null);						
				}
				tree.get(layer1).set(indexOfLeaf1, leaf2);					
				Adjustment.adjustValue(layer1, indexOfLeaf1);
				Adjustment.adjustValues(layer1, indexOfLeaf1, valueOfLeaf1);
			}else {
				
				/* ******************************************************
				 * remove element with coordinates [layer1][indexOfLeaf1]
				 * if there is no existing element to replace it
				 ********************************************************/
				RemoveNode.remove(layer1, indexOfLeaf1);
			}
					
			if(tempLeaf != null){					
				tree.get(layer2).set(indexOfLeaf2, tempLeaf);
				Adjustment.adjustValue(layer2, indexOfLeaf2);
				Adjustment.adjustValues(layer2, indexOfLeaf2, valueOfLeaf2);
			}
		}
	}
	
	private static int[] skipNulls(int layer, int indexOfLeaf){
		
		/* ****************************************
		 * searching for existing element to attach
		 ******************************************/
		
		int[] coordinates = null;
		
		if(layer > 0 && tree.get(layer-1).get(indexOfLeaf/2) == null)
			coordinates = skipNulls(layer-1, indexOfLeaf/2);
		else
			coordinates = new int[]{layer, indexOfLeaf};			

		return coordinates;
	}
}
