import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Plawan
 * This class contains function to find the odd length cycle in a non-bipartite 
 * graph. 
 */
public class FindCycles {

	/**
	 * This function runs a BFS to find the edge that leads to 
	 * an odd length cycle. If this function returns null, that
	 * means that the graph is Bipartite.
	 * Parameter 1: The graph G
	 * Returns the edge that leads to odd-length cycle.
	 * @param args
	 */
	public static Edge findOddEdge(Graph g)
	{
		GraphOperations.MarkUnseen(g);
		Vertex src = g.verts.get(1); //Take first vertex as the source
		Queue<Vertex> bfsQ = new LinkedList<Vertex>();
		bfsQ.add(src);
		Edge nonBipartiteEdge = null;
		Edge returnValue = null;
		while(!bfsQ.isEmpty())
		{
			Vertex current = bfsQ.poll();
			if(!current.seen)
			{
				current.seen = true;
				for(Edge e : current.Adj)
				{
					nonBipartiteEdge = e;
					Vertex v = e.otherEnd(current);
					if(v.seen)
						continue;
					v.parent = current;
					bfsQ.add(v);					
				}
			}
			else
			{
				returnValue = nonBipartiteEdge;
			}
		}
		return returnValue;
	}
	
	/**
	 * The idea here is first I find the edge that leads to odd length cycle using BFS
	 * Then I use that edge to run a partial BFS until I get the cycle and capture the
	 * set of vertices in the process. 
	 * Parameter 1: The graph G
	 * Returns the list of vertices that comprise the odd-length cycle.
	 * @param args
	 */	
	public static List<Vertex> oddLengthCycle(Graph g)
	{
		List<Vertex> vertexList = new LinkedList<>();
		Edge oddEdge = findOddEdge(g);
		if(oddEdge == null)
		{
			System.out.println("Graph is Bipartite and has no odd-length cycle!!");
			return null;
		}
		else
		{
			Vertex u = oddEdge.From;
			Vertex v = oddEdge.To;
			GraphOperations.MarkUnseen(g);
			g.verts.get(u.name).seen = true;
			Queue<Vertex> bfsQ = new LinkedList<Vertex>();
			bfsQ.add(v);
			while(!bfsQ.isEmpty())
			{
				Vertex current = bfsQ.poll();
				if(current.name == u.name && current.seen)
				{
					vertexList.add(u);
					return vertexList;
				}
				vertexList.add(current);
				if(!current.seen)
				{
					current.seen = true;
					for(Edge e : current.Adj)
					{
						Vertex t = e.otherEnd(current);
						t.parent = current;
						bfsQ.add(t);					
					}
				}	
			}
			return null;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = new Graph(4);
		g.addEdge(1, 2, 0);
		g.addEdge(2, 3, 0);
		g.addEdge(3, 4, 0);
		g.addEdge(2, 4, 0);
		System.out.println(FindCycles.oddLengthCycle(g));
		Graph g1 = new Graph(4);
		g1.addEdge(1, 2, 0);
		g1.addEdge(2, 3, 0);
		g1.addEdge(3, 4, 0);
		System.out.println(FindCycles.oddLengthCycle(g1));		
	}

}
