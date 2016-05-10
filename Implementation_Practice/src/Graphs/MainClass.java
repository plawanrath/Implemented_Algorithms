package Graphs;

import java.util.Scanner;

/**
 *
 * Created by Plawan on 5/3/16.
 */
public class MainClass {

    public static void clearGraph(Graph g) {
        for(Vertex u : g) {
            u.parent = null;
            u.seen = false;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
//        Graph g = Graph.readGraph(sc,false);
//        clearGraph(g);
//        GraphOperations.DFS(g);
//        clearGraph(g);
//        GraphOperations.BFS(g.verts.get(1));
        Graph g1 = Graph.readGraph(sc,false);
        clearGraph(g1);
        System.out.println(GraphOperations.DFSCC(g1));
    }
}
