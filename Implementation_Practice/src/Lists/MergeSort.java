package Lists;

import java.util.Comparator;

/**
 * Created by Plawan on 5/3/16.
 * SP0
 */

public class MergeSort {

    //Without using extra memory
    private static <T extends Comparable<T>> void mergeSort ( T[] x, int first, int last ) {
        int mid, lt, rt;
        T tmp;

        if (first >= last) return;

        mid = (first + last) / 2;

        mergeSort(x, first, mid);
        mergeSort(x, mid + 1, last);

        lt = first;
        rt = mid + 1;
        // One extra check:  can we SKIP the merge?
        if (x[mid].compareTo(x[rt]) <= 0)
            return;

        while (lt <= mid && rt <= last) {
            // Select from left:  no change, just advance lt
            if (x[lt].compareTo(x[rt]) <= 0)
                lt++;
                // Select from right:  rotate [lt..rt] and correct
            else {
                tmp = x[rt];     // Will move to [lt]
                System.arraycopy(x, lt, x, lt + 1, rt - lt);
                x[lt] = tmp;
                // EVERYTHING has moved up by one
                lt++;
                mid++;
                rt++;
            }
        }
    }

    public static void mergeSort2(int[] arr,int low,int high)
    {
        if(low < high)
        {
            int mid = (low+high)/2;
            mergeSort2(arr,low,mid);
            mergeSort2(arr,mid+1,high);
            merge(arr,low,mid,high);
        }
    }

    public static void merge(int[] arr, int low, int mid, int high)
    {
        int n1 = mid-low+1;
        int n2 = high-mid;
        int[] firstH = new int[n1];
        int[] secondH = new int[n2];
        for(int i=0;i<n1;i++)
            firstH[i] = arr[low+i];
        for(int i=0;i<n2;i++)
            secondH[i] = arr[mid+1+i];
        int i = 0,j = 0;
        int index = low;
        while(i<n1 && j<n2)
        {
            if(firstH[i] <= secondH[j]) {
                arr[index] = firstH[i];
                i++;
            }
            else {
                arr[index] = secondH[j];
                j++;
            }
            index++;
        }
        while(i<n1) {
            arr[index] = firstH[i];
            index++;
            i++;
        }
        while(j<n2) {
            arr[index] = secondH[j];
            index++;
            j++;
        }
    }


    public static void main(String[] args)
    {
        Integer[] test = {1,15,2,22,35,9,72,16,2,11};
        mergeSort(test,0,test.length-1);
        System.out.println(test);
        int[] test2 = {1,15,2,22,35,9,72,16,2,11};
        mergeSort2(test2,0,test2.length-1);
        System.out.println();
    }
}
