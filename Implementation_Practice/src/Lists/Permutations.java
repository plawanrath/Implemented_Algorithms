package Lists;

/**
 * Created by Plawan on 5/9/16.
 */
import java.util.*;
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        int size = nums.length;
        nums = addSentinal(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        item.addAll(getInList(nums));
        result.add(item);
        while(true) {
            item.clear();
            int j=0,l=size;
            while(j < size) {
                if(nums[j] < nums[j+1])
                    j++;
                else
                    break;
            }
            j--;
            if(j==0)
                break;
            while(l > 0) {
                if(nums[j] < nums[l])
                    l--;
                else
                    break;
            }
            l++;
            int temp = nums[j];
            nums[j] = nums[l];
            nums[l] = temp;
            reverse(nums,j+1);
            item.addAll(getInList(nums));
            result.add(item);
        }
        return result;
    }

    public int[] addSentinal(int[] arr) {
        int[] newArr = new int[arr.length+1];
        newArr[0] = Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++)
            newArr[i+1] = arr[i];
        return newArr;
    }

    public List<Integer> getInList(int[] arr)
    {
        List<Integer> res = new ArrayList<>();
        for(int i=1;i<arr.length;i++)
            res.add(arr[i]);
        return res;
    }

    public void reverse(int[] arr, int start)
    {
        if(arr.length-1 > start)
        {
            int index = 0;
            while(index < start)
                index++;
            int end = arr.length-1;
            while(index <= end)
            {
                int temp = arr[index];
                arr[index] = arr[end];
                arr[end] = temp;
                index++;
                end--;
            }
        }
    }

    public static void main(String[] args) {
        Permutations p = new Permutations();
        int[] t = {1,2,3};
        List<List<Integer>> res =  p.permute(t);
        System.out.println();
    }
}