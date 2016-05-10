import java.io.File;
import java.util.Scanner;

/*
 * @author Girish
 * Modified by Plawan
 * Drier program for level 1
 */


public class DriverLevel1 {

    public static void main(String[] args) throws Exception {

        Scanner input = null;
        if(args.length < 1)
            input = new Scanner(System.in);
        else if(args.length == 1)
        {
            File inputFile = new File(args[0]);
            input = new Scanner(inputFile);
        }
        Graph g = Graph.readGraph(input, true);
        long start = System.currentTimeMillis();
        if (PathOperations.checkUniformWeights(g)) {
            BFS.bfsShortestPath(g);
            System.out.println("BFS " + PathOperations.returnSumOfDistances(g));
        } else if (DAG.isDag(g)) {
            DAG.shortestPath(g);
            System.out.println("DAG " + PathOperations.returnSumOfDistances(g));
        } else if (PathOperations.checkPositiveWeights(g)) {
            Dijkstra.shortestPath(g);
            System.out.println("Dij " + PathOperations.returnSumOfDistances(g));
        } else {
            if (!BellManFord.shortestPath(g)) {
                System.out.println("Unable to solve problem. Graph has a negative cycle.");
                return;
            }
            System.out.println("B-F " + PathOperations.returnSumOfDistances(g));
        }
        long end = System.currentTimeMillis();
        if(g.numNodes <= 100)
            PathOperations.displayShortestPath(g);
        System.out.println("Time taken = " + ((end-start)) + "ms");
    }

}