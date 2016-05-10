/**
 * Created by Plawan on 4/28/16.
 * My implementation of Boyer Moore Algorithm
 */
import java.util.*;

public class BM {

    static int size = 26;

    /*
     * Apply the algorithm to search pattern in the string
     */
    public static int search(String str, String pattern) {
        int n = str.length();
        int m = pattern.length();
        str = str.toLowerCase();
        pattern = pattern.toLowerCase();

        //preprocess the pattern
        int[] items = new int[size];
        for (int i = 0; i < size; i++)
            items[i] = -1;
        char[] pchars = pattern.toCharArray();
        for(int i=0;i<pchars.length;i++)
        {
            int index = pchars[i] - 'a';
            items[index] = i;
        }

        //BM algorithm
        int patternStartIndex = -1;
        int i = 0;
        while (i <= (n - m)) {
            int j = m - 1;
            char[] schars = str.toCharArray();
            while (j >= 0 && pchars[j] == schars[i + j])
                j--;
            if (j < 0) {
                patternStartIndex = i;
                if((i + m) < n)
                    i  = i + m - items[schars[i + m] - 'a'];
                else
                    i++;
            }
            else {
                i = i + Math.max(1, j - items[schars[i + j] - 'a']);
            }
        }
        return patternStartIndex;
    }

    public static void main(String[] args) {


        String str="plawanrath";
        String pattern = "nra";
        System.out.println("Pattern found starting at index " + search(str,pattern));

    }

}
