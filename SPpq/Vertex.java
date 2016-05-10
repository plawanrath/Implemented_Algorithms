/**
 * Class to represent a vertex of a graph
 * Updated by Plawan to accommodate IndexedHeap
 *
 */

import java.util.*;

public class Vertex implements Index,Comparable<Vertex> {
    public int name; // name of the vertex
    public boolean seen; // flag to check if the vertex has already been visited
    public Vertex parent; // parent of the vertex
    public int distance; // distance to the vertex from the source vertex
    public List<Edge> Adj, revAdj; // adjacency list; use LinkedList or ArrayList
    public int index;
    
    /**
     * Constructor for the vertex
     * 
     * @param n
     *            : int - name of the vertex
     */
    Vertex(int n) {
	name = n;
	seen = false;
	parent = null;
	Adj = new ArrayList<Edge>();
	revAdj = new ArrayList<Edge>();   /* only for directed graphs */
	index = -1;
    }

    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
	return Integer.toString(name);
    }
    
    public int getIndex() {
    	return index;
    }
    
    public void putIndex(int index) {
    	this.index = index;
    }
    
    /*
     * I write a comparaTo function to define what needs to be checked 
     * when comparing vertices.
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Vertex v2)
    {
    	if(this.distance > v2.distance)
    		return 1;
    	else if(this.distance < v2.distance)
    		return -1;
    	else
    		return 0;
    }
}