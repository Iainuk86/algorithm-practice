package dev.iainmcintosh.graph.shortestpathtrees;

import dev.iainmcintosh.sorting.Topological;
import dev.iainmcintosh.stack.ArrayStack;

public class AcyclicSP {

    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public AcyclicSP(EdgeWeightedDigraph graph, int source)
    {
        distTo = new double[graph.numberOfVertices()];
        edgeTo = new DirectedEdge[graph.numberOfVertices()];

        for (int v = 0; v < graph.numberOfVertices(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;

        distTo[source] = 0.0;

        Topological top = new Topological(graph);

        if (top.hasNoCycle())
            throw new IllegalArgumentException("Graph is not acyclic");
        for (int v : top.getOrder())
            relax(graph, v);
    }

    private void relax(EdgeWeightedDigraph graph, int vertex)
    {
        for (DirectedEdge e : graph.adjacent(vertex))
        {
            int w = e.to();
            if (distTo[w] > distTo[vertex] + e.weight())
            {
                distTo[w] = distTo[vertex] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

    public double distanceTo(int vertex)
    { return distTo[vertex]; }

    public boolean hasPathTo(int vertex)
    { return distTo[vertex] < Double.POSITIVE_INFINITY; }

    public Iterable<DirectedEdge> pathTo(int vertex)
    {
        ArrayStack<DirectedEdge> path = new ArrayStack<>();

        for (DirectedEdge e = edgeTo[vertex]; e != null; e = edgeTo[e.from()])
            path.push(e);

        return path;
    }
}
