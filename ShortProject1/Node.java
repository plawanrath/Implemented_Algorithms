/**
 * @author plawanrath
 * This is a custom Node class that is used by my SinglyList class.
 */

public class Node<T extends Comparable<? super T>> {
	
	T value;
	Node<T> next;
	
	public Node()
	{
		value = null;
		next = null;
	}
	
	public Node(T data,Node<T> link)
	{
		value = data;
		next = link;
	}

	public void setNext(Node<T> n)
	{
		next = n;
	}
	
	public void setValue(T data)
	{
		value = data;
	}
	
	public T getValue()
	{
		return this.value;
	}
	
	public Node<T> getNext()
	{
		return this.next;
	}
}
