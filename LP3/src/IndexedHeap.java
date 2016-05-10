//=============================================================================================================================================================================================//
/*
	 *  @author: 			-Drishti Mittal
	 *  @reviewer:			-Drishti Mittal, Partha De
 */
//=============================================================================================================================================================================================//


import java.util.Comparator;

public class IndexedHeap<T extends Index> extends BinaryHeap<T> {
  
    /** Build a priority queue with a given array q */
    IndexedHeap(T[] q, Comparator<T> comp) {
	super(q, comp);
    }

    /** Create an empty priority queue of given maximum size */
    IndexedHeap(int n, Comparator<T> comp) {
	super(n, comp);
	}

    /** restore heap order property after the priority of x has decreased */
    void decreaseKey(T x) {
	percolateUp(((Index) x).getIndex());
    }
    
    @Override
	public void assign(int i, T x){
    	x.putIndex(i);
    	super.assign(i, x);
		
	}
}
