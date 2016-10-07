package binarysearchtree;

import java.util.Map;

public class RemoveNode {
	
	private static Map<Integer, Node> nodes = new Tree().getNodes();
	
	static Node remove(int nodeID){
		
		Node node = null;
		
		if(nodes.containsKey(nodeID)){
			
			/* ****************************
			 * remove node with id = nodeID 
			 ******************************/
			
			node = nodes.get(nodeID);
			Node child = null;
			int childID = 0;
						
			if(node.getLeftChildID() > 0){											//node has left child
				child = nodes.get(node.getLeftChildID());
			}
			
			if(node.getRightChildID() > 0){											//node has right child
				if(child == null){													//node hasn't left child
					child = nodes.get(node.getRightChildID());
				}else{ 																//node has both left child and right child
					if(child.getRightChildID() > 0){								//node's left child has right child
						Node rightGrandChild = nodes.get(child.getRightChildID());	//right child of node's left child
						Node predecessor = nodes.get(node.getRightChildID());		//node's right child
						int predecessorLeftChildID = predecessor.getLeftChildID();	
						
						while(predecessorLeftChildID > 0){							//left subtree of node's right child 
							predecessor = nodes.get(predecessorLeftChildID);
							predecessorLeftChildID = predecessor.getLeftChildID();
						}
						
						/* right child of node's left child goes to
						 * left subtree of node's right child*/
						
						rightGrandChild.setParentID(predecessor.getId());			
						predecessor.setLeftChildID(rightGrandChild.getId());		
					}
					
					/* node's right child becomes right child of node's left child*/
					child.setRightChildID(node.getRightChildID());				
				}
			}
			
			if(child != null){														//node has at least one child
				child.setParentID(node.getParentID());
				childID = child.getId();
			}
			
			if(node.getParentID() > 0){												//node isn't the tree root
				Node parent = nodes.get(node.getParentID());						//node's parent
	
				if(parent.getLeftChildID() == nodeID)								//node is left child
					parent.setLeftChildID(childID);
				else																//node is right child
					parent.setRightChildID(childID);
			}
		}
		return nodes.remove(node.getId());
	}
}
