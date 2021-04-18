package dev.iainmcintosh.graph.shortestpathtrees;

import dev.iainmcintosh.priorityqueue.IndexMinPQ;
import dev.iainmcintosh.stack.ArrayStack;

public class DijkstraSP {

    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph graph, int source)
    {
        distTo = new double[graph.numberOfVertices()];
        edgeTo = new DirectedEdge[graph.numberOfVertices()];
        pq = new IndexMinPQ<>(graph.numberOfVertices());

        for (int v = 0; v < graph.numberOfVertices(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;

        distTo[source] = 0.0;
        pq.insert(source, 0.0);
        while (!pq.isEmpty())
            { relax(graph, pq.indexOfMinimumValue()); pq.delMin(); } // TODO fix this
    }

    private void relax(EdgeWeightedDigraph graph, int vertex)
    {
        for (DirectedEdge e : graph.adjacent(vertex))
        {
            int next = e.to();
            if (distTo[next] > distTo[vertex] + e.weight())
            {
                distTo[next] = distTo[vertex] + e.weight();
                edgeTo[next] = e;
                if (pq.containsIndex(next)) pq.changeValue(next, e.weight());
                else                        pq.insert(next,e.weight());
            }
        }
    }

    public double distTo(int vertex)
    { return distTo[vertex]; }

    public boolean hasPathTo(int vertex)
    { return distTo[vertex] < Double.POSITIVE_INFINITY; }

    public Iterable<DirectedEdge> pathTo(int vertex)
    {
        if (!hasPathTo(vertex))
            throw new IllegalArgumentException("No path to vertex " + vertex + " found");

        ArrayStack<DirectedEdge> path = new ArrayStack<>();

        for (DirectedEdge e = edgeTo[vertex]; e != null; e = edgeTo[e.from()])
            path.push(e);

        return path;
    }
}
