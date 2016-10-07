package binarytree;

import java.util.Vector;

class Adjustment {
		
	private static Vector<Vector<Node>> tree = new Tree().getTree();
	private static double adjustment;
	private static MathOperations mathOperation;
	
	static void adjustValues(int layer, int indexOfLeaf, double oldValue){
		
		/* ******************************************************
		 * adjust all elements related with the element placed at 
		 * coordinates [layer][indexOfLeaf] up to the tree's root
		 ********************************************************/
		
		if(layer > 0 && tree.get(layer-1).get(indexOfLeaf/2) != null){
				
			Node 
				parent = tree.get(layer-1).get(indexOfLeaf/2),
				leaf = null;			
			
			double					
				adjustment = 0,
				oldValueOfParent = parent.getValue(),
				addition = 0,
				valueOfLeaf = 0;						
			
			if(tree.get(layer).get(indexOfLeaf) != null){
				leaf = tree.get(layer).get(indexOfLeaf);
				valueOfLeaf = leaf.getValue();
				adjustment = leaf.getAdjustment();
			}
			
			if(leaf.getMathOperation() == MathOperations.SUM || leaf.getMathOperation() == MathOperations.SUB){
				addition = (valueOfLeaf - oldValue)*adjustment;				
			}else{				
				
				addition = valueOfLeaf;
				
				if(oldValue != 0)
					addition /= oldValue;	
				else
					addition *= adjustment;
			}
			
			if(addition != 0){
				executeMathOperetion(parent, leaf, addition);				
				tree.get(layer-1).set(indexOfLeaf/2, parent);	
				
				adjustValues(layer-1, indexOfLeaf/2, oldValueOfParent);
			}
		}
	}
	
	static void adjustValue(int layer, int indexOfLeaf){
		
		/* *************************************
		 * adjust element regarding its children
		 ***************************************/
		
		if(layer >= 0 && layer < tree.size()-1){
			
			Node
				leftLeaf = tree.get(layer+1).get(2*indexOfLeaf),
				rightLeaf = tree.get(layer+1).get(2*indexOfLeaf+1),
				leaf = tree.get(layer).get(indexOfLeaf);	
			
			double addition;			
								
			if(leftLeaf != null){
				addition = leftLeaf.getValue()*leftLeaf.getAdjustment();
				if(addition != 0)
					executeMathOperetion(leaf, leftLeaf, addition);
			}
			
			if(rightLeaf != null){
				addition = rightLeaf.getValue() * rightLeaf.getAdjustment();
				if(addition != 0)
					executeMathOperetion(leaf, rightLeaf, addition);
			}					
		}
	}	
	
	static void restoreToOriginalValue(int layer, int indexOfLeaf){
		
		/* ******************************************
		 * remove impact of children to their parent,
		 * placed at coordinates [layer][indexOfLeaf] 
		 ********************************************/
		
		if(layer >= 0 && layer < tree.size()-1){
			
			Node
				leftLeaf = tree.get(layer+1).get(2*indexOfLeaf),
				rightLeaf = tree.get(layer+1).get(2*indexOfLeaf+1),
				leaf = tree.get(layer).get(indexOfLeaf);
			
			double 
				addition = 0,
				originalValue;
			
			if(rightLeaf != null){
				
				addition = rightLeaf.getValue() * rightLeaf.getAdjustment();
	
				if(addition != 0){
					originalValue = executeMathOperetionToRestore(leaf, rightLeaf, addition);
					leaf.setValue(originalValue);
				}
			}
			
			if(leftLeaf != null){
				
				addition = leftLeaf.getValue()*leftLeaf.getAdjustment();
				
				if(addition != 0){
					originalValue = executeMathOperetionToRestore(leaf, leftLeaf, addition);
					leaf.setValue(originalValue);
				}
			}						
		}
	}
	
	static void setNewRules(int layer, int indexOfLeaf, double adjustment, MathOperations operation){
		
		/* ********************************************************************
		 * set new rules for element placed at coordinates [layer][indexOfLeaf]
		 * and re-adjust all related to it elements
		 **********************************************************************/
		
		if(layer > 0 && layer < tree.size()){
			
			if(tree.get(layer).get(indexOfLeaf) != null){
				
				Node leaf = tree.get(layer).get(indexOfLeaf);			
				
				double parentOldValue = tree.get(layer-1).get(indexOfLeaf/2).getValue();
				
				restoreToOriginalValue(layer-1, indexOfLeaf/2);
				
				leaf.setRules(operation, adjustment);
				
				adjustValue(layer-1, indexOfLeaf/2);
				
				adjustValues(layer-1, indexOfLeaf/2, parentOldValue);
			}
		}
	}
	
