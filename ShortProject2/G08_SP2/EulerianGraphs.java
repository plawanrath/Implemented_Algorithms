/**
 * @author Plawan
 * This class has the code to test whether a graph is Eulerian.
 *  
 */

public class EulerianGraphs {

	/*
	 * This function checks whether the graph G passed is connected.
	 * It returns true if the graph G is connected or else it returns
	 * false.
	 */
	public static boolean isConnected(Graph g)
	{
		for(Vertex u : g)
			u.seen = false;
		GraphOperations.DFS(g);
		for(Vertex v : g)
		{
			if(!v.seen)
				return false;
		}
		return true;
	}
/*
 * The logic is taken from Euler Theorem mentioned in the following link:
 * http://www.ctl.ua.edu/math103/euler/howcanwe.htm
 * If odd count is 0 the graph has Euler cycle
 * If odd count is 2 the graph has Euler path
 * If odd count > 2 if not Eulerian 
 * Given a graph G, the function first checks is the graph is connected,
 * if it is connected, then it counts the number of vertices of odd degrees
 * and based on that it comes to the conclusion.
 * Parameter 1: Graph G
 * 
 */
	public static void testEulerian(Graph g)
	{
		if(!isConnected(g))
		{
			System.out.println("Graph is not connected.");
			return;
		}
		int oddCount = 0;
		for(Vertex v : g)
		{
			if(v.Adj.size()%2 != 0)
				oddCount++;
		}
		switch(oddCount)
		{
		case 2: System.out.println("Graph has a Euler Path between 1 and " + g.numNodes);
				return;
		case 0: System.out.println("Graph is Eulerian");
				return;
		default: System.out.println("Graph is not Eulerian. It has " + oddCount + 
				" vertices of odd degree");; // Odd count can never be 1 for undirected graph 
		}
	}
	
	public static void main(String[] args) {
		Graph gEcycle = new Graph(5);
		gEcycle.addEdge(1, 2, 0);
		gEcycle.addEdge(1, 3, 0);
		gEcycle.addEdge(1, 4, 0);
		gEcycle.addEdge(1, 5, 0);
		gEcycle.addEdge(2, 3, 0);
		gEcycle.addEdge(4, 5, 0);
		Graph gEPath = new Graph(5);
		gEPath.addEdge(1, 2, 0);
		gEPath.addEdge(1, 3, 0);
		gEPath.addEdge(1, 4, 0);
		gEPath.addEdge(2, 3, 0);
		gEPath.addEdge(4, 5, 0);
		EulerianGraphs.testEulerian(gEcycle);
		EulerianGraphs.testEulerian(gEPath);
	}

}
