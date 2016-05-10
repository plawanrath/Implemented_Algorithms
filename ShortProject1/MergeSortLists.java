/**
 * @author plawanrath
 * THis class performs Merge Sort over Linked Lists. I use my own SinglyList class 
 * for this Merge Sort on Linked Lists.
 */

public class MergeSortLists {

	/*
	 * This is the recursive merge sort function of merge sort algorithm.
	 * Parameter 1: List to be sorted
	 * Parameter 2: Start position of the list.
	 * Parameter 3: End position of the list.
	 * After function completion myList function stores contains the sorted list.
	 */
	public static <T extends Comparable<? super T>> void mergeSort(SinglyList<T> myList,int start,int end)
	{
		if(start < end)
		{
			int midPoint = (start + end)/2;
			mergeSort(myList,start,midPoint);
			mergeSort(myList,midPoint+1,end);
			merge(myList,start,midPoint,end);
		}
		else
			return;	
	}
	
	/*
	 * This function is the merge function to merge two sorted lists.
	 * Parameter 1: List which is being sorted.
	 * Parameter 2: Start position of first half
	 * Parameter 3: mid point of the list passed in merge function
	 * Parameter 4: end position of list passed into the function. 
	 */
	private static <T extends Comparable<? super T>> void merge(SinglyList<T> myList,int start,int midPoint,int end)
	{
		SinglyList<T> L = myList.copy(start, midPoint); //Function implemented in my custom SinglyList class
		SinglyList<T> R = myList.copy(midPoint+1, end);
		Node<T> tempL = L.start;
		Node<T> tempR = R.start;
		for(int i=start;i<=end;i++)
		{
			if(tempL == null && tempR != null)
			{
				myList.updateAtPos(tempR.getValue(), i); //Function implemented in my custom SinglyList class
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

	public static void main(String[] args) {
		SinglyList<Integer> testList = new SinglyList<Integer>();
		testList.insertAfterEnd(12);
		testList.insertAfterEnd(5);
		testList.insertAfterEnd(34);
		testList.insertAfterEnd(2);
		testList.insertAfterEnd(11);
		MergeSortLists.mergeSort(testList, 0, 4);
		testList.display();
		SinglyList<String> testStringList = new SinglyList<String>();
		testStringList.insertAfterEnd("abc");
		testStringList.insertAfterEnd("xyz");
		testStringList.insertAfterEnd("def");
		testStringList.insertAfterEnd("ijkl");
		testStringList.insertAfterEnd("rst");
		testStringList.insertAfterEnd("mnp");
		MergeSortLists.mergeSort(testStringList, 0, 5);
		testStringList.display();
	}

}
