/**
 * Class to represent a vertex of a graph
 * 
 *
 */

import java.util.*;

public class Vertex implements Comparator<Vertex> {
    public int name; // name of the vertex
    public boolean seen; // flag to check if the vertex has already been visited
    public Vertex parent; // parent of the vertex
    public int distance; // distance to the vertex from the source vertex
    public List<Edge> Adj, revAdj; // adjacency list; use LinkedList or ArrayList
    public int label; //Label for Weighted Matching Algorithm
    public boolean isInfinity; //check if the Vertex distance is infinity
	public int outer; // Set to -1 when uninitialized, 0 if inner and 1 if outer.
    public Vertex mate; //The vertex that will be the mate for that Vertex match

    /**
     * Constructor for the vertex
     * 
     * @param n
     *            : int - name of the vertex
     */
    Vertex(int n) {
		label=-1;
		name = n;
		seen = false;
		parent = null;
		distance=Integer.MAX_VALUE;
		Adj = new ArrayList<Edge>();
		revAdj = new ArrayList<Edge>();   /* only for directed graphs */
		isInfinity = true;
		outer = -1;
        mate = null;
    }

    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
	return Integer.toString(name);
    }

//	@Override
//	public void putIndex(int index) {
//		this.index=index;
//	}
//
//	@Override
//	public int getIndex() {
//		return index;
//	}

	@Override
	public int compare(Vertex o1, Vertex o2) {
		return o1.distance-o2.distance;
	}
}