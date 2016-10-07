package treeofbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class CreateTree {
	
	private static Map<Integer, Node> nodes = new Tree().getNodes();
	private static List<List<Double>> tree;
	
	static void createTree(Double[] arrayForTree){
		
		/* ***************************************************************
		 * create tree based structure from array, set first array's 
		 * element as root, and set all others elements as root's children
		 *****************************************************************/
		
		Node node;
		int parentID = 1;
		
		if(arrayForTree.length > 0){
			for(int i = 0; i < arrayForTree.length; ++i){
				node = new Node (arrayForTree[i]);
		
				if(i > 0){
					node.setParentID(parentID);
					
					/* ****************************************************
					 * adding node's id in the parent list with children id
					 ******************************************************/
					nodes.get(parentID).getChildrenID().add(node.getId());
				}
				nodes.put(node.getId(),node);
			}
		}
	}	
	
	static List<List<Double>> getTree(){
		
		/* **************************************
		 * return multidimensional ArrayList in 
		 * tree like form containing all elements
		 ****************************************/
		
		tree = new ArrayList<List<Double>>();
		
		for(Integer id : nodes.keySet()){
			if(nodes.get(id).getParentID() == 0){
				createTree(id, 0);
				break;
			}
		}
		return tree;
	}
	
	static List<List<Double>> getTree(int nodeID){
		
		/* ******************************************
		 * return multidimensional ArrayList in 
		 * tree like form containing all elements 
		 * in subtree rooted by node with id = nodeID
		 ********************************************/
		
		tree = new ArrayList<List<Double>>();		
		createTree(nodeID, 0);
		return tree;
	}
	
	private static void createTree(int nodeID, int layer){
		
		/* ***************************************************************
		 * building tree of values of all nodes in tree(subtree) rooted 
		 * by node with id = nodeID applying node's rules to their parents
		 *****************************************************************/
		
		Node node = nodes.get(nodeID);
		List<Integer> nodeChildrenID = node.getChildrenID();
		
		if(layer > tree.size()-1){
			tree.add(new ArrayList<Double>());
		}		

		double adjustedValuen = node.getValue();
		
		if(nodeChildrenID.size() > 0){		//if node has children list
	
			for(int i = 0; i < nodeChildrenID.size(); ++i){
				int id = nodeChildrenID.get(i);				
				double addition;
				
				createTree(id, layer+1);
				
				int offsetInTree = tree.get(layer+1).size()-1;
				
				if((addition = executeMathOperetion(nodes.get(id), 
						adjustedValuen, tree.get(layer+1).get(offsetInTree))) != 0){
			
					adjustedValuen = addition;
				}
			}		
		}
		tree.get(layer).add(adjustedValuen);
	}
	
	private static double executeMathOperetion(Node node, double adjustedValue, double value){
		
		double addition = value*node.getAdjustment();
		
		switch(node.getMathOperation()){
		 	case SUM: adjustedValue += addition;
		 		break;
		 	case SUB: adjustedValue -= addition;
		 		break;
		 	case MULTI: adjustedValue *= addition;
				break;
		 	case DIV: adjustedValue /= addition;
				break;
		 }
		
		return adjustedValue;
	}
}
