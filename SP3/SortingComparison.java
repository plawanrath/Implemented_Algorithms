import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class SortingComparison {

	/* Insertion sort as provided */
	
	public static<T extends Comparable<T>> void insertionSort(T[] A, int p, int r) {
		  
    	for(int i=p, j=i; i<r; j=++i) {
            T ai = A[i+1];
            while(ai.compareTo(A[j]) < 0) {
                A[j+1] = A[j];
                if (j-- == p) {
                    break;
                }
            }
            A[j+1] = ai;
        }
    }

	/* Merge sort as discussed in the class, when it return 1 means the array is 
	 * sorted in temp and we need to copy it back to original one */
	
	public static <T extends Comparable<T>> void mergeSort(T[] array, T[] tmp) {
	
		if(mergeSort(array,tmp,0,array.length-1)==1)
			System.arraycopy(tmp, 0, array, 0, tmp.length);
	
	}
	
	/* Merge sort as discussed in the class, when it return 1 means the array is stored 
	 * in original array and when it return 0 means the array is stored in temp */
	
	public static <T extends Comparable<T>> int mergeSort(T[] array, T[] tmp, int p, int r) {
		
		int n = r - p + 1;
		if (p < r) {
			if( n <= 11){ //threshold
				insertionSort(array,p,r);
				return 0;
			}
			int mid = (p + r) / 2;
			int t1 = mergeSort(array, tmp, p, mid);
			int t2 = mergeSort(array, tmp, mid + 1, r);
			
			if(t1-t2 == 0){
				merge(array, tmp, p, mid, r);
				return 1;
			}
			else{
				merge(tmp, array, p, mid, r);
				return 0;
			}
			
		}
		return 0;

	}

	/* Merge Function */
	
	public static <T extends Comparable<T>> void merge(T[] array,T[] tmp, int p,int q, int r) {

		int i = p;
		int j = q + 1;
		int k = 0;

		while (i <= q && j <= r) {
			if (array[i].compareTo(array[j]) < 0) {
				tmp[k++] = array[i++];
			} else {
				tmp[k++] = array[j++];
			}
		}

		while (i <= q) {
			tmp[k++] = array[i++];
		}
		while (j <= r) {
			tmp[k++] = array[j++];
		}

		for (i = 0; i < k; i++) {
			array[i + p] = tmp[i];
		}

	}
	
	/* Swap function to swap the data */
	
	public static <T extends Comparable<T>> void swap(T[] array,int left,int right)
	{
		T temp = array[left];
		array[left] = array[right];
		array[right] = temp;
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
	
	public static <T extends Comparable<T>> void display(T[] array){
		System.out.print("Sorted Array : " );
		for (T i: array)
		{
			System.out.print(i + " ");
		}
		System.out.println();
		
	}

	public static void main(String[] args) throws Exception {

//		int n = 29580;
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
		int n = input.nextInt();
		Integer[] array = new Integer[n];
		Integer[] temp = new Integer[n];

		for(int i=0;i<n;i++){
			array[i] = n-i;
		}
		
		shuffle(array,0,n-1);

		Timer timer = new Timer();
		System.out.println("Merge Sort Statistics ");
		timer.start();
		mergeSort(array,temp);
		timer.end();
		System.out.println(timer);
		System.out.println("Dual Pivot Quick Sort Statistics");
		timer.start();
		QuickSort.dualPivotQuickSort(array, 0, n-1);
		timer.end();
		System.out.println(timer);
	}

}