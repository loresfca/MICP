import java.io.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class Solution {
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
    	//getting the size of the linked list
    	int ln = getLength(head);
    	//Structure to store the first half of the list
    	Stack<Character> first_half = new Stack<Character>();
    	//Size of the first half. If the linked list's size is odd,the middle is n/2 rounded 
    	//to the upper closest integer
    	int md = (int)Math.ceil((double)ln/2);
    	//storing the first half
    	for(int i = 0; i<md; i++){
    		first_half.push(head.value);
    		head = head.next;
    	}
    	//If the size is odd, it doesn't consider the middle element, so
    	//it is popped from the stack
    	if(ln%2!=0) first_half.pop();
    	//Compares the upper element of the stack with the next 
    	//value until the stack is empty or they are not the same
    	while(!first_half.isEmpty()){
    		char curr = first_half.pop();
    		if(curr!=head.value) return false;
    		head = head.next;
    	}
    	//if we reach this point the list is a palindrome so we return true
    	return true;
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