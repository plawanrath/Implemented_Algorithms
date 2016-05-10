import java.util.Stack;

/**
 * Created by Plawan on 3/26/16.
 * This class implements the Level 2 to find the number of shortest distances using the B-F algorithm
 */
public class SimpleShortestPaths {

    static boolean isNotCycle = false; //This variable determines if we need to proceed further after running the B-F algorithm.

    /*
     * Custom topological sort utility function which along with doing topological sort also distinguishes edges in terms of whether they are in the shortest paths.
     * This logic is useful to count the number of shortest paths.
     */
    private static void CustomTopologicalOrderStack(Graph g, Vertex v, Stack<Vertex> stack) {
        v.seen = true;
        for (Edge e : v.Adj) {
            if(e.active) {
                Vertex u = e.To;
                if (!u.seen)
                    CustomTopologicalOrderStack(g, u, stack);
            }
        }
        stack.push(v);
    }

    /*
     * Topological sort function using our custom utility function
     */
    public static Stack<Vertex> toplogicalOrder(Graph g) {

        Stack<Vertex> stack = new Stack<Vertex>();
        for (int i = 1; i <= g.numNodes; i++) {
            g.verts.get(i).seen = false;
        }
        for (int i = 1; i <= g.numNodes; i++) {
            if (g.verts.get(i).seen == false)
                CustomTopologicalOrderStack(g, g.verts.get(i), stack);
        }

        return stack;
    }

    /*
     * This function runs the B-F algorithm and populates the active field of edges to distinguish them for which edges need to be considered further.
     * Basically the "active" field if set true for an edge, means that the edge belongs to the grapg D (as discussed on class).
     */
    private static void buildShortestPathGraph(Graph g) {
        isNotCycle = BellManFord.shortestPath(g,true);
        g.clearVertexCount();
        for(Edge e : g.edges) {
            Vertex u = e.From;
            Vertex v = e.To;
            if((u.distance + e.Weight) == v.distance)
                e.active = true;
        }
    }

    /*
     * Function checks if the graph has negative or zero cycle then it prints the cycle or else, it counts
     * the number of shortest paths and stores them within the count parameter of the vertices.
     */
    public static void getShortestPaths(Graph g) {
        buildShortestPathGraph(g);
        if(!isNotCycle) {
            g.clearVertexSeen();
            if(BellManFord.startCycle != null) {
                Stack<Edge> cycleEdges = PathOperations.getNegativeCycleEdges(g, BellManFord.startCycle);
                Edge e = null;
                while (!cycleEdges.isEmpty()) {
                    e = cycleEdges.pop();
                    System.out.print(e.From + "->");
                }
                if (e != null)
                    System.out.print(e.To);
                System.out.println();
                System.exit(0);
            }
        }
        Stack<Vertex> orderedVertices = toplogicalOrder(g);
        g.verts.get(1).count = 1;
        while (!orderedVertices.isEmpty()) {
            Vertex v = orderedVertices.pop();
            for (Edge e : v.Adj) {
                if (e.active) {
                    Vertex u = e.otherEnd(v);
                    if (u.name != 1)
                        u.count = u.count + v.count;
                }
            }
        }
    }
}
