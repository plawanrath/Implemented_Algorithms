import java.util.*;

/*
 * @author Girish
 * This class contains logic to perform Topological sort on the given graph
 */
public class TopologicalSort {

	public static ArrayList<Integer> computeIndegree(Graph g) {

		ArrayList<Integer> indegree = new ArrayList<Integer>(g.numNodes + 1);

		for (int i = 1; i <= g.numNodes + 1; i++) {
			indegree.add(0);
		}
		indegree.set(0, -1);
		for (int i = 1; i <= g.numNodes; i++) {
			for (Edge e : g.verts.get(i).Adj) {
				Vertex v = e.To;
				indegree.set(v.name, indegree.get(v.name) + 1);
			}
		}
		return indegree;
	}

	/*
	 * This function performs Topological ordering using Lists.
	 */
	static List<Vertex> toplogicalOrder1(Graph g) {

		Queue<Vertex> queue = new LinkedList<Vertex>();
		List<Vertex> list = new ArrayList<Vertex>();

		ArrayList<Integer> indegree = computeIndegree(g);
		for (int i = 1; i < indegree.size(); i++) {
			if (indegree.get(i) == 0)
				queue.add(g.verts.get(i));
		}

		while (!queue.isEmpty()) {
			Vertex u = queue.poll();
			list.add(u);
			for (Edge e : u.Adj) {
				Vertex v = e.To;
				indegree.set(v.name, indegree.get(v.name) - 1);
				if (indegree.get(v.name) == 0) {
					queue.add(v);
				}
			}

		}
		return list;
	}

	/*
	 * This is a utility function used to perform topological ordering
	 * using stacks.
	 */
	static void topologicalOrderStack(Graph g, Vertex v, Stack<Vertex> stack) {
		v.seen = true;
		for (Edge e : v.Adj) {
			Vertex u = e.To;
			if (!u.seen)
				topologicalOrderStack(g, u, stack);
		}
		stack.push(v);
	}

	/*
	 * This function performs topological ordering using stacks
	 */
	static Stack<Vertex> toplogicalOrder2(Graph g) {

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
	 * Function to print the given list.
	 */
	public static <T> void printList(List<T> list) {
		for (T v : list) {
			System.out.print(v + " ");
		}
	}

	/*
	 * Function that checks whether the given graph is a DAG
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
	 * This is the utility function to check Directed Acyclic property
	 * for a specific vertex the given graph.
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

	public static void main(String[] args) {

		Graph g = new Graph(6);
		g.addDirectedEdge(6, 3, 1);
		g.addDirectedEdge(6, 1, 1);
		g.addDirectedEdge(5, 1, 1);
		g.addDirectedEdge(5, 2, 1);
		g.addDirectedEdge(3, 4, 1);
		g.addDirectedEdge(4, 2, 1);

		if (isDag(g) == true) {

			System.out.println("Topological order using List : ");
			List<Vertex> top1 = toplogicalOrder1(g);
			printList(top1);
			System.out.println();

			System.out.println("Topological order using Stack : ");
			List<Vertex> top2 = toplogicalOrder2(g);
			printList(top2);
		} else {
			System.out.println("Graph is not a DAG as it contain cycle(s)");
		}
	}
}
