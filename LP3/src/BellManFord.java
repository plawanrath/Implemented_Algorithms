import java.util.*;

/*
 * @author Girish and Plawan
 * This class implements the BellManFord algorithm to find the shortest Paths
 */

public class BellManFord {

	static Vertex startCycle = null; //This variable stores a vertex of the cycle from which negatice or zero cycle can be recreated.

	/*
	 * This function is used for relaxation with the added condition that zero cycles are also caught. This is used for B_F algorithm used in Level 2
	 *
	 * @param u - From vertex
	 * @param v - To Vertex
	 * @param e - Edge
	 * @param queue - Queue to hold vertices
	 *
	 */
	private static void relaxZeroCycle(Vertex u, Vertex v, Edge e, Queue<Vertex> queue) {

		if (v.distance >= u.distance + e.Weight) {
			v.distance = u.distance + e.Weight;
			v.parent = u;
			v.isInfinity = false;

			if (!v.seen) {
				queue.add(v);
				v.seen = true;
			}	
		}

	}

	/*
	 * This function is used for relaxation. This is used for B_F algorithm used in Level 1
	 * @param u - From vertex
	 * @param v - To Vertex
	 * @param e - Edge
	 * @param queue - Queue to hold vertices
	 */
	private static void relax(Vertex u, Vertex v, Edge e, Queue<Vertex> queue) {
		if (v.distance > u.distance + e.Weight) {
			v.distance = u.distance + e.Weight;
			v.parent = u;
			v.isInfinity = false;

			if (!v.seen) {
				queue.add(v);
				v.seen = true;
			}
		}
	}

	/*
	 * This function runs B-F algorithm .
	 *
	 * @param g - Graph on which B-F algorithm is to be run
	  * @param zeroCycleRelaxationFlag - condition to set the level of relaxation based on whether we want to catch zero length cycles.
	 */
	public static boolean shortestPath(Graph g,boolean zeroCycleRelaxationFlag) { //The zeroCycleRelaxation flag is set to true for Level 2 to run B-F algorithm to add functionality to distinguish zero cycle.

		g.initialize();
		
		Queue<Vertex> queue = new LinkedList<Vertex>();
		queue.add(g.verts.get(1));

		while (!queue.isEmpty()) {
			Vertex u = queue.poll();
			u.seen = false;
			u.count = u.count + 1;

			if (u.count > g.numNodes) {
				startCycle = u;
				return false;
			}

			for (Edge e : u.Adj) {
				Vertex v = e.otherEnd(u);
				if(zeroCycleRelaxationFlag) {
					relaxZeroCycle(u, v, e, queue);
				}
				else
					relax(u, v, e, queue);
			}
		}
		return true;
	}

	/*
	 * Wrapper function to call the b-F algorithm implementation with zero distance relaxation by default
	 */
	public static boolean shortestPath(Graph g) { //This version needs to be run for Level 1
		return shortestPath(g, false);
	}
}
