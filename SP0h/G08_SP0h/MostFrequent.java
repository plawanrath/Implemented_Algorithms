/*
 * @author Girish Tejwani
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class MostFrequent {

	/*
	 * Function takes as parameter an array of integers, and returns an integer that is most frequent in the array
	 * This function uses Array sort 
	 */
	 public static int mostFrequent(int[] arr){

		if (arr == null || arr.length == 0)
			return 0;

		Arrays.sort(arr);
		//display(arr);

		int current = arr[0];
		int maxCount = 0;
		int count = 0;
		int value = current;
		for (int i : arr) {
			if (i == current) {
				count++;
			}
			else {
				count = 1;
			}
		
			if (count > maxCount) {
				maxCount = count;
				value = i;
			}
				
			current = i;
		}
		return value;
	}
	 
	 /*
	  * Function takes as parameter an array of integers, and returns an integer that is most frequent in the array
	  * This function uses HashMap
	  */
	 public static int mostFrequentMap(int[] arr) {
	        HashMap<Integer, Integer> countMap = new HashMap<Integer, Integer>();
	        int len = arr.length;
	        for(int i=0; i < len; i++){
	            if(countMap.containsKey(arr[i])){
	                countMap.put(arr[i], countMap.get(arr[i]) + 1);
	            }else{
	                countMap.put(arr[i], 1);
	            }
	        }

	        // now that we have the map, just loop over it and keep a track of the biggest value yet

	        int result = arr[0];
	        int tempCount = 0;
	        for(Integer key: countMap.keySet()){
	            int tempValue = countMap.get(key); //to make sure that only one access call is done
	            if(tempValue > tempCount){
	                tempCount = tempValue;
	                result = key;
	            }
	        }

	        return result;
	    }

	/* Swap function to swap the data */

	public static <T extends Comparable<T>> void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}

	/* shuffle function to shuffle the data in the array */

	public static <T extends Comparable<T>> void shuffle(int[] A, int from, int to) {
		int n = to - from + 1;
		Random rand = new Random();
		for (int i = 1; i < n; i++) {
			int j = rand.nextInt(i);
			swap(A, i + from, j + from);
		}
	}

	public static <T extends Comparable<T>> void display(int[] array) {
		System.out.print("Array : ");
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();

	}
	
	public static void main(String[] args) throws Exception {

		int n = 10000000;
		int[] array = new int[n];
		Random rand = new Random();
		for(int i=0;i<n;i++){
			array[i] = rand.nextInt(n);
		}
		
		shuffle(array,0,n-1);
		Timer timer = new Timer();
		System.out.println("O(nlogn) Algorithm Statistics :");
		timer.start();
		System.out.println("Most Frequent Element = " + mostFrequent(array));
		timer.end();
		System.out.println(timer);
		System.out.println("*********************************************");
		System.out.println("O(n) Algorithm Statistics using Hashmap :");
		timer.start();
		System.out.println("Most Frequent Element = " + mostFrequentMap(array));
		timer.end();
		System.out.println(timer);
	}


}
