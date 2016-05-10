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

}
