import java.util.*;

/*
 * @author Girish
 */
public class SCC {

	// A recursive function to print DFS starting from v
	static void DFS(Graph g, Vertex v) {
		//System.out.print(v + " ");
		// Mark the current node as visited and print it
		int u = v.name;
		for (Vertex w : g) {
			if (w.name == u)
				v = w;
		}
		v.seen = true;

		for (Edge e : v.Adj) {
			Vertex a = e.To;
			if (!a.seen)
				DFS(g, a);
		}
	}

	// Function that returns reverse (or transpose) of this graph
	static Graph getTranspose(Graph g) {

		Graph a = new Graph(g.numNodes);

		for (Vertex v : g) {
			for (Edge e : v.Adj) {
				a.addDirectedEdge(e.To.name, e.From.name, 0);
			}
		}
		return a;
	}

	static void DFSStack(Graph g, Vertex v, Stack<Vertex> s) {

		// Mark the current node as visited.
		v.seen = true;

		for (Edge e : v.Adj) {
			Vertex a = e.To;
			if (!a.seen)
				DFSStack(g, a, s);
		}

		s.push(v);
	}

	static int stronglyConnectedComponents(Graph g) {

		int count = 0;
		Stack<Vertex> stack = new Stack<>();

		// Mark all the vertices as not visited (For first DFS)
		for (Vertex v : g) {
			v.seen = false;
		}

		// push vertices in stack

		for (Vertex v : g) {
			if (v.seen == false) {
				DFSStack(g, v, stack);
			}
		}

		// Create a reversed graph
		Graph gr = getTranspose(g);

		// Mark all the vertices as not visited (For second DFS)
		for (Vertex v : gr)
			v.seen = false;

		// Now process all vertices in order defined by Stack
		while (stack.empty() == false) {
			// Pop a vertex from stack
			Vertex v = stack.pop();
			int u = v.name;

			for (Vertex w : gr) {
				if (w.name == u) {
					v = w;
				}
			}

			if (!v.seen) {
				count++;
				DFS(gr, v);
				//System.out.println();
			}
		}

		return count;
	}

	public static void main(String[] args) {

		Graph g = new Graph(7);
		g.addDirectedEdge(1, 2, 1);
		g.addDirectedEdge(2, 3, 1);
		g.addDirectedEdge(3, 1, 1);
		g.addDirectedEdge(2, 4, 1);
		g.addDirectedEdge(2, 5, 1);
		g.addDirectedEdge(2, 7, 1);
		g.addDirectedEdge(4, 6, 1);
		g.addDirectedEdge(5, 6, 1);
		
		int SCC = stronglyConnectedComponents(g);
		System.out.println("Total Strongly Connected components are : " + SCC);

	}

}
