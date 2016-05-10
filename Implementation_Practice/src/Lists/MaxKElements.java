package Lists;

import java.util.*;

/**
 * Created by Plawan on 5/9/16.
 */
public class MaxKElements {

    static class itemCount {
        int val;
        int count;

        itemCount(int v,int c)
        {
            val = v;
            count = c;
        }
    }

    static class customComparator implements Comparator<itemCount> {

        @Override
        public int compare(itemCount o1, itemCount o2) {
            if(o1.count > o2.count)
                return 1;
            else if(o1.count < o2.count)
                return -1;
            else
                return 0;
        }
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        Arrays.sort(nums);
        ArrayList<itemCount> itemF = new ArrayList<>();
        int item1 = nums[0];
        int count1 = 1;
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i] != item1)
            {
                itemCount ic = new itemCount(item1,count1);
                itemF.add(ic);
                item1 = nums[i];
                count1 = 1;
            }
            else
                count1++;
        }
        itemF.add(new itemCount(item1,count1));
        Collections.sort(itemF,new customComparator());
        List<Integer> res = new ArrayList<>();
        int index = itemF.size()-1;
        for(int i=0;i<k;i++)
        {
            res.add(itemF.get(index-i).val);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = {1,1,1,2,2,3};
        List<Integer> res = topKFrequent(test,2);
        List<itemCount> testic = new ArrayList<>();
        Collections.sort(testic,new customComparator());
        System.out.println();
    }
}
