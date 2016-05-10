import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Plawan on 3/26/16.
 * Class to hold functions that are not specific to any of the implemented algorithms and could be reused for other implementations.
 */
public class PathOperations {

    /*
     * Function to print the shortest paths after any of the given algorithms have run.
     * THis is used for Level 1 to print as per Level 1 specifications.
     */
    public static void displayShortestPath(Graph g) {
        for (Vertex v : g) {
            if (!v.isInfinity)
                if (v.parent == null)
                    System.out.println(v + " " + v.distance + " -");
                else
                    System.out.println(v + " " + v.distance + " " + v.parent);
            else
                System.out.println(v + " " + "INF -");

        }
    }

    /*
     * Function to print the shortest path counts after we have done the computation as per Level 2
     */
    public static void displayShortestPath2(Graph g) {
        for (Vertex v : g) {
            if (!v.isInfinity)
                System.out.println(v + " " + v.distance + " " + v.count);
            else
                System.out.println(v + " " + "INF " + 0);

        }
    }

    /*
     * Function to check if edges have uniform weights
     */
    public static boolean checkUniformWeights(Graph g) {
        int weight = g.edges.get(0).Weight;
        for (Edge e : g.edges)
            if (e.Weight != weight)
                return false;
        return true;
    }

    /*
     * Function to check if all edges have positive weights
     */
    public static boolean checkPositiveWeights(Graph g) {
        for (Edge e : g.edges)
            if (e.Weight < 0)
                return false;
        return true;
    }

    /*
     * Function to find the edges corresponding to the cycle passed as list of vertices from the graph g
     */
    private static Stack<Edge> getCycleEdges(Graph g, List<Vertex> vertices) {
        Stack<Edge> cycleEdges = new Stack<>();
        for(Vertex v : vertices) {
            for(Edge e : g.edges) {
                if(e.From == v.parent && e.To == v) {
                    cycleEdges.push(e);
                    break;
                }
            }
        }
        return cycleEdges;
    }

    /*
     * Function to find negative cycle that starts from the start vertex in the graph g
     */
    public static Stack<Edge> getNegativeCycleEdges(Graph g,Vertex start) {
        int totalWeight = 0;
        List<Vertex> cycleVertices = findCycle(start);
        Stack<Edge> cycleEdges = getCycleEdges(g, cycleVertices);
        for (Edge e : cycleEdges) {
            totalWeight += e.Weight;
        }
        if(totalWeight < 0) {
            System.out.println("Negative Cycle");
            return cycleEdges;
        }
        else if(totalWeight == 0) {
            System.out.println("Zero Weight Cycle");
            return cycleEdges;
        }
        return null;
    }

    /*
     * Utility function used by the getNegativeCycleEdges to construct the cycle and return the vertices for the cycle
     */
    private static List<Vertex> findCycle(Vertex start) {
        List<Vertex> cycle = new ArrayList<>();
        start.seen = true;
        cycle.add(start);
        while(true) {
            if(!start.parent.seen) {
                start = start.parent;
                start.seen = true;
                cycle.add(start);
            }
            else {
                Vertex cStart = start.parent;
                for(int i=0;i< cycle.size();i++) {
                    Vertex v = cycle.get(i);
                    if(v.name != cStart.name) {
                        cycle.remove(v);
                        i--;
                    }
                    else
                        break;
                }
                return cycle;
            }
        }
    }

    /*
     * Function to return the total shortest distance for Level 1
     */
    public static long returnSumOfDistances(Graph g) {
        long sum = 0;
        for(Vertex v : g) {
            if(!v.isInfinity)
                sum += v.distance;
        }
        return sum;
    }

    /*
     * Function to return the total number of shortest distances for Level 2
     */
    public static long returnSumOfNumberOfPaths(Graph g) {
        long sum = 0;
        for(Vertex v : g) {
            if(!v.isInfinity)
                sum += v.count;
        }
        return sum;
    }
}