	static void setNewAdjustment(int layer, int indexOfLeaf, double adjustment){
		
		/* *************************************************************************
		 * set new adjustment for element placed at coordinates [layer][indexOfLeaf]
		 * and re-adjust all related to it elements
		 ***************************************************************************/
		
		if(layer > 0 && layer < tree.size()){
			
			if(tree.get(layer).get(indexOfLeaf) != null){
				
				Node leaf = tree.get(layer).get(indexOfLeaf);			
				
				double parentOldValue = tree.get(layer-1).get(indexOfLeaf/2).getValue();
				
				restoreToOriginalValue(layer-1, indexOfLeaf/2);
				
				leaf.setAdjustment(adjustment);
				
				adjustValue(layer-1, indexOfLeaf/2);				
				
				adjustValues(layer-1, indexOfLeaf/2, parentOldValue);
			}
		}
	}
	
	static void setNewMathOperation(int layer, int indexOfLeaf, MathOperations operation){
		
		/* ****************************************************************************
		 * set new mathOperation for element placed at coordinates [layer][indexOfLeaf]
		 * and re-adjust all related to it elements
		 ******************************************************************************/
		
		if(layer > 0 && layer < tree.size()){
			
			if(tree.get(layer).get(indexOfLeaf) != null){
				
				Node leaf = tree.get(layer).get(indexOfLeaf);			
				
				double parentOldValue = tree.get(layer-1).get(indexOfLeaf/2).getValue();
				
				restoreToOriginalValue(layer-1, indexOfLeaf/2);
				
				leaf.setMathOperation(operation);
				
				adjustValue(layer-1, indexOfLeaf/2);				

				adjustValues(layer-1, indexOfLeaf/2, parentOldValue);
			}
		}
	}
	
	static void reAdjustAllValues(){
		
		/* ******************************************
		 * restore the original value of the elements
		 * in the tree and apply new rules
		 ********************************************/
		
		adjustment  = Tree.getAdjustment();
		mathOperation = Tree.getMathOperations();
		
		restoreToOriginalAllValues(0, 0);
		addjustAllValues(0, 0);
	}		
	
	private static void addjustAllValues(int layer, int indexOfLeaf){
		
		/* *************************************************
		 * adjust all elements in the tree with common rules
		 ***************************************************/
		
		if(tree.get(layer).get(indexOfLeaf) != null){
			
			if(layer < tree.size() - 1){
				
				addjustAllValues(layer+1, 2*indexOfLeaf);
				addjustAllValues(layer+1, 2*indexOfLeaf+1);			
			}							
			
			if(layer > 0){
				
				Node 
					leaf = tree.get(layer).get(indexOfLeaf),
					parent = tree.get(layer-1).get(indexOfLeaf/2);		
				double addition = 0;
				
				addition = leaf.getValue() * leaf.getAdjustment();
				
				if(addition != 0)
					executeMathOperetion(parent, leaf, addition);
			}
		}
	}
	
	private static void restoreToOriginalAllValues(int layer, int indexOfLeaf){
	
		/* ******************************************************
		 * restore the original value of all elements in the tree
		 ********************************************************/
		
		if(tree.get(layer).get(indexOfLeaf) != null){
			
			restoreToOriginalValue(layer, indexOfLeaf);
			
			if(layer < tree.size() - 1){		
				
				restoreToOriginalAllValues(layer+1, 2*indexOfLeaf+1);
				restoreToOriginalAllValues(layer+1, 2*indexOfLeaf);				
			}
			
			Node leaf = tree.get(layer).get(indexOfLeaf);
			
			if(mathOperation != null && mathOperation != leaf.getMathOperation())
				leaf.setMathOperation(mathOperation);				
					
			if(adjustment > -1 && adjustment != leaf.getAdjustment())
				leaf.setAdjustment(adjustment);				
		}					
	}

	private static void executeMathOperetion(Node parent, Node leaf, double addition){
		
		double value = parent.getValue();
		
		switch(leaf.getMathOperation()){
		 	case SUM: value += addition;
		 		break;
		 	case SUB: value -= addition;
		 		break;
		 	case MULTI: value *= addition;
				break;
		 	case DIV: value /= addition;
				break;
		 }
		parent.setValue(value);
	}
	
	static double executeMathOperetionToRestore(Node parent, Node leaf, double addition){
		
		double value = parent.getValue();
		
		switch(leaf.getMathOperation()){
			case SUM: value -= addition;
				break;
			case SUB: value += addition;
				break;
			case MULTI: value /= addition;
				break;
			case DIV: value *= addition;
				break;
		}		
		
		return value;
	}
}
