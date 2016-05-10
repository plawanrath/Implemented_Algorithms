/**
 * @author plawanrath
 * This class demonstrates union, intersection and difference on Lists.
 */

import java.util.*;

public class ListSets {

	private static <T extends Comparable<? super T>> T next(Iterator<T> it)
	{
		return it.hasNext() ? it.next() : null;
	}
	
	/*
	 * This function handles intersection of Lists. 
	 * Parameter 1 : Input list 1
	 * Parameter 2 : Input list 2
	 * Parameter 3 : Output List 
	 * The function will store common elements of List 1 and List 2 in outList.
	 */
	public static<T extends Comparable<? super T>> void intersect(List<T> l1, List<T> l2, List<T> outList) 
	{		
		Iterator<T> it1 = l1.iterator();
		Iterator<T> it2 = l2.iterator();
		T x1 = next(it1);
		T x2 = next(it2);
		while(x1!= null && x2!=null)
		{
			if(x1.compareTo(x2) < 0)
				x1 = next(it1);
			else if(x1.compareTo(x2) > 0)
				x2 = next(it2);
			else
			{
				outList.add(x1);
				x1 = next(it1);
				x2 = next(it2);
			}
		}
	}

	/*
	 * This function handles union of Lists. 
	 * Parameter 1 : Input list 1
	 * Parameter 2 : Input list 2
	 * Parameter 3 : Output List 
	 * The function will store all elements of List 1 and List 2 in outList.
	 */
	public static<T extends Comparable<? super T>> void union(List<T> l1, List<T> l2, List<T> outList) 
	{
		Iterator<T> it1 = l1.iterator();
		Iterator<T> it2 = l2.iterator();
		T x1 = next(it1);
		T x2 = next(it2);
		while(x1!= null && x2!=null)
		{
			if(x1.compareTo(x2) != 0)
			{
				outList.add(x1);
				outList.add(x2);
			}
			x1 = next(it1);
			x2 = next(it2);
		}
	}

	/*
	 * This function handles difference of Lists. 
	 * Parameter 1 : Input list 1
	 * Parameter 2 : Input list 2
	 * Parameter 3 : Output List 
	 * The function will store all elements that are in List 1 but not in List 2 in outList.
	 */
	public static<T extends Comparable<? super T>> void difference(List<T> l1, List<T> l2, List<T> outList) 
	{
		Iterator<T> it1 = l1.iterator();
		Iterator<T> it2 = l2.iterator();
		T x1 = next(it1);
		T x2 = next(it2);
		while(x1!= null && x2!=null)
		{
			if(x1.compareTo(x2) < 0)
			{
				outList.add(x1);
				x1 = next(it1);
			}
			else if(x1.compareTo(x2) > 0)
			{
				outList.add(x1);
				x2 = next(it2);
			}
			else
			{
				x1 = next(it1);
				x2 = next(it2);
			}
		}
		while(x1!= null)
		{
			outList.add(x1);
			x1 = next(it1);
		}
	}
	
	/*
	 * This function displays the list stored in Parameter 1.
	 */
	public static<T extends Comparable<? super T>> void displayList(List<T> res)
	{
		Iterator<T> it = res.iterator();
		while(it.hasNext())
			System.out.print(it.next() + " ");
		System.out.println();		
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> al1 = new ArrayList<>(Arrays.asList(2,4,6,8,10));
		ArrayList<Integer> al2 = new ArrayList<>(Arrays.asList(6,8));
		ArrayList<Integer> res = new ArrayList<>();
		LinkedList<String> sl1 = new LinkedList<>(Arrays.asList("abc","def","xyz","tuv"));
		LinkedList<String> sl2 = new LinkedList<>(Arrays.asList("xyz","tuv"));
		LinkedList<String> res2 = new LinkedList<>();
		ListSets.intersect(sl1, sl2, res2);
		ListSets.displayList(res2);
		ListSets.intersect(al1, al2, res);
		ListSets.displayList(res);
		LinkedList<String> unionList = new LinkedList<>();
		ListSets.union(sl1, sl2, unionList);
		ListSets.displayList(unionList);
		LinkedList<String> diffListString = new LinkedList<>();
		ListSets.difference(sl1, sl2, diffListString);
		ListSets.displayList(diffListString);
		ArrayList<Integer> resDiffInt = new ArrayList<>();
		ListSets.difference(al1, al2, resDiffInt);
		ListSets.displayList(resDiffInt);
	}

}
