package Graphs;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Plawan on 5/3/16.
 *
 */
public class GraphOperations {

    public static void DFS(Graph g) {
        for(Vertex u : g) {
            if(!u.seen)
                DFSVisit(u);
        }
    }

    public static void DFSVisit(Vertex u) {
        u.seen = true;

        for(Edge e : u.Adj) {
            Vertex v = e.otherEnd(u);
            if(!v.seen) {
                v.parent = u;
                DFSVisit(v);
            }
        }
    }

    public static int DFSCC(Graph g) {
        int connected = 0;

        for(Vertex u : g) {
            if(!u.seen)
                DFSVisit(u,++connected);
        }
        return connected;
    }

    public static void DFSVisit(Vertex u,int con) {

        u.seen = true;
        u.connected = con;

        for(Edge e : u.Adj) {
            Vertex v = e.otherEnd(u);
            if(!v.seen) {
                v.parent = u;
                DFSVisit(v,con);
            }
        }
    }

    public static ArrayDeque<Vertex> DFSTopological(Graph g) {
        ArrayDeque<Vertex> stack = new ArrayDeque<>();
        for(Vertex u : g) {
            if(!u.seen)
                DFSVISITTop(u,stack);
        }
        return stack;
    }

    public static void DFSVISITTop(Vertex u,ArrayDeque<Vertex> s) {
        u.seen = true;

        for(Edge e : u.Adj) {
            Vertex v = e.otherEnd(u);
            if(!v.seen) {
                v.parent = u;
                DFSVISITTop(v, s);
            }
        }
        s.push(u);
    }

    public static void BFS(Vertex source) {
        Queue<Vertex> q = new LinkedList<>();
        q.add(source);

        while(!q.isEmpty()) {
            Vertex u = q.poll();
            if(!u.seen) {
                u.seen = true;
                for(Edge e : u.Adj) {
                    Vertex v = e.otherEnd(u);
                    v.parent = u;
                    q.add(v);
                }
            }
        }
    }
}
