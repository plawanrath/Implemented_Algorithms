import java.util.Stack;

/*
 * @author Girish
 * This class implements the DAG shortest path algorithm
 */

public class DAG {

	/*
	 * Function checks if a given graph is a DAG
	 */
	public static boolean isDag(Graph g) {

		Boolean[] s = new Boolean[g.numNodes + 1];

		for (int i = 1; i <= g.numNodes; i++) {
			g.verts.get(i).seen = false;
			s[i] = false;
		}

		for (int i = 1; i <= g.numNodes; i++) {
			if (g.verts.get(i).seen == false) {
				if (!isDagUtil(g, g.verts.get(i), s))
					return false;
			}
		}
		return true;
	}

	/*
	 * Utility function to find DAG shortest path
	 */
	public static boolean isDagUtil(Graph g, Vertex v, Boolean[] stack) {

		if (v.seen == false) {
			v.seen = true;
			stack[v.name] = true;

			for (Edge e : v.Adj) {
				Vertex u = e.To;
				if (u.seen == false) {
					if (!isDagUtil(g, u, stack))
						return false;
				} else {
					if (stack[u.name] == true)
						return false;
				}
			}
		}
		stack[v.name] = false;
		return true;
	}

	/*
	 * Utility function for DAG shortest path
	 */
	public static void topologicalOrderStack(Graph g, Vertex v, Stack<Vertex> stack) {
		v.seen = true;
		for (Edge e : v.Adj) {
			Vertex u = e.To;
			if (!u.seen)
				topologicalOrderStack(g, u, stack);
		}
		stack.push(v);
	}

	/*
	 * Function to do a topological ordering of the vertices and return the stack containing the order
	 */
	public static Stack<Vertex> toplogicalOrder(Graph g) {

		Stack<Vertex> stack = new Stack<Vertex>();
		for (int i = 1; i <= g.numNodes; i++) {
			g.verts.get(i).seen = false;
		}
		for (int i = 1; i <= g.numNodes; i++) {
			if (g.verts.get(i).seen == false)
				topologicalOrderStack(g, g.verts.get(i), stack);
		}

		return stack;
	}

	/*
	 * Relaxation function for DAG shortest path
	 */
	public static void relax(Vertex u, Vertex v, Edge e) {

		if (v.distance > u.distance + e.Weight) {
			v.distance = u.distance + e.Weight;
			v.parent = u;
			v.isInfinity = false;
		}

	}

	/*
	 * Function to find the DAG shortest path
	 */
	public static void shortestPath(Graph g) {

		Stack<Vertex> stack;
		stack = toplogicalOrder(g);
		g.initialize();

		while (!stack.isEmpty()) {
			Vertex u = stack.pop();
			if(!u.isInfinity) {
				for (Edge e : u.Adj) {
					Vertex v = e.To;
					if (!v.seen) {
						relax(u, v, e);
					}
					v.seen = true;
				}
			}
		}
	}
}
