import java.util.Scanner;

/*
 * @author Plawan
 */
public class MST2 {
	static final int Infinity = Integer.MAX_VALUE;
	
	/*
	 *This function implements the Prim's algorithm as discussed in the class using Indexed Heap. 
	 */
	
	   static int PrimMST(Graph g)
	    {
	        int wmst = 0;
	        for(Vertex v : g)
	        {
	        	v.seen = false;
	        	v.parent = null;
	        	v.distance = Infinity;
	        }
	        Vertex src = g.verts.get(1);
	        src.distance = 0;
	        IndexedHeap<Vertex> IndexedPQ = new IndexedHeap<Vertex>(100, null); //Taking 100 as a start then it can be resized
	        //I also implement the comparator in the vertex class which seems cleaner than implementing comparator here.
	        IndexedPQ.insert(src);
	        for(Vertex v : g)
	        	IndexedPQ.insert(v);
	        while(!IndexedPQ.isEmpty()) {
	        	Vertex u = IndexedPQ.remove();
	        	u.seen = true;
	        	wmst += u.distance;
	        	for(Edge e : u.Adj)
	        	{
	        		Vertex v = e.otherEnd(u);
	        		if(!v.seen && e.Weight < v.distance)
	        		{
	        			v.distance = e.Weight;
	        			v.parent = u;
	        			IndexedPQ.percolateUp(v.getIndex());
	        		}
	        	}
	        }
	        return wmst;
	    }

	    public static void main(String[] args) 
	    {
	        Scanner in = new Scanner(System.in);
	        Graph g = Graph.readGraph(in, false);
	        System.out.println(PrimMST(g));
	    }
}
