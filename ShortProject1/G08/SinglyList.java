/**
 * @author plawanrath
 * This is a custom generic Singly Linked List class that can be used.
 * I use this for most of my code that needs a SinglyList.
 */

public class SinglyList<T extends Comparable<? super T>> {
	
	Node<T> start;
	Node<T> end;
	int size;
	
	public SinglyList()
	{
		start =  null;
		end = null;
		size = 0;
	}

	/*
	 * Function to check if the list is empty
	 */
	public boolean isEmpty()
	{
		return start == null;
	}
	
	/*
	 * FUnction to get size of the list
	 */
	public int getSize()
	{
		return this.size;
	}
	
	/*  
	 * Function that inserts a Node at the start of the list
	 */
	public void insertBeforeStart(T value)
	{
		Node<T> temp = new Node<T>(value,null);
		size++;
		if(start == null)
		{
			start = temp;
			end = start;
		}
		else
		{
			temp.setNext(start);
			start = temp;
		}
	}
	
	/*
	 * Function that inserts a Node at the end of the list
	 */
	public void insertAfterEnd(T value)
	{
		Node<T> temp = new Node<T>(value, null);
		size++;
		if(start == null)
		{
			start = temp;
			end = start;
		}
		else
		{
			end.setNext(temp);
			end = temp;
		}
	}
	
	/*
	 * Function to update value at a particular position
	 */
	public void updateAtPos(T value,int pos)
	{
		Node<T> temp = start;
		for(int i=0;i<pos;i++)
			temp = temp.getNext();
		temp.setValue(value);
	}
	
	/*
	 * Function to delete an element at the position given by the parameter.
	 */
	public void deleteAtPos(int pos)
	{
		if(pos == 1)
		{
			start = start.getNext();
			size--;
			return;
		}
		if(pos == size)
		{
			Node<T> s = start;
			Node<T> t= start;
			while(s != end)
			{
				t = s;
				s = s.getNext();
			}
			end = t;
			end.setNext(null);
			size--;
			return;
		}
		Node<T> temp = start;
		pos = pos - 1;
		for(int i = 1;i<size - 1; i++)
		{
			if( i == pos)
			{
				Node<T> temp1 = temp.getNext();
				temp1 = temp1.getNext();
				temp.setNext(temp1);
				break;
			}
			temp = temp.getNext();
		}
		size--;
	}
	
	/*
	 * Function to get value at a particular position.
	 */
	public T getValue(int pos)
	{
		if(pos == 0)
			return start.getValue();
		if(pos == size-1)
			return end.getValue();
		Node<T> temp = start;
		pos = pos - 1;
		for(int i=0;i<size - 2;i++)
		{
			temp = temp.getNext();
			if(i == pos)
				return temp.getValue();
		}
		return null;
	}
	
	/* 
	 * Function to copy values from the List represented by the class
	 * from the beginning to end position passed to the function into 
	 * a new list and return the new list.
	 */
	public SinglyList<T> copy(int endPos)
	{
		SinglyList<T> to = new SinglyList<T>();
		int index = 0;
		if(endPos == 0)
			to.insertAfterEnd(start.getValue());
		else
		{
			Node<T> temp = start;
			endPos = endPos - 1;
			while(index < endPos || temp != null)
			{
				to.insertAfterEnd(temp.getValue());
				temp = temp.getNext();
				index++;
			}
		}
		return to;
	}

	/* 
	 * Function to copy values from the List represented by the class
	 * from the start position passed to the function to end position 
	 * passed to the function into a new list and return the new list.
	 */
	public SinglyList<T> copy(int startPos, int endPos)
	{
		SinglyList<T> to = new SinglyList<T>();
		int index = 0;
		if(startPos > endPos)
			throw new IndexOutOfBoundsException("Start cannot be before end!!");
		if(startPos == 0 && endPos == 0)
			to.insertAfterEnd(start.getValue());
		else
		{
			Node<T> temp = start;
			while(index < startPos)
			{
				temp = temp.getNext();
				index++;
			}
			while(index <= endPos && temp != null)
			{
				to.insertAfterEnd(temp.getValue());
				temp = temp.getNext();
				index++;
			}
		}
		return to;
	}

	/*
	 * Function to display the elements in the list.
	 */
	public void display()
	{
		if(size == 0)
			return;
		if(start.getNext() == null)
		{
			System.out.println(start.getValue());
			return;
		}
		Node<T> temp = start;
		System.out.print(start.getValue() + " ");
		temp = temp.getNext();
		while(temp.getNext() != null)
		{
			System.out.print(temp.getValue() + " ");
			temp = temp.getNext();
		}
		System.out.print(temp.getValue() + "\n");
	}	
}
