/*
 * @author Plawan
 * This class implements 2 versions of the selection problem.
 * first implementation is using the partition algorithm and the second implementation is using minHeap
 */
import java.io.File;
import java.util.Scanner;

public class  SelectionProblem {

	/*
	 * Function to swap 2 values.
	 * Param 1: array containing values
	 * Param 2: first value
	 * Param 3: second value
	 * 
	 */
	public static <T extends Comparable<T>> void swap(T[] array,int left,int right)
	{
		T temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
	
	/*
	 * I implement the Lomuto-Partition which I refered from
	 * http://cs.stackexchange.com/questions/11458/quicksort-partitioning-hoare-vs-lomuto
	 * Param 1: array to be partitioned
	 * Param 2: left end index of the array
	 * Param 3: right end index of the array
	 * Returns the pivot element such that elements to its left are smaller and elements to its right are greater.
	 */
	public static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
		T pivot = array[right];
		int i = left - 1;
		for(int j=left;j<right;j++)
		{
			if(array[j].compareTo(pivot) <= 0)
			{
				i++;
				swap(array,i,j);
			}
		}
		swap(array,i+1,right);
		return i+1;
	}
	
	/*
	 * Implements the select algorithm discussed in class using partition
	 * Param 1: array of values
	 * Param 2: start index of array
	 * Param 3: end index of array
	 * Param 4: number of largest numbers required
	 * The function returns a value q such that elements from q to r in the array are the k largest numbers.
	 */
	public static <T extends Comparable<T>> int select(T[] array,int p,int r,int k)
	{
		int q = partition(array,p,r);
		if((r-q) >= k)
			return select(array,q+1,r,k);
		else if((r-q+1) == k)
			return q;
		else
			return select(array,p,q-1,k-(r-q+1));
	}

	/*
	 * Implements select algorithm using minHeap.
	 * Param 1: array of values
	 * Param 2: number of largest numbers required
	 * The function returns a BinaryHeap of size k containing the k largest values.
	 * 
	 */
	public static <T extends Comparable<T>> BinaryHeap<T> select(T[] array,int k)
	{
		BinaryHeap<T> pq = new BinaryHeap<T>(k,null);
		for(int i=0;i<array.length;i++)
		{
			if(i < k)
				pq.add(array[i]);
			else
			{
				T value = array[i];
				if(value.compareTo(pq.peek()) > 0)
				{
					pq.remove();
					pq.add(value);
				}
			}
		}
		return pq;
	}

	/*
	 * The main function expects 0,1 or 2 arguments. If 0 arguments are passed then the 
	 * program will accept an array in command line and give 3 largest elements from the 
	 * array. If 1 argument is passed, that 1 argument should be a file name for the file
	 * containing array values, and the program will give 3 largest values from file. For
	 * a value of k other than 3, 2 arguments are required, first should be the value of k and 
	 * second should be the file name.
	 */
	public static void main(String[] args) throws Exception {
		Scanner input = null;
		int k = 3;
		if(args.length < 1)
		{
			System.out.println("Running with k = 3 by default! Pass value of k in command line.");
			input = new Scanner(System.in);
		}
		else if(args.length == 1)
		{
			System.out.println("Running with k = 3 by default! Pass value of k in command line.");
			
			File inputFile = new File(args[0]);
			input = new Scanner(inputFile);
		}
		else if(args.length == 2)
		{
			k = Integer.parseInt(args[0]);
			File inputFile = new File(args[1]);
			input = new Scanner(inputFile);			
		}
		else
		{
			System.out.println("Wrong number of arguments!!");
			System.exit(-1);
		}
		Timer timer = new Timer();
		int array_length = input.nextInt();
		Integer[] test = new Integer[array_length];
		for(int i=0;i<array_length;i++)
			test[i] = input.nextInt();

		System.out.println("Time taken by selection using partition");
		timer.start();
		int q = select(test,0,array_length-1,k);
		timer.end();

		for(int i =q;i<test.length;i++)
			System.out.print(test[i] + " ");
		System.out.println();
		System.out.println(timer);
		
		System.out.println("Time taken by selection using minHeap");
		timer.start();
		BinaryHeap<Integer> res = select(test,k);
		timer.end();
		
		while(!res.isEmpty())
			System.out.print(res.remove() + " ");
		System.out.println();
		System.out.println(timer);
		
//		Integer[] test = {12,4,24,76,45,21,77,65,11,15,98};
//		String[] testString = {"abc","xyz","def","nmo","ghi","pqr","jkl"};
//		int k = 3;
//		int q = select(test,0,test.length-1,k);
//		for(int i =q;i<test.length;i++)
//			System.out.print(test[i] + " ");
//		System.out.println();
//		int qS = select(testString,0,testString.length-1,k);
//		for(int i =qS;i<testString.length;i++)
//			System.out.print(testString[i] + " ");
//		System.out.println();		
		// TODO Auto-generated method stub

	}

}
