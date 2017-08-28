import java.io.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
//This class is the implementation of the alternative method with O(1) space usage.
//Implies the modification of the list as suggested by Connie and has the fast pointer method to find the middle node.
public class Solution2 {
	//Node structure considered for this problem
	class Node{
		char value;
		Node next;
		Node(char value, Node next){
			this.value = value;
			this.next = next;
		}
	}
    public boolean isPalindrome(Node head) {
    	//input validation
    	if(head == null) throw new NullPointerException();
    	//getting the middle element and length of the list to know if it is even or odd
        int ln = getLength(head);
    	Node middle = getMiddle(head);
        //if odd we ignore the middle element
    	if(ln%2!=0) middle = middle.next;
        //here the linked list is reversed using the method described below
        middle = reverse(middle); 
        //we traverse the reversed and original half to see if they are the same, if so we return true.
        while(middle!=null){
            if(head.value!=middle.value) return false;
            head = head.next;
            middle = middle.next;
        }
    	//if we reach this point the list is a palindrome so we return true
    	return true;
    }
    public Node reverse(Node head){
        //Reverses the list doing the following:
        /*
        aux stores the previous node that will become the next node of the current node, since the first node 
        will have no next the aux initial value is null.
        */
        Node aux = null;
        //this is repeated until we get to the end of the list
        while(head != null){
            //stores the previous next value to know which node are we heading next
            Node next = head.next;
            //Stores the previous node as the current node's next, thus reversing the order
            head.next = aux;
            //store this node as the last node checked
            aux = head;
            //moves to the next node
            head = next;
        }
        //returns the head of the list
        return aux;
    }
    public int getLength(Node head){
        //gets the length of the linked list
        //aux is created to avoid overriding head
        Node aux = head;
        int i = 0;
        while(aux!=null){
            i++;
            aux=aux.next;
        }
        return i;
    }
    public Node getMiddle(Node head){
    	//finds the middle node by using a fast pointer.
        Node aux = head;
        while(aux!=null&&aux.next!=null){
            aux = aux.next.next;
            head = head.next;
        }
        return head;
    }
    @Test
   public void testIsPalindrome() {
	   	//Null Array
	   	boolean error = false;
	   	try{
			isPalindrome(null);
	   	}catch(NullPointerException e){
	   		error = true;
	   	}
	   	assertEquals(true,error);
        //Single node
        Node head = new Node('a',null);
        assertEquals(true,isPalindrome(head)); 
        //Correct even length
        head = new Node('a',new Node('a',null));
        assertEquals(true,isPalindrome(head));
        //Correct odd length
        head = new Node('a',new Node('b',new Node('a',null)));
        assertEquals(true,isPalindrome(head));
        //Incorrect even
        head = new Node('a',new Node('b',null));
        assertEquals(false,isPalindrome(head));
        //incorrect odd
        head = new Node('a',new Node('a',new Node('b',null)));
        assertEquals(false,isPalindrome(head));
        //Correct with special characters
        head = new Node('*',new Node(' ',new Node('*',null)));
        assertEquals(true,isPalindrome(head));
        //Incorrect with upper/lowercase
        head = new Node('A',new Node('b',new Node('a',null)));
        assertEquals(false,isPalindrome(head));
   }
}