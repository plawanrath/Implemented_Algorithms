/**
 * @author plawanrath
 * This class contains code to simulate recursion using stacks.
 * For this simulation, I take my MergeSortLists class where I have written
 * code to merge sort Linked Lists, and make that non-recursive using stacks. For
 * this reason, the merge function is the same as that of MergeSortLists class.
 */
import java.util.*;

public class RecursionSimulation {

	/*
	 * This function is the merge function to merge two sorted lists.
	 * Parameter 1: List which is being sorted.
	 * Parameter 2: Start position of first half
	 * Parameter 3: mid point of the list passed in merge function
	 * Parameter 4: end position of list passed into the function. 
	 */
	private <T extends Comparable<? super T>> void merge(SinglyList<T> myList,int start,int midPoint,int end)
	{
		SinglyList<T> L = myList.copy(start, midPoint);
		SinglyList<T> R = myList.copy(midPoint+1, end);
		Node<T> tempL = L.start;
		Node<T> tempR = R.start;
		for(int i=start;i<=end;i++)
		{
			if(tempL == null && tempR != null)
			{
				myList.updateAtPos(tempR.getValue(), i);
				tempR = tempR.getNext();
			}
			else if(tempR == null && tempL != null)
			{
				myList.updateAtPos(tempL.getValue(), i);
				tempL = tempL.getNext();
			}
			else if(tempL.getValue().compareTo(tempR.getValue()) <= 0)
			{
				myList.updateAtPos(tempL.getValue(), i);
				tempL = tempL.getNext();
			}
			else
			{
				myList.updateAtPos(tempR.getValue(), i);
				tempR = tempR.getNext();			
			}
		}
	}

	/*
	 * This is the non-recursive merge sort function of merge sort algorithm.
	 * Parameter 1: List to be sorted
	 * Parameter 2: Start position of the list.
	 * Parameter 3: End position of the list.
	 * After function completion myList function stores contains the sorted list.
	 */
	public <T extends Comparable<? super T>> void mergeSort(SinglyList<T> myList,int start,int end)
	{
		Stack<StackEntry> recursionCallStack = new Stack<StackEntry>();
		StackEntry record = new StackEntry(false,start,end);
		recursionCallStack.push(record);
		while(!recursionCallStack.isEmpty())
		{
			record = recursionCallStack.pop();
			int midPoint = (record.first + record.last) / 2;
			if(record.isSorted)
				merge(myList,record.first,midPoint,record.last);
			else
			{
				if(record.first < record.last) 
				{
					recursionCallStack.push(new StackEntry(true,record.first,record.last));
					recursionCallStack.push(new StackEntry(false,record.first,midPoint));
					recursionCallStack.push(new StackEntry(false,midPoint+1,record.last));
				}
			}
		}
	}
	
	/*
	 * This is the StackEntry class that stores whether the segment of the list
	 * pushed into the stack is sorted or not. We refer the isSorted element of this class
	 * to check whether its time to merge because as per merge sort logic we merge two sorted
	 * lists.  
	 */
	private class StackEntry
	{
		boolean isSorted;
		int first;
		int last;
		
		public StackEntry(boolean s,int f,int l)
		{
			isSorted = s;
			first = f;
			last = l;
		}
	}
	
	public static void main(String[] args) {
		SinglyList<Integer> testList = new SinglyList<Integer>();
		testList.insertAfterEnd(12);
		testList.insertAfterEnd(5);
		testList.insertAfterEnd(34);
		testList.insertAfterEnd(2);
		testList.insertAfterEnd(11);
		RecursionSimulation mergeSortDemo = new RecursionSimulation();
		System.out.println("Unsorted List :");
		testList.display();
		mergeSortDemo.mergeSort(testList, 0, 4);
		System.out.println("Sorted List :");
		testList.display();
	}

}
