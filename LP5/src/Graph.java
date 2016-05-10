/**
 * Class to represent a graph
 * 
 *
 */

//import java.io.*;
import java.util.*;

class Graph implements Iterable<Vertex> {
    public List<Vertex> verts; // array of vertices
    public int numNodes; // number of verices in the graph
	public List<Edge> edges; //List of edges

    /**
     * Constructor for Graph
     * 
     * @param size
     *            : int - number of vertices
     */
    Graph(int size) {
		numNodes = size;
		verts = new ArrayList<>(size + 1);
		edges = new ArrayList<>();
		verts.add(0, null);
		// create an array of Vertex objects
		for (int i = 1; i <= size; i++)
			verts.add(i, new Vertex(i));
    }

    /**
     * Method to add an edge to the graph
     * 
     * @param a
     *            : int - one end of edge
     * @param b
     *            : int - other end of edge
     * @param weight
     *            : int - the weight of the edge
     */
    void addEdge(int a, int b, int weight) {
		Vertex u = verts.get(a);
		Vertex v = verts.get(b);
		Edge e = new Edge(u, v, weight);
		u.Adj.add(e);
		v.Adj.add(e);
		edges.add(e);
    }

    /**
     * Method to add an arc (directed edge) to the graph
     * 
     * @param a
     *            : int - the head of the arc
     * @param b
     *            : int - the tail of the arc
     * @param weight
     *            : int - the weight of the arc
     */
    void addDirectedEdge(int a, int b, int weight) {
		Vertex head = verts.get(a);
		Vertex tail = verts.get(b);
		Edge e = new Edge(head, tail, weight);
		head.Adj.add(e);
		tail.revAdj.add(e);
		edges.add(e);
    }

	void addEdge(Edge e)
	{
		Vertex u = e.From;
		Vertex v = e.To;
		int w = e.Weight;
		addEdge(u.name,v.name,w);
	}

    /**
     * Method to create an instance of VertexIterator
     */
    public Iterator<Vertex> iterator() {
	return new VertexIterator();
    }

    /**
     * A Custom Iterator Class for iterating through the vertices in a graph
     *
     */
    private class VertexIterator implements Iterator<Vertex> {
	private Iterator<Vertex> it;
	/**
	 * Constructor for VertexIterator
	 * 
	 */
	private VertexIterator() {
	    it = verts.iterator();
	    it.next();  // Index 0 is not used.  Skip it.
	}

	/**
	 * Method to check if there is any vertex left in the iteration
	 * Overrides the default hasNext() method of Iterator Class
	 */
	public boolean hasNext() {
	    return it.hasNext();
	}

	/**
	 * Method to return the next Vertex object in the iteration
	 * Overrides the default next() method of Iterator Class
	 */
	public Vertex next() {
	    return it.next();
	}

	/**
	 * Throws an error if a vertex is attempted to be removed
	 */
	public void remove() {
	    throw new UnsupportedOperationException();
	}
    }

    public Vertex getVertex(int name){
    	for(int i=1;i<=numNodes;i++)
    		if(verts.get(i).name==name)
    			return verts.get(i);
    	return null;
    }

	/**
	 * This function clears the seen parameter of all vertices of the graph
	 */
	public void clearVertexSeen()
	{
		for(int i = 1;i <= numNodes;i++) {
			verts.get(i).seen = false;
		}
	}

	/*
	 * This function classifies into outer and inner
	 */
	public void MarkVertices()
	{
		Queue<Vertex> bfsQ = new LinkedList<>();
		Vertex src = verts.get(1);
		src.outer = 1;
		src.seen = true;
		bfsQ.add(src);
		while(!bfsQ.isEmpty())
		{
			Vertex u = bfsQ.remove();
			for(Edge e : u.Adj)
			{
				Vertex v = e.otherEnd(u);
				if(!v.seen) {
					v.seen = true;
					v.outer = u.outer == 1 ? 0 : 1;
					bfsQ.add(v);
				}
			}
		}
	}

    public static Graph readGraph(Scanner in, boolean directed) {
	// read the graph related parameters
    int n = in.nextInt(); // number of vertices in the graph
    int m = in.nextInt(); // number of edges in the graph

	// create a graph instance
	Graph g = new Graph(n);
	for (int i = 0; i < m; i++) {
	    int u = in.nextInt();
	    int v = in.nextInt();
	    int w = in.nextInt();
	    if(directed) {
		g.addDirectedEdge(u, v, w);
	    } else {
		g.addEdge(u, v, w);
	    }
	}
	in.close();
	return g;
    }
}