/*
 * @author Girish
 * Modified by Plawan.
 */
import java.util.Scanner;

/*
 * Class to find minimum spanning tree using Prim's algorithm
 */
public class MST {
    static final int Infinity = Integer.MAX_VALUE;

	final static int sizeOfarray = 100000;

	/*
	 * Function implements the first Prim's algorithm implementation
	 * discussed in class.
	 */
	static int MSTPrim1(Graph g) {
		int wmst = 0;
		Vertex src = g.verts.get(1);

		for (Vertex v : g) {
			v.seen = false;
			v.parent = null;
		}
		src.seen = true;

		BinaryHeap<Edge> queue = new BinaryHeap<Edge>(sizeOfarray,null);

		for (Edge e : src.Adj) {
			queue.add(e);
		}

		while (!(queue.size == 0)) {
			Edge e = queue.remove();
			Vertex v, u;
			if (e.From.seen && e.To.seen)
				continue;
			if (e.From.seen) {
				v = e.To;
				u = e.From;
			} else {
				u = e.To;
				v = e.From;
			}
			v.parent = u;
			wmst += e.Weight;
			v.seen = true;
			for (Edge f : v.Adj) {
				Vertex w = f.otherEnd(v);
				if (!w.seen)
					queue.add(f);
			}
		}

		return wmst;
	}
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        Graph g = Graph.readGraph(in, false);
        System.out.println(MSTPrim1(g));
    }
}
