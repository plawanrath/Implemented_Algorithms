import java.util.*;

/**
 * Created by Plawan on 4/24/16.
 */
public class LongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        int count = 0;
        HashMap<Integer,ArrayList<Character>> vals = new HashMap<>();
        char[] cArray = s.toCharArray();
        int hIndex = 0;
        while(hIndex < cArray.length) {
            ArrayList<Character> substring = new ArrayList<>();
            int index = hIndex;
            while (index < cArray.length && !substring.contains(cArray[index])) {
                substring.add(cArray[index]);
                index++;
            }
            vals.put(hIndex, substring);
            hIndex++;
        }
        for(Map.Entry<Integer,ArrayList<Character>> item : vals.entrySet())
        {
            if(item.getValue().size() > count)
                count = item.getValue().size();
        }
        return count;
    }

    public static void main(String[] args)
    {
        int x = lengthOfLongestSubstring("dvdf");
        System.out.println(x);
    }
}
