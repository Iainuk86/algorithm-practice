package org.iainuk.graph.shortestpathtrees;

import org.iainuk.queue.ArrayQueue;
import org.iainuk.stack.ArrayStack;

public class BellmanFord {

    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private boolean[] onQueue;
    private ArrayQueue<Integer> queue;
    private int cost;
    private Iterable<DirectedEdge> cycle;

    public BellmanFord(EdgeWeightedDigraph graph, int source)
    {
        int V = graph.numberOfVertices();
        edgeTo = new DirectedEdge[V];
        distTo = new double[V];
        onQueue = new boolean[V];
        queue = new ArrayQueue<>();

        for (int v = 0; v < V; v++)
            distTo[v] = Double.POSITIVE_INFINITY;

        distTo[source] = 0.0;
        queue.enqueue(source);
        onQueue[source] = true;

        while(!queue.isEmpty())
        {
            int vertex = queue.dequeue();
            onQueue[vertex] = false;
            relax(graph, vertex);
        }
    }

    private void relax(EdgeWeightedDigraph graph, int vertex)
    {
        for (DirectedEdge e : graph.adjacent(vertex))
        {
            int to = e.to();
            if (distTo[to] > distTo[vertex] + e.weight())
            {
                distTo[to] = distTo[vertex] + e.weight();
                edgeTo[to] = e;

                if (!onQueue[to])
                {
                    queue.enqueue(to);
                    onQueue[to] = true;
                }

                if (cost++ % graph.numberOfVertices() == 0)
                    detectNegativeCycle();
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

    public boolean hasNegativeCycle()
    { return cycle != null; }

    public Iterable<DirectedEdge> negativeCycle()
    { return cycle; }

    private void detectNegativeCycle()
    {
        int N = edgeTo.length;
        EdgeWeightedDigraph copy = new EdgeWeightedDigraph(N);

        for (int v = 0; v < N; v++)
            if (edgeTo[v] != null)
                copy.addEdge(edgeTo[v]);

        NegativeCycleFinder cf = new NegativeCycleFinder(copy);

        if (cf.hasCycle())
            cycle = cf.cycle();
    }
}
