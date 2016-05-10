package Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Plawan on 5/3/16.
 * PQ sort
 */
public class PQSort {

    public static <T extends Comparable<T>> void pqSort(List<T> array) {

        PriorityQueue<T> elements = new PriorityQueue<>();
        for(T item : array)
            elements.add(item);
        array.clear();
        while(!elements.isEmpty())
            array.add(elements.poll());
    }

    public static void main(String[] args)
    {
        Integer[] vals = {1,15,2,16,33,23,72,9};
        ArrayList<Integer> test = new ArrayList<>(Arrays.asList(vals));
        pqSort(test);
        System.out.println();
    }
}
