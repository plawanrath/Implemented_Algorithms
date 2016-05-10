import java.util.*;

/**
 * @author Plawan
 * This class contains utility functions that perform specific operations 
 * on graphs. 
 */

public class GraphOperations {
	
	/*
	 * This is a function to perform DFS taken from
	 * Dr. Balaji's slides.
	 */
	public static void DFS(Graph g)
	{
		MarkUnseen(g);
		for(Vertex u : g)
		{
			if(!u.seen)
				DFSVisit(u);
		}
	}
	
	/*
	 * This is a utility function that marks all vertices of the given
	 * graph as unseen.
	 */
	public static void MarkUnseen(Graph g)
	{
		for(Vertex u : g)
		{
			u.seen = false;
			u.parent = null;
			u.adjSeen = false;
		}		
	}
	
	/*
	 * This is a recursive function that will visit all the
	 * vertices that are adjacent to any given vertex.It is 
	 * used by the DFS algorithm implemented above. Taken 
	 * from Dr. Balaji's slides.
	 */
	private static void DFSVisit(Vertex u)
	{
		u.seen = true;
		for(Edge e : u.Adj)
		{
			Vertex v = e.otherEnd(u);
			if(!v.seen)
			{
				v.parent = u;
				DFSVisit(v);
			}
		}
	}
	
	/*
	 * This function perform's BFS on the given 
	 * graph G, starting from the given source.
	 */
	public static void BFS(Graph g,Vertex src)
	{
		MarkUnseen(g);
		Queue<Vertex> bfsQ = new LinkedList<Vertex>();
		bfsQ.add(src);
		while(!bfsQ.isEmpty())
		{
			Vertex current = bfsQ.poll();
			if(!current.seen)
			{
				current.seen = true;
				System.out.print(current.name + " ");
				for(Edge e : current.Adj)
				{
					Vertex v = e.otherEnd(current);
					bfsQ.add(v);
				}
			}
		}
	}
	
	/*
	 * This function checks whether the given graph is Bipartite.
	 * It uses the BFS logic to perform this check.
	 */
	public static boolean isBipartite(Graph g)
	{
		GraphOperations.MarkUnseen(g);
		Vertex src = g.verts.get(1); //Take first vertex as the source
		Queue<Vertex> bfsQ = new LinkedList<Vertex>();
		bfsQ.add(src);
		while(!bfsQ.isEmpty())
		{
			Vertex current = bfsQ.poll();
			if(!current.seen)
			{
				current.seen = true;
				for(Edge e : current.Adj)
				{
					Vertex v = e.otherEnd(current);
					if(v.seen)
						continue;
					v.parent = current;
					bfsQ.add(v);
				}
			}
			else
				return false;
		}
		return true;
	}

	/*
	 * This function checks if the given graph is connected
	 * Returns true if the graph is connected
	 */
	public static boolean getIsConnected(Graph g)
	{
		MarkUnseen(g);
//		GraphOperations.DFS(g);
		GraphOperations.NonrecursiveDFS(g, g.verts.get(1));
		for(Vertex v : g)
		{
			if(!v.seen)
				return false;
		}
		return true;		
	}
	
	/*
	 * This function returns the number of odd vertices in the given graph g
	 */
	public static int getOddVertexCount(Graph g)
	{
		return getOddVertices(g).size();
	}
	
	/*
	 * This function returns all the odd vertices of the given graph g
	 */
	public static List<Vertex> getOddVertices(Graph g)
	{
		List<Vertex> oddVertexList = new ArrayList<>();
		if(!getIsConnected(g))
			return null;
		for(Vertex v : g)
		{
			if(v.Adj.size()%2 != 0)
				oddVertexList.add(v);
		}
		return oddVertexList;
	}
	
	/*
	 * Given a vertex as a parameter, get the next edge that
	 * is not visited and mark it as visited.
	 */
	public static Edge getUnvisitedEdge(Vertex u)
	{
		for(Edge e : u.Adj)
		{
			if(!e.visited)
			{
				e.visited = true;
				return e;
			}
		}
		return null;
	}
	
	/*
	 * This function marks all edges of graph as unseen
	 */
	public static void NarkEdgesUnseen(Graph g)
	{
		for(Edge e : g.edges)
			e.visited = false;
	}
	
	/*
	 * @author Girish
	 * Function that performs iterative DFS
	 * Expects 2 parameters
	 * Parameter 1: Graph
	 * Parameter 2: source vertex  
	 */
	 public static void NonrecursiveDFS(Graph g, Vertex src) {
		 	MarkUnseen(g);
	        // depth-first search using an explicit stack
	        Deque<Vertex> stack = new LinkedList<Vertex>();
	        src.seen = true;
	        stack.push(src);
	        while (!stack.isEmpty()) {
	            Vertex u = stack.peek();
	            if(!u.adjSeen){	
	            	for(Edge e : u.Adj){
	            		Vertex v = e.otherEnd(u); 
	            		if(!v.seen){
	            			v.seen = true;
	            			v.parent = u;
	            			stack.push(v);
	            		}
	        		}
	            	u.adjSeen = true;
	            }
	    		else {
	    				stack.pop();
	            }
	        }
	 }
}
