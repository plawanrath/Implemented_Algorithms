/*
 * @author Plawan Kumar Rath
 * The function findDistinct written in this class, finds all distinct elements in the array passed
 * as parameter and moves then to the front of the array and returns the number of distinct elements
 * 
 * The function assumes that the Type T array passed implements hashCode and equals
 */
import java.util.HashSet;
import java.util.Scanner;

public class DistinctElements {

	/*
	 * Expects an array of Type T where T implements hashCode and equals
	 * @param 1: An array of type T
	 * Returns the number of distinct elements
	 */
	public static <T> int findDistinct(T[] arr)
	{
		HashSet<T> elementSet = new HashSet<T>(); //HashSet only stores distinct elements when elements are repeated
		for(T element : arr)
			elementSet.add(element);
		int k=0;
		for(T value : elementSet)
			arr[k++] = value;
		return k;
	}
	
	public static void main(String[] args) {
		Scanner input = null;
		System.out.println("Please enter size of array");
		input = new Scanner(System.in);
		int length = input.nextInt();
		Integer[] test = new Integer[length];
		for(int i=0;i<length;i++)
			test[i] = input.nextInt();
			
		System.out.println(findDistinct(test));
		input.close();
	}

}
