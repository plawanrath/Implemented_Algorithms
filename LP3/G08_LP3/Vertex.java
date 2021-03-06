/**
 * Class to represent a vertex of a graph
 * 
 *
 */

import java.util.*;

public class Vertex implements Comparator<Vertex>, Index {
    public int name; // name of the vertex
    public boolean seen; // flag to check if the vertex has already been visited
    public Vertex parent; // parent of the vertex
    public int distance; // distance to the vertex from the source vertex
    public List<Edge> Adj, revAdj; // adjacency list; use LinkedList or ArrayList
    public int index; //Used for IndexedHeap Coverage
    public boolean isInfinity; //check if the Vertex distance is infinity
	public int count; // Stores the number of shortest paths from that vertex
    /**
     * Constructor for the vertex
     * 
     * @param n
     *            : int - name of the vertex
     */
    Vertex(int n) {
		index=0;
		name = n;
		seen = false;
		parent = null;
		distance=Integer.MAX_VALUE;
		Adj = new ArrayList<Edge>();
		revAdj = new ArrayList<Edge>();   /* only for directed graphs */
		isInfinity = true;
		count = 0;
    }

    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
	return Integer.toString(name);
    }

	@Override
	public void putIndex(int index) {
		this.index=index;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public int compare(Vertex o1, Vertex o2) {
		return o1.distance-o2.distance;
	}
}