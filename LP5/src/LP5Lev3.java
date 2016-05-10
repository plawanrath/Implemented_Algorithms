import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Plawan on 5/1/16.
 */
public class LP5Lev3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in;
        boolean VERBOSE = false;

        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }
        if (args.length > 1) {
            VERBOSE = true;
        }
        Graph g = Graph.readGraph(in, false);   // read undirected graph from stream "in"
//	 Create your own class and call the function to find a maximum matching.
        WeightedMatching.getWeightedMatching(g);
        System.out.println();
//        int result = Matching.msize;
//        System.out.println(result);
//        if (VERBOSE) {
//            for(Edge e : g.edges)
//            {
//                if(e.From.mate == e.To && e.To.mate == e.From)
//                    System.out.println(e.From + " " + e.To + " " + e.Weight);
//            }
//        }
    }
}
