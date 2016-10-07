package treeofbeans;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RemoveNode {
	
	private static Map<Integer, Node> nodes = new Tree().getNodes();
	
	static void remove(int nodeID){
		
		if(nodes.containsKey(nodeID)){
			
			/* **************************************************************
			 * remove node with id equals to value of method parameter nodeID 
			 ****************************************************************/
			
			Node node = nodes.get(nodeID);
				
			if(node.getParentID() == 0){									//if the removed node is tree's root
				setNewRoot(node);
			}else if(node.getChildrenID().size() > 0){
				Node
					parent	= nodes.get(node.getParentID()),
					nodeToReplace = findBiggestValue(node.getChildrenID());
				
				removeFromParentList(nodeToReplace);
				
				for(Integer id : node.getChildrenID()){
					nodes.get(id).setParentID(nodeToReplace.getId());
					nodeToReplace.getChildrenID().add(0, id);
				}
				nodeToReplace.setParentID(node.getParentID());
				parent.getChildrenID().add(nodeToReplace.getId());				
			}	
			
			removeFromParentList(node);
			nodes.remove(nodeID);
		}
	}
	
	static void setNewRoot(Node root){
		
		/* **************************************************************
		 * set new root of the tree, choosing node with the biggest value 
		 * in old root's children id list, 
		 * where old root is referred by method parameter reference root
		 ****************************************************************/
		
		List<Integer> rootChildren = root.getChildrenID();
		Node newRoot = findBiggestValue(rootChildren);
		removeFromParentList(newRoot);
		newRoot.setParentID(0);
		root.getChildrenID().addAll(newRoot.getChildrenID());
		newRoot.getChildrenID().clear();
		newRoot.getChildrenID().addAll(root.getChildrenID());
		root.getChildrenID().clear();
		ShiftNodes.changeParentIDForList(newRoot.getId());
	}
	
	static void removeFromParentList(Node node){
		
		/* **********************************************
		 * remove node's id from its parent children list
		 ************************************************/
		
		Node parent = nodes.get(node.getParentID());
		
		if(parent != null){
			Iterator<Integer> previousParentIt = parent.getChildrenID().iterator();
			
			while(previousParentIt.hasNext()){
				if(previousParentIt.next() == node.getId()){
					previousParentIt.remove();
					break;
				}
			}
		}
	}
	
	private static Node findBiggestValue(List<Integer> list){
		
		/* *****************************************************
		 * searching in list, referred by method parameter list,
		 * for node with biggest value
		 *******************************************************/
	
		double biggestValue = nodes.get(list.get(0)).getValue();
		Node node = nodes.get(list.get(0));
		
		for(int i = 1; i < list.size(); ++i){
			if(nodes.get(list.get(i)).getValue() > biggestValue){
				biggestValue = nodes.get(list.get(i)).getValue();
				node = nodes.get(list.get(i));
			}
		}
		return node;
	}
}
