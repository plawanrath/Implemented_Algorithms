import java.util.LinkedList;
import java.util.Queue;
/*
 * @author Girish
 * This class implements the BFS algoeithm to find the shortest paths.
 */

public class BFS {

	/*
	 * Finds the shortest path in the graph using Breadth First Search
	 * @param - Graph g on which the algorithm is to be run.
	 *
	 */
	public static void bfsShortestPath(Graph g) {

		Vertex src = g.initialize();
		Queue<Vertex> bfsQ = new LinkedList<Vertex>();

		bfsQ.add(src);
		while (!bfsQ.isEmpty()) {
			Vertex u = bfsQ.poll();

			for (Edge e : u.Adj) {
				Vertex v = e.To;
				if (!v.seen) {
					v.distance = u.distance + e.Weight;
					v.parent = u;
					v.seen = true;
					v.isInfinity = false;
					bfsQ.add(v);
				}
			}
		}
	}
}
