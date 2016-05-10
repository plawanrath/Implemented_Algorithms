/**
 * Created by Plawan on 4/30/16.
 * This is to find Maximum Weighted matching for Bipartite Graphs.
 */
public class WeightedMatching {

    private static void label(Graph g)
    {
        for(Vertex u : g)
        {
            if(u.outer == 1)
            {
                int maxW = -1;
                for(Edge e : u.Adj)
                {
                    if(e.Weight >= maxW)
                        maxW = e.Weight;
                }
                u.label = maxW;
            }
            else
                u.label = 0;
        }
    }

    private static Graph zeroGraph(Graph g)
    {
        Graph z = new Graph(g.numNodes);
        for(Edge e : g.edges)
        {
            Vertex u = e.From;
            Vertex v = e.To;
            if((u.label + v.label) == e.Weight)
                z.addEdge(e);
        }
        return z;
    }

    private static boolean checkPerfectmatching(Graph g)
    {
        int innerCount = 0,outerCount = 0;
        for(Vertex u : g)
        {
            if(u.outer == 0)
                innerCount++;
            if(u.outer == 1)
                outerCount++;
        }
        if(innerCount == outerCount)
            return true;
        else
            return false;
    }

    public static void getWeightedMatching(Graph g)
    {
        g.MarkVertices();
        label(g);
        Graph zero = zeroGraph(g);
        Matching.getMatching(zero);
        boolean perfectMatching = checkPerfectmatching(zero);
        if(perfectMatching)
        {
            //TODO Output it as maximum wright matching
        }
        else
        {

        }
    }
}
