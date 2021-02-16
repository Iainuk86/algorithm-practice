package org.iainuk.graph.minimumspanningtrees;

import org.iainuk.priorityqueue.MinPQ;
import org.iainuk.queue.ArrayQueue;
import org.iainuk.unionfind.UnionFind;

public class Kruskal {

    private MinPQ<Edge> pq;
    private UnionFind unionFind;
    private ArrayQueue<Edge> edges;

    public Kruskal(EdgeWeightedGraph graph)
    {
        edges = new ArrayQueue<>();
        pq = new MinPQ<>(graph.edges());
        unionFind = new UnionFind(graph.numberOfVertices());

        while (!pq.isEmpty() && edges.size() < graph.numberOfVertices() - 1)
        {
            Edge e = pq.delMin();
            int from = e.either(), to = e.other(from);
            if (unionFind.connected(from, to)) continue;
            edges.enqueue(e);
            unionFind.union(from, to);
        }
    }

    public Iterable<Edge> edges()
    { return edges; }

    public double totalWeight()
    {
        double weight = 0.0;

        for (Edge e : edges)
            weight += e.weight();

        return weight;
    }
}
