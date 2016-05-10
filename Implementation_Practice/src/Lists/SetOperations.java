package Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Plawan on 5/3/16.
 * Union, Intersection, Set Difference
 */
public class SetOperations {

    public static<T extends Comparable<? super T>> void intersection(List<T> l1, List<T> l2,List<T> outList)
    {
        Collections.sort(l1);
        Collections.sort(l2);
        int i=0,j=0;
        while(i<l1.size() && j <l2.size())
        {
            if(l1.get(i).compareTo(l2.get(j)) < 0)
                i++;
            else if(l1.get(i).compareTo(l2.get(j)) > 0)
                j++;
            else {
                outList.add(l1.get(i));
                i++;
                j++;
            }
        }
    }

    public static<T extends Comparable<? super T>> void union(List<T> l1, List<T> l2,List<T> outList)
    {
        Collections.sort(l1);
        Collections.sort(l2);
        int i=0,j=0;
        while(i<l1.size() && j<l2.size())
        {
            if(l1.get(i).compareTo(l2.get(j)) < 0)
            {
                outList.add(l1.get(i));
                i++;
            }
            else if(l1.get(i).compareTo(l2.get(j)) > 0)
            {
                outList.add(l2.get(j));
                j++;
            }
            else
            {
                outList.add(l1.get(i));
                i++; j++;
            }
        }
    }

    public static<T extends Comparable<? super T>> void difference(List<T> l1, List<T> l2,List<T> outList)
    {
        Collections.sort(l1);
        Collections.sort(l2);
        int i=0,j=0;
        while(i<l1.size() && j<l2.size())
        {
            if(l1.get(i).compareTo(l2.get(j)) < 0)
            {
                outList.add(l1.get(i));
                i++;
            }
            else if(l1.get(i).compareTo(l2.get(j)) > 0)
            {
                j++;
            }
            else
            {
                i++;j++;
            }
        }
    }

    public static<T extends Comparable<? super T>> void printList(List<T> printList)
    {
        for(T item : printList)
            System.out.print(item + " ");
        System.out.println();
    }

    public static void main(String[] args)
    {
        ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(2,44,5,12,42,78));
        ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(32,44,12,33,78,99));
        ArrayList<Integer> outList = new ArrayList<>();
        intersection(l1,l2,outList);
        printList(outList);
        outList.clear();
        union(l1,l2,outList);
        printList(outList);
        outList.clear();
        difference(l1,l2,outList);
        printList(outList);
    }
}
