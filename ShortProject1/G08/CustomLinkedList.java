/* This class written by Girish handles the final question of the project to 
 * reverse order of list and print elements in reverse order.
 */

import java.util.*;

public class CustomLinkedList {
	 
	    static Node head;
	    
	    static class Node {
	 
	        int data;
	        Node next;
	 
	        Node(int d) {
	            data = d;
	            next = null;
	        }
	    }
	 
	    /* Function to reverse the linked list */
	    Node reverseList(Node node) {
	        Node prev = null;
	        Node current = node;
	        Node next = null;
	        while (current != null) {
	            next = current.next;
	            current.next = prev;
	            prev = current;
	            current = next;
	        }
	        node = prev;
	        return node;
	    }
	    
	    /* Function to print the linked list in reverse order */
	    void reversePrint(Node node) {
	        Deque<Node> stack = new ArrayDeque<Node>();
	        while (node != null) {
	            stack.push(node);
	            node = node.next;
	        }
	        // print the stack
	        while (!stack.isEmpty())
	            System.out.print(stack.poll().data + " ");
	    }
	    
	    /* Recursive Function to reverse the linked list */

	    Node reverseListRecursively(Node head) {
	 
	    	if(head==null || head.next == null)
	            return head;
	     
	        //get second node    
	        Node second = head.next;
	        //set first's next to be null
	        head.next = null;
	     
	        Node rest = reverseListRecursively(second);
	        second.next = head;
	     
	        return rest;
	    }
	    
	    
	    /* Recursive Function to print reverse of linked list */
	    void reversePrintRecursively(Node head)
	    {
	        if (head == null) return;
	 
	        // print list of head node
	        reversePrintRecursively(head.next);
	 
	        // After everything else is printed
	        System.out.print(head.data + " ");
	    }
	    
	    // prints content of linked list
	    void display(Node node) {
	        while (node != null) {
	            System.out.print(node.data + " ");
	            node = node.next;
	        }
	    }
	 
	    public static void main(String[] args) {
	        CustomLinkedList list = new CustomLinkedList();
	        list.head = new Node(1);
	        list.head.next = new Node(2);
	        list.head.next.next = new Node(3);
	        list.head.next.next.next = new Node(4);
	         
	        System.out.println("Original Linked list is :");
	        list.display(head);
	        System.out.println("");
	        System.out.println("Reversed linked list using Print function : ");
	        list.reversePrint(head);
	        System.out.println("");
	        System.out.println("Reversed linked list using Recursive Print function : ");
	        list.reversePrintRecursively(head);
	        System.out.println("");
	        Node result = list.reverseList(head);
	        System.out.println("Reversed linked list : ");
	        list.display(result);
	        System.out.println("");
	        Node res = list.reverseListRecursively(result);
	        System.out.println("Reversed linked list using recursive function : ");
	        list.display(res);
	    }
}
