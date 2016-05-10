import java.util.Arrays;
import java.util.Comparator;

public class BinaryHeap<T extends Comparable<T>> implements PQ<T> {
    T[] pq;
    Comparator<T> c;
    int size;
    /** Build a priority queue with a given array q */
    BinaryHeap(T[] q, Comparator<T> comp) {
	pq = q;
	c = comp;
	buildHeap();
    }

    /** Create an empty priority queue of given maximum size */

	@SuppressWarnings("unchecked")
	BinaryHeap(int n, Comparator<T> comp) {
    	size = 0;
    	c = comp;
    	pq = (T[])new Comparable[n+1];    	
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

    public void add(T x) {
    	 if(size == pq.length - 1)
    		 pq = this.resize();	  
         // place element into heap at bottom
         size++;
         int index = size;
         pq[index] = x;
         
         percolateUp(index);
    }

    public T remove() { 
    	// what do want return?
    	T result = peek();
    	
    	// get rid of the last leaf/decrement
    	pq[1] = pq[size];
    	pq[size] = null;
    	size--;
    	
    	percolateDown(1);
    	
    	return result;
    }

    public T peek() { 
    	   if (this.isEmpty()) {
               throw new IllegalStateException();
           }
           
           return pq[1];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }

    /** pq[i] may violate heap order with parent */
    void percolateUp(int i) { 
    	 i = this.size;
         
         while (hasParent(i)
                 && (parent(i).compareTo(pq[i]) > 0)) {
             // parent/child are out of order; swap them
             swap(i, parentIndex(i));
             i = parentIndex(i);
         }        
    }

    /** pq[i] may violate heap order with children */
    void percolateDown(int i) {
    	 int index = i;
         
         // bubble down
         while (hasLeftChild(index)) {
             // which of my children is smaller?
             int smallerChild = leftIndex(index);
             
             // bubble with the smaller child, if I have a smaller child
             if (hasRightChild(index)
                 && pq[leftIndex(index)].compareTo(pq[rightIndex(index)]) > 0) {
                 smallerChild = rightIndex(index);
             } 
             
             if (pq[index].compareTo(pq[smallerChild]) > 0) {
                 swap(index, smallerChild);
             } else {
                 // otherwise break
                 break;
             }
             
             // make sure to update loop counter/index of where last el is put
             index = smallerChild;
         }        
    }

    /** Create a heap.  Precondition: none. */
    void buildHeap() {
    	for (int k = size/2; k > 0; k--)
        {
    		percolateDown(k);
        }
    }
    
    protected boolean hasParent(int i) {
        return i > 1;
    }
    
    
    protected int leftIndex(int i) {
        return i * 2;
    }
    
    
    protected int rightIndex(int i) {
        return i * 2 + 1;
    }
    
    
    protected boolean hasLeftChild(int i) {
        return leftIndex(i) <= size;
    }
    
    
    protected boolean hasRightChild(int i) {
        return rightIndex(i) <= size;
    }
    
    
    protected T parent(int i) {
        return pq[parentIndex(i)];
    }
    
    
    protected int parentIndex(int i) {
        return i / 2;
    }
    
    
    protected T[] resize() {
        return Arrays.copyOf(pq, pq.length * 2);
    }
    
    
    protected void swap(int index1, int index2) {
        T tmp = pq[index1];
        pq[index1] = pq[index2];
        pq[index2] = tmp;        
    }

    /* sort array A[1..n].  A[0] is not used. 
       Sorted order depends on comparator used to buid heap.
       min heap ==> descending order
       max heap ==> ascending order
     */
 	@SuppressWarnings("unchecked")
	public void heapSort(T[] A, Comparator<T> comp) { 
    	 size = A.length;
    	 c = comp;
         pq = (T[]) new Comparable[size+1];
         System.arraycopy(A, 0, pq, 1, size);
         buildHeap();

         for (int i = size; i > 0; i--)
         {
            T tmp = pq[i]; //move top item to the end of the heap array
            pq[i] = pq[1];
            pq[1] = tmp;
            size--;
            percolateDown(1);
         }
         for(int k = 0; k < pq.length-1; k++)
            A[k] = pq[pq.length - 1 - k];
    }
    
    public static void main(String[] args)
    {
       BinaryHeap<String> h = new BinaryHeap<String>(10, null);

       h.insert("p");
       h.insert("r");
       h.insert("i");
       h.insert("o");
       System.out.println(h);
       h.deleteMin();
       System.out.println(h);


       BinaryHeap<Integer> tmp = new BinaryHeap<Integer>(10, null);
       Integer[] a = {4,7,7,7,5,0,2,3,5,1};
       tmp.heapSort(a,null);
       System.out.println(Arrays.toString(a));
    }
 
}