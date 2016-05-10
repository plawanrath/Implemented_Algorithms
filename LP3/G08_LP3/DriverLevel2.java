import java.io.File;
import java.util.Scanner;

/**
 * Created by Plawan on 3/27/16.
 * Driver program for Level 2
 */
public class DriverLevel2 {

    public static void main(String[] args) throws Exception {
        Scanner input = null;
        if(args.length < 1)
        {
            input = new Scanner(System.in);
        }
        else if(args.length == 1)
        {
            File inputFile = new File(args[0]);
            input = new Scanner(inputFile);
        }
        Graph g = Graph.readGraph(input, true);
        long start = System.currentTimeMillis();
        SimpleShortestPaths.getShortestPaths(g);
        long end = System.currentTimeMillis();
        System.out.println("Time taken = " + ((end-start)) + "ms");
        System.out.println(PathOperations.returnSumOfNumberOfPaths(g));
        if(g.numNodes <= 100)
            PathOperations.displayShortestPath2(g);
    }
}
