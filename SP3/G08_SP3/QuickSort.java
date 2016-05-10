import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class QuickSort {
	
	/* Swap function to swap the data */
	public static <T extends Comparable<T>> void swap(T[] array,int left,int right)
	{
		T temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
	
	/* Quick sort partition, considering the pivot as extreme right element */
	
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
	
	/* Quick sort */
	
	public static <T extends Comparable<T>> void quickSort(T[] arr, int left, int right) {
	      int index = partition( arr, left, right);
	      if (left < index - 1)
	            quickSort(arr, left, index - 1);
	      if (index < right)
	            quickSort(arr, index, right);
	}
	
	public static <T extends Comparable<T>> void display(T[] array){
		System.out.print("Sorted Array : " );
		for (T i: array)
		{
			System.out.print(i + " ");
		}
		System.out.println();
		
	}
	/* Dual pivot Quick sort, considering two pivots as extreme left and extreme right elements */
	
	public static <T extends Comparable<T>> void dualPivotQuickSort(T[] arr, int left, int right) {
		  
		if (right <= left) return;

	        T pivot1 = arr[left];
	        T pivot2 = arr[right];

	        // Swap pivots 
	        if (pivot1.compareTo(pivot2) > 0){
	            swap(arr, left, right);
	            pivot1 = arr[left];
	            pivot2 = arr[right];
	        }
	        
	        // if pivots are same, increment and look for elements which is less than other and consider it as pivot
	        else if (pivot1 == pivot2){
	            while (pivot1 == pivot2 && left < right){
	                left++;
	                pivot1 = arr[left];
	            }
	        }

	        int i=left+1;
	        int lt=left+1;
	        int gt=right-1;

	        while (i<=gt){

	            if (arr[i].compareTo(pivot1) < 0){
	                swap(arr, i++, lt++);
	            }
	            else if (pivot2.compareTo(arr[i]) < 0 ){
	                swap(arr, i, gt--);
	            }
	            else{
	                i++;
	            }

	        }

	        swap(arr, left, --lt);
	        swap(arr, right, ++gt);
	        
	        dualPivotQuickSort(arr, left, lt-1);
	        dualPivotQuickSort(arr, lt+1, gt-1);
	        dualPivotQuickSort(arr, gt+1, right);
	}
	
	/* shuffle function to shuffle the data in the array */
	
	public static<T extends Comparable<T>> void shuffle(T[] A, int from, int to) {
		int n = to - from  + 1;
		Random rand = new Random();
		for(int i=1; i<n; i++) {
		    int j = rand.nextInt(i);
		    swap(A, i+from, j+from);
		}
	}
	
	public static void main(String[] args) throws Exception {
	//	Integer[] test = {12,4,24,76,45,21,77,65,11,15,98};
		//String[] testString = {"abc","xyz","def","nmo","ghi","pqr","jkl"};
		// TODO Auto-generated method stub
		Scanner input = null;
		if(args.length < 1)
		{
			input = new Scanner(System.in);
		}
		else if(args.length == 1)
		{			
			File inputFile = new File(args[0]);
			input = new Scanner(inputFile);
		}
//		int n = 200000;
		int n = input.nextInt();
		Integer[] array = new Integer[n];

		for(int i=0;i<n;i++){
			array[i] = n-i;
		}
		
		shuffle(array,0,n-1);
		
		/*Timer timer = new Timer();
		timer.start();
		dualPivotQuickSort(arr,0,n-1);
		timer.end();
		//display(test);
		timer.start();
		quickSort(testString,0,6);
		timer.end();
		//display(testString);
*/		
		Timer timer = new Timer();
		System.out.println("Quick Sort Statistics ");
		timer.start();
		quickSort(array,0,n-1);
		timer.end();
		System.out.println(timer);
		System.out.println("Dual Pivot Quick Sort Statistics");
		timer.start();
		QuickSort.dualPivotQuickSort(array, 0, n-1);
		timer.end();
		System.out.println(timer);
		
	}

}
