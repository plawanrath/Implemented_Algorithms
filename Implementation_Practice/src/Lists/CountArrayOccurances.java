package Lists;

import java.util.Arrays;

/**
 * Created by Plawan on 5/9/16.
 */
public class CountArrayOccurances {

    public static void countArrayOcc(int[] arr)
    {
        Arrays.sort(arr);
        int value = arr[0];
        int count = 1;
        for(int i=1;i<arr.length;i++)
        {
            if(arr[i] != value)
            {
                System.out.println(value + " " + count);
                value = arr[i];
                count = 1;
            }
            else
                count++;
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,5,7,1,2,4,6,8,9,12,32,4,12,6,2,2,7,12,32};
        countArrayOcc(arr);
    }
}
