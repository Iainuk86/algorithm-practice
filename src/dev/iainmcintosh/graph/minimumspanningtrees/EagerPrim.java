package dev.iainmcintosh.graph.minimumspanningtrees;

import dev.iainmcintosh.priorityqueue.IndexMinPQ;
import dev.iainmcintosh.queue.ArrayQueue;

public class EagerPrim {

    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public EagerPrim(EdgeWeightedGraph graph)
    {
        edgeTo = new Edge[graph.numberOfVertices()];
        distTo = new double[graph.numberOfVertices()];
        marked = new boolean[graph.numberOfVertices()];
        pq = new IndexMinPQ<>();

        for (int v = 0; v < graph.numberOfVertices(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;

        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty())
            { visit(graph, pq.indexOfMinimumValue()); pq.delMin(); } // TODO and this
    }

    private void visit(EdgeWeightedGraph graph, int vertex)
    {
        marked[vertex] = true;
        for (Edge e : graph.adjacent(vertex))
        {
            int next = e.other(vertex);
            if (marked[next]) continue;
            if (distTo[next] > e.weight())
            {
                distTo[next] = e.weight();
                edgeTo[next] = e;
                if (!pq.containsIndex(next)) pq.insert(next, e.weight());
                else                         pq.changeValue(next, e.weight());
            }
        }
    }

    public Iterable<Edge> edges()
    {
        ArrayQueue<Edge> edges = new ArrayQueue<>();

        for (int v = 1; v < edgeTo.length; v++)
        {
            if (edgeTo[v] != null)
                edges.enqueue(edgeTo[v]);
        }

        return edges;
    }

    public double totalWeight()
    {
        double weight = 0.0;

        for (int v = 1; v < edgeTo.length; v++)
        {
            if (edgeTo[v] != null)
                weight += edgeTo[v].weight();
        }

        return weight;
    }
}
