/*
 * @author Plawan.
 * This is a class that implements methods to find Euler tour or a Euler path in a graph 
 * Given a tour it has a function to check if that is a Euler tour.   
 */
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class EulerTourFinder {

	/*
	 * Algorithm implemented given in
	 * https://en.wikipedia.org/wiki/Eulerian_path
	 * parameter 1: Graph g
	 * Returns a list of edges in Euler tour or Euler path
	 * It will exit if the graph doesn't have a Euler tour.
	 */
	public static List<Edge> findEulerTour(Graph g)  
	{
		if(!GraphOperations.getIsConnected(g)) //Run Iterative DFS to check if graph is connected and stop if it isn't
		{
			System.err.println("The graph isn't connected!!");
			System.exit(-1);
		}
		int oddVertexCount = GraphOperations.getOddVertexCount(g); //Can't be Eulerian if odd vertex count not equal to 0 or 2
		if(oddVertexCount != 0 && oddVertexCount != 2)
		{
			System.err.println("Graph is not Eulerian. It has " + oddVertexCount + 
					" vertices of odd degree"); // Odd count can never be 1 for undirected graph 
			System.exit(-1);
		}
		List<Edge> eulerTour = new LinkedList<Edge>(); //List to store euler tour
		Stack<Edge> UnVisitedEdges = new Stack<Edge>(); //Stack contains all edges that are unvisited
		
		for(Edge e : g.edges)
			UnVisitedEdges.push(e);
		while(!UnVisitedEdges.isEmpty()) //continue until all edges are visited
		{
			Edge startEdge = UnVisitedEdges.pop(); //We could start from any edge
			Vertex start = startEdge.From;
			List<Edge> tour = new LinkedList<Edge>(); // List to store sub tours
			Vertex end = startEdge.otherEnd(start);
			tour.add(startEdge);
			while(end.name != start.name && !UnVisitedEdges.isEmpty()) //Loop through adjacent edges until we either find a sub tour or until all edges are visited
			{
				Edge e = GraphOperations.getUnvisitedEdge(end); //Function gets an unvisited edge adjacent to the given vertex
				tour.add(e);
				UnVisitedEdges.remove(e);
				end = e.otherEnd(end);
			}
			eulerTour.addAll(tour); //Add sub tours to the Euler tour
		}
		return eulerTour;
	}
	
	/*
	 * Check if the given set of Edges form a Eulerian tour
	 * Traverse through the tour and at the end check if all edges
	 * have been visited.
	 * Parameter 1: graph g
	 * Parameter 2: List of edges in the tour to be verified
	 * Parameter 3: Start vertex
	 * Returns true if the tour is Eulerian.
	 * Implementation similar to the one above
	 */
	
	static boolean verifyTour(Graph g, List<Edge> tour, Vertex start)
	{
		if(!GraphOperations.getIsConnected(g))
		{
			System.err.println("The graph isn't connected!!");
			System.exit(-1);
		}
		int oddVertexCount = GraphOperations.getOddVertexCount(g);
		if(oddVertexCount != 0 && oddVertexCount != 2)
		{
			System.err.println("Graph is not Eulerian. It has " + oddVertexCount + 
					" vertices of odd degree"); // Odd count can never be 1 for undirected graph 
			System.exit(-1);
		}
		List<Edge> UnvisitedEdges = g.edges;
		Stack<Edge> tourEdges = new Stack<Edge>();
		for(Edge e : tour)
			tourEdges.push(e);
		while(!tourEdges.isEmpty())
		{
			Edge startEdge = tourEdges.pop();
			if(UnvisitedEdges.isEmpty())
				return false;
			UnvisitedEdges.remove(startEdge);
		}
		if(UnvisitedEdges.isEmpty())
			return true;
		return false;
	}
	
	/*
	 * Main function expects either 0 or 1 arguments.
	 * If no arguments passed to the program, then the graph needs
	 * to be entered in command line.
	 * Or a graph file can be passed as command line argument.
	 */
	public static void main(String[] args) throws Exception {
		
		Scanner input = null;
		if(args.length < 1)
		{
			input = new Scanner(System.in);
		}
		else if(args.length == 1)
		{
			File inputFile = new File(args[0]);
			input = new Scanner(inputFile);
		}
		Graph g = Graph.readGraph(input, false);
		long start = System.currentTimeMillis();
		List<Edge> eulerTour = findEulerTour(g);
		long end = System.currentTimeMillis();
		System.out.println("Time taken = " + ((end-start)) + "ms");
		for(Edge e : eulerTour)
			System.out.println(e);
	}

}
