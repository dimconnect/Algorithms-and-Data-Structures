package binarytree;

import java.util.List;
import java.util.Vector;

public class RemoveNode {
	
	private static Vector<Vector<Node>> tree;
	
	static void remove(int layer, int indexOfLeaf){
		
		tree = new Tree().getTree();
		
		if(layer >= 0 && layer < tree.size() && tree.get(layer).get(indexOfLeaf) != null){
			
			/* ***************************************************************************
			 * remove the element placed at coordinates [layer][indexOfLeaf] from the tree
			 * and adjust all related to it elements up to the tree's root
			 *****************************************************************************/
			double oldValue = tree.get(layer).get(indexOfLeaf).getValue();
									
			
			if(layer < tree.size()-1){					
				
				removeLeaf(layer, indexOfLeaf);
				Adjustment.adjustValues(layer, indexOfLeaf, oldValue);
			}else if(layer == tree.size()-1 && tree.get(layer).get(indexOfLeaf) != null){				
				
				/* **********************************************
				 * if layer is equal to the index of the last row
				 ************************************************/
				double parentOldValue = tree.get(layer-1).get(indexOfLeaf/2).getValue();
				Adjustment.restoreToOriginalValue(layer-1, indexOfLeaf/2);			
				tree.get(layer).set(indexOfLeaf, null);
				Adjustment.adjustValue(layer-1, indexOfLeaf/2);
				Adjustment.adjustValues(layer-1, indexOfLeaf/2, parentOldValue);
			}
			removeLayer();
		}
	}
	
	private static void removeLeaf(int layer, int indexOfLeaf){
		
		Node leaf = tree.get(layer).get(indexOfLeaf);				
		
		if(layer < tree.size()-1){
			
			/* ************************************************************
			 * if value of layer isn't equal to the index of the last "row"  
			 **************************************************************/
			
			if(leaf != null){
				
				Node
					leftLeaf = tree.get(layer+1).get(2*indexOfLeaf),
					rightLeaf = tree.get(layer+1).get(2*indexOfLeaf+1);
				
				if(leftLeaf != null){
					
					/* *************************
					 * if element has left child  
					 ***************************/
					
					Adjustment.restoreToOriginalValue(layer+1, 2*indexOfLeaf);					
					tree.get(layer).set(indexOfLeaf, leftLeaf);
					removeLeaf(layer+1, 2*indexOfLeaf);
					Adjustment.adjustValue(layer, indexOfLeaf);									
				
				}else if(rightLeaf != null){
					
					/* **************************************************
					 * if element hasn't left child, but have right child  
					 ****************************************************/
					
					if(layer < tree.size()-2 && tree.get(layer+2).get(2*(2*indexOfLeaf+1)) != null){
						
						/* ******************************
						 * if right child has left child
						 ********************************/
						Adjustment.restoreToOriginalValue(layer+1, 2*indexOfLeaf+1);
						Adjustment.restoreToOriginalValue(layer+2, 2*(2*indexOfLeaf));
						leftLeaf = tree.get(layer+2).get(2*(2*indexOfLeaf+1));
						Node leafLeafCopy = new Node(leftLeaf.getValue());
						leafLeafCopy.setRules(leftLeaf.getMathOperation(), leftLeaf.getAdjustment()*100);
						tree.get(layer).set(indexOfLeaf, leafLeafCopy);							
						Adjustment.adjustValue(layer, indexOfLeaf);
						removeLeaf(layer+2, 2*(2*indexOfLeaf+1));
						Adjustment.restoreToOriginalValue(layer, indexOfLeaf);
					}else{
						
						/* ********************************
						 * if right child hasn't left child
						 **********************************/
						
						Adjustment.restoreToOriginalValue(layer+1, 2*indexOfLeaf+1);						
						tree.get(layer).set(indexOfLeaf, rightLeaf);
						removeLeaf(layer+1, 2*indexOfLeaf+1);
					}
					Adjustment.adjustValue(layer, indexOfLeaf);

				}else{
					
					/* **************************
					 * if element has no children 
					 ****************************/
					tree.get(layer).set(indexOfLeaf, null);
				}
			}
		}else{
			/* **************************************************
			 * if last element in the subtree is already moved up
			 * set element's previous position to be null
			 ****************************************************/
			tree.get(layer).set(indexOfLeaf, null);	
		}
	}
	
	private static void removeLayer(){
		
		/* ************************
		 * remove row if it's empty
		 **************************/
		
		List<Node> lastRow = tree.get(tree.size()-1);
		boolean hasElement = false; 
		
		foundElement:
		for(int i = 0; i < lastRow.size(); ++i){
			if(lastRow.get(i) != null){
				hasElement = true;
				break foundElement;
			}
		}
		if(!hasElement){
			tree.removeElementAt(tree.size()-1);
			removeLayer();
		}
	}
}
