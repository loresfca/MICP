import java.io.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class Solution {
	//Node structure considered for this problem
	class Node {
		int value;
		Node left;
		Node right;

		Node(int value, Node left,Node right){
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}

    public boolean isSubtree(Node t, Node s) {
    	//input validation
    	if(t == null) return true;
    	if(s == null) return false;
    	//queue for BFS
    	Queue<Node> bfs = new LinkedList<Node>();
    	bfs.add(s);
    	//while there are still nodes on the tree
    	while(!bfs.isEmpty()){
    		s = bfs.remove();
    		//If this node is a leaf ignore it
    		if(s==null) continue;
    		//if both t and s have the same value compare
    		if(t.value == s.value){
    			//If they have the same children return true
    			if(checkEquals(t.left,s.left)&&checkEquals(t.right,s.right)){
    				return true;
    			}
    		}
    		//add children to continue checking.
    		bfs.add(s.left);
    		bfs.add(s.right);
    	}
    	return false;
    }

    public boolean checkEquals (Node t, Node s){
    	//this method traverse to see if t and s are both the same tree
    	//if both are null return true
    	if(t==null&&s==null) return true;
    	//if one is a leaf but the other is not return false
    	if(t==null||s==null) return false;
    	//if they don't have the same value return false
    	if(t.value!=s.value) return false;
    	//Check children of both trees recursively
    	return checkEquals(t.left,s.left)&&checkEquals(t.right,s.right);
    }

    @Test
   public void testIsSubtree() {
        //t is null;
        Node t = null;
        Node s = new Node(1,null,null);
        assertEquals(true,isSubtree(t,s)); 
        //s is null
        t = new Node(1,null,null);
        assertEquals(false,isSubtree(t,null)); 
        //Same tree
        assertEquals(true,isSubtree(t,s)); 
        //Two paths, first is wrong (same level)
        s = new Node(2,new Node(1,null,null),new Node(1,null,null));
        assertEquals(true,isSubtree(t,s)); 
        //Partially correct
        s = new Node(2,new Node(1,new Node(2,null,null),null),new Node(2,null,null));
        assertEquals(false,isSubtree(t,s)); 
        //no coincidence
        s = new Node(2,null,null);
        assertEquals(false,isSubtree(t,s)); 
        //Two paths, first is wrong (different level level)
        s = new Node(2,new Node(1,new Node(2,null,null),new Node(1,null,null)),new Node(2,null,null));
        assertEquals(true,isSubtree(t,s)); 
   }
}