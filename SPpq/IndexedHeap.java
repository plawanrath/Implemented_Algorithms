//Revised by Plawan to implement BinaryHeap and override its functions
//To set Index for objects of any class that implements that interface
// Ver 1.0:  Wed, Feb 3.  Initial description.
// Ver 1.1:  Thu, Feb 11.  Simplified Index interface

import java.util.Comparator;
import java.util.InputMismatchException;

public class IndexedHeap<T extends Comparable<T>> extends BinaryHeap<T> {
    /** Build a priority queue with a given array q */
    IndexedHeap(T[] q, Comparator<T> comp) {
    	super(q, comp);
    	if(!(q instanceof Index[]))
    		throw new InputMismatchException();
    }

    /** Create an empty priority queue of given maximum size */
    IndexedHeap(int n, Comparator<T> comp) {
	super(n, comp);
    }

    /** restore heap order property after the priority of x has decreased */
    void decreaseKey(T x) {
    	if(!(x instanceof Index)) //We need to make sure that the generic type passed, has indexes. We do that by checking if it implements the Index interface
    		throw new InputMismatchException();
    	percolateUp(((Index) x).getIndex());
    }
    
    /*
     * This overrides the add function in BinaryHeap class and updates its heap index in the item 
     * @see BinaryHeap#add(java.lang.Comparable)
     */
    public void add(T x)
    {
    	if(!(x instanceof Index))
    		throw new InputMismatchException();
    	if(size == pq.length - 1)
    		pq = resize();
    	size++;
    	int index = size;
    	pq[index] = x;
    	if(x instanceof Index)
    		((Index) x).putIndex(index);
    	percolateUp(index);
    }
    
    /*
     * I override this function to update index of items whenever swap happens.
     * This is pivotal because this means, no matter what we do, we add or remove
     * items, whenever any kind of percolation happens, for an indexed heap while
     * swapping elements, their indexes will also be swapped.
     * @see BinaryHeap#swap(int, int)
     */
    protected void swap(int index1,int index2)
    {
    	super.swap(index1, index2);
    	int indexOf1 = ((Index) pq[index1]).getIndex();
    	((Index) pq[index1]).putIndex(((Index) pq[index2]).getIndex());
    	((Index) pq[index2]).putIndex(indexOf1);
    }
}
