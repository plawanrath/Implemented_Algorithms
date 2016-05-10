//=============================================================================================================================================================================================//
/*
	 *  @author: 			-Drishti Mittal
	 *  @reviewer:			-Drishti Mittal, Partha De
 */
//=============================================================================================================================================================================================//


import java.util.*;

public class BinaryHeap<T> implements PQ<T> {
	T[] pq;
	Comparator<T> c;
	int size;

	/** Build a priority queue with a given array q */
	@SuppressWarnings("unchecked")
	BinaryHeap(T[] q, Comparator<T> comp) {

		pq = (T[]) new Object[q.length * 2];
		pq[0] = q[0];
		for (int i = 1; i <= q.length; i++) {
			assign(i,q[i-1]);
			//pq[i] = q[i - 1];
		}

		c = comp;
		size = q.length;
		buildHeap();
	}

	/** Create an empty priority queue of given maximum size */
	@SuppressWarnings("unchecked")
	BinaryHeap(int n, Comparator<T> comp) {
		pq = (T[]) new Object[n];
		c=comp;
		size = 0;
	}

	@SuppressWarnings("unchecked")
	public int compare(T o1, T o2) {
		if (c != null)
			return c.compare(o1, o2);
		else
			return ((Comparable<T>) o1).compareTo(o2);
	}

	public void insert(T x) {
		add(x);
	}

	public T deleteMin() {
		return remove();
	}

	public T min() {
		return peek();
	}

	public void resize() {

		Arrays.copyOf(pq, pq.length * 2);
	}

	public void assign(int i, T x){
		pq[i] = x;	
	}
	
	public void add(T x) {
		if (size >= pq.length - 1) {
			resize();
		}	
		size++;
		assign(size,x);
		percolateUp(size);
	}
	
	public T remove() {
		T min = pq[1];
		assign(1,pq[size--]);
		percolateDown(1);
		return min;
	}

	public T peek() {
		if (size == 0)
			return null;
		return pq[1];
	}

	void percolateUp(int i) {
		pq[0] = pq[i];
		while (compare(pq[i / 2], pq[0]) > 0) {
			assign(i,pq[i/2]);
			i = i / 2;
		}
		assign(i,pq[0]);
	}

	void percolateDown(int i) {
		T x = pq[i];
		int schild;
		while (2 * i <= size) {
			if (2 * i == size) { // one child
				if (compare(x, pq[size]) > 0) {
					assign(i,pq[size]);
					i = size;
				} else
					break;
			} else {
				if (compare(pq[2 * i], pq[2 * i + 1]) <= 0)
					schild = 2 * i;
				else
					schild = 2 * i + 1;
				if (compare(x, pq[schild]) > 0) {
					assign(i,pq[schild]);
					i = schild;
				} else
					break;
			}
		}
		assign(i,x);
	}
	
	void buildHeap() {
		for (int i = size / 2; i > 0; i--) {
			percolateDown(i);
		}
	}

	private void swap(int index1, int index2) {
		T tmp = pq[index1];
		assign(index1,pq[index2]);
		assign(index2,tmp);
	}

	public void display() {
		for (int i = 1; i <= size; i++) {
			System.out.print(pq[i] + " ");
		}
		System.out.println();
	}

	public static <T> void heapSort(T[] A, Comparator<T> comp) {
		BinaryHeap<T> b = new BinaryHeap<T>(A, comp);
		b.buildHeap();
	
		for (int i = b.size; i > 0; i--) {
			b.swap(1, i);
			b.size--;
			b.percolateDown(1);
		}

	}
}
