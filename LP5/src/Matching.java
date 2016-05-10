import java.util.*;

/**
 * Created by Plawan on 4/30/16.
 * This is to find Maximum Cardinality matching for Bipartite Graphs.
 *
 */
public class Matching {

    static int msize = 0;

    //Function groups vertices into outer and inner
    public static List<Vertex> getOuterOrInner(Graph g,boolean outer)
    {
        List<Vertex> res = new ArrayList<>();
        for(int i=1;i<=g.numNodes;i++)
        {
            Vertex u = g.verts.get(i);
            if(outer)
            {
                if(u.outer == 1)
                    res.add(u);
            }
            else
            {
                if(u.outer == 0)
                    res.add(u);
            }
        }
        return res;
    }

    //Finding a maximal matching using Greedy algorithm
    public static int getGreedyMaximalMatch(Graph g)
    {
        int maxM = 0;
        for(Edge e : g.edges)
        {
            Vertex u = e.From;
            Vertex v = e.To;
            if(u.mate == null && v.mate == null)
            {
                u.mate = v;
                v.mate = u;
                maxM++;
            }
        }
        return maxM;
    }

    //Function to build a Queue containing only outer vertices
    public static Queue<Vertex> buildOuterQueue(Graph g)
    {
        Queue<Vertex> outerQ = new LinkedList<>();
        for(int index = 1;index <= g.numNodes;index++)
        {
            Vertex u = g.verts.get(index);
            u.seen = false;
            u.parent = null;
            if(u.mate == null && u.outer == 1)
            {
                u.seen = true;
                outerQ.add(u);
            }
        }
        return outerQ;
    }

    //Function to implement Matching algorithm to find Max cardinality matching.
    public static void getMatching(Graph g)
    {
        g.MarkVertices();
        List<Vertex> outer = getOuterOrInner(g,true);
        for(Edge e : g.edges) {
            if (outer.contains(e.From) && outer.contains(e.To)) {
                System.out.println("Graph g is not Bipartite!!");
                System.exit(-1);
            }
        }

        msize = getGreedyMaximalMatch(g);

        // Repeat: Find augmenting path, increase size of matching
        Start:
        while(true)
        {
            Queue<Vertex> outerQ = buildOuterQueue(g);
            while(!outerQ.isEmpty())
            {
                Vertex u = outerQ.poll();
                for(Edge e : u.Adj)
                {
                    Vertex v = e.otherEnd(u);
                    if(!v.seen) {
                        v.parent = u;
                        v.seen = true;
                        if (v.mate == null) { //We found Augmenting Path
                            processAugPath(v);
                            continue Start;
                        } else {
                            Vertex x = v.mate;
                            x.seen = true;
                            x.parent = v;
                            outerQ.add(x);
                        }
                    }
                }
            }
            break;
        }
    }

    //Helper function to increase size of matching using an augmenting path
    private static void processAugPath(Vertex u)
    {
        //u is a free inner node with an augmenting path
        Vertex p = u.parent;
        Vertex x = p.parent;
        u.mate = p;
        p.mate = u;
        while(x != null)
        {
            Vertex mnx = x.parent;
            Vertex y = mnx.parent;
            x.mate = mnx;
            mnx.mate = x;
            x = y;
        }
        msize++;
    }
}
