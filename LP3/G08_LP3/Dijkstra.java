/*
 * @author Girish
 * This class implements the Dijkistra's algorithm
 */

public class Dijkstra {

	/*
	 * Relaxation function for Dijkistra's algorithm uses an IndexedHeap
	 */
	public static void relax(Vertex u, Vertex v, Edge e, IndexedHeap<Vertex> PQ) {

		if (v.distance > u.distance + e.Weight) {
			v.distance = u.distance + e.Weight;
			v.parent = u;
			v.isInfinity = false;

			if (PQ != null)
				PQ.decreaseKey(v);
		}

	}

    /*
     * Function finds the shortest paths in the graph using the Dijkistra's algorithm.
     */
	public static void shortestPath(Graph g) {

		g.initialize();

		IndexedHeap<Vertex> IndexedPQ = new IndexedHeap<Vertex>(1000000, g.verts.get(1));

		for (Vertex v : g)
			IndexedPQ.insert(v);

		while (IndexedPQ.size != 0) {
			Vertex u = IndexedPQ.remove();
			u.seen = true;

			for (Edge e : u.Adj) {
				Vertex v = e.otherEnd(u);
				if (!v.seen) {
					relax(u, v, e, IndexedPQ);
				}
			}
		}
	}
}
