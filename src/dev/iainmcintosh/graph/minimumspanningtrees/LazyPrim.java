package dev.iainmcintosh.graph.minimumspanningtrees;

import dev.iainmcintosh.priorityqueue.MinPQ;
import dev.iainmcintosh.queue.ArrayQueue;

public class LazyPrim {

    private MinPQ<Edge> pq;
    private boolean[] marked;
    private ArrayQueue<Edge> treeEdges;

    public LazyPrim(EdgeWeightedGraph graph)
    {
        pq = new MinPQ<>();
        treeEdges = new ArrayQueue<>();
        marked = new boolean[graph.numberOfVertices()];

        visit(graph, 0);
        while (!pq.isEmpty())
        {
            Edge edge = pq.delMin();
            int from = edge.either(), to = edge.other(from);
            if (marked[from] && marked[to]) continue;
            treeEdges.enqueue(edge);
            if (!marked[from])  visit(graph, from);
            if (!marked[to])    visit(graph, to);
        }
    }

    private void visit(EdgeWeightedGraph graph, int vertex)
    {
        marked[vertex] = true;
        for (Edge e : graph.adjacent(vertex))
        {
            int to = e.other(vertex);
            if(!marked[to])
                pq.insert(e);
        }
    }

    public Iterable<Edge> edges()
    { return treeEdges; }

    public double totalWeight()
    {
        double weight = 0.0;

        for (Edge e : treeEdges)
            weight += e.weight();

        return weight;
    }
}
