package treeofbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ShiftNodes {
	
	private static Map<Integer, Node> nodes = new Tree().getNodes();
	
	static void moveElementTo(int nodeID, int newParentID){
		
		/* ********************************************************
		 * move node with id = nodeID to new position, 
		 * attaching it to another exist node with id = newParentID 
		 **********************************************************/
		
		if(nodes.containsKey(nodeID) && nodes.containsKey(newParentID)){
			
			Node 
				child = nodes.get(nodeID),
				parent = nodes.get(newParentID);
			
			RemoveNode.removeFromParentList(child);
			
			if(child.getParentID() == 0)
				RemoveNode.setNewRoot(child);
			
			parent.getChildrenID().add(child.getId());
			child.setParentID(newParentID);
		}
	}
	
	static void exchangeSubtrees(int nodeID1, int nodeID2){
		
		/* **************************************************
		 * exchange position of two subtrees rooted by nodes
		 * with id nodeID1 and nodeID2, if non of these nodes
		 * do not contain the other in its subtree
		 ****************************************************/
		
		if(nodes.containsKey(nodeID1) && nodes.containsKey(nodeID2)){
			Node 
				node1 = nodes.get(nodeID1),
				node2 = nodes.get(nodeID2);
			
			if(searchInSubtree(node1, nodeID2) == false && searchInSubtree(node2, nodeID1) == false){
				/* ***********************************************
				 * check both nodes for independence of each other  
				 *************************************************/
				
				int temp = node1.getParentID();						
				
				RemoveNode.removeFromParentList(node1);
				RemoveNode.removeFromParentList(node2);
				
				node1.setParentID(node2.getParentID());
				node2.setParentID(temp);
				
				if(nodes.get(node1.getParentID()) != null)
					nodes.get(node1.getParentID()).getChildrenID().add(node1.getId());
				if(nodes.get(node2.getParentID()) != null)
					nodes.get(node2.getParentID()).getChildrenID().add(node2.getId());
			}
		}		
	}
	
	static void exchangeNodes(int nodeID1, int nodeID2){
		
		/* ***************************************
		 * exchange position of two existing nodes
		 * whit id nodeID1 and nodeID2 
		 *****************************************/
		
		if(nodes.containsKey(nodeID1) && nodes.containsKey(nodeID2)){
			
			exchangeSubtrees(nodeID1, nodeID2);
			
			List<Integer> temp = new ArrayList<Integer>(nodes.get(nodeID1).getChildrenID());
			
			nodes.get(nodeID1).getChildrenID().clear();
			nodes.get(nodeID1).getChildrenID()
							.addAll(nodes.get(nodeID2).getChildrenID());
			changeParentIDForList(nodeID1);
			
			nodes.get(nodeID2).getChildrenID().clear();
			nodes.get(nodeID2).getChildrenID().addAll(temp);
			changeParentIDForList(nodeID2);
		}
	}
	
	private static boolean searchInSubtree(Node node, int id){
		
		/* ************************************************
		 * check if node with id = id reside in subtree
		 * rooted by node referred by method parameter node
		 **************************************************/
		
		boolean inSubtree = false;
		
		if(node.getChildrenID().size() > 0){
			for(Integer childID : node.getChildrenID()){
				if(childID != id){
					inSubtree = searchInSubtree(nodes.get(childID), id);
				}else{
					inSubtree = true;
				}
				if(inSubtree){ 			//if match a child with id = method parameter id, 
					break;				//stop searching and return true through all recursive calls
				}
			}
		}
		return inSubtree;
	}
	
	static void changeParentIDForList(int nodeID){
		
		for(Integer id : nodes.get(nodeID).getChildrenID())
			nodes.get(id).setParentID(nodeID);
	}
}
