package dev.iainmcintosh.graph.shortestpathtrees;

public class DijkstraSPMatrix {

    private DijkstraSP[] allPairs;

    public DijkstraSPMatrix(EdgeWeightedDigraph graph)
    {
        for (int v = 0; v < graph.numberOfVertices(); v++)
            allPairs[v] = new DijkstraSP(graph, v);
    }

    public double distanceBetween(int from, int to)
    { return allPairs[from].distTo(to); }

    public Iterable<DirectedEdge> path(int from, int to)
    { return allPairs[from].pathTo(to); }
}
