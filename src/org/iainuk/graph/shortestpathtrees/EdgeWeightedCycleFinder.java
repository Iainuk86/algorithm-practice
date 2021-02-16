package org.iainuk.graph.shortestpathtrees;

import org.iainuk.stack.ArrayStack;

public class EdgeWeightedCycleFinder {

    private boolean[] marked;
    private boolean[] onStack;
    private DirectedEdge[] edgeTo;
    private Iterable<DirectedEdge> cycle;

    public EdgeWeightedCycleFinder(EdgeWeightedDigraph graph)
    {
        marked = new boolean[graph.numberOfVertices()];
        onStack = new boolean[graph.numberOfVertices()];
        edgeTo = new DirectedEdge[graph.numberOfVertices()];

        for (int v = 0; v < graph.numberOfVertices(); v++)
            if (!marked[v])
                search(graph, v);
    }

    private void search(EdgeWeightedDigraph graph, int vertex)
    {
        onStack[vertex] = true;
        marked[vertex] = true;
        for (DirectedEdge e : graph.adjacent(vertex))
        {
            int next = e.to();
            if (this.hasCycle()) return;
            else if (!marked[next])
            {
                edgeTo[next] = e;
                search(graph, next);
            }
            else if (onStack[next])
            {
                ArrayStack<DirectedEdge> foundCycle = new ArrayStack<>();

                DirectedEdge f = e;
                while (f.from() != next)
                {
                    foundCycle.push(f);
                    f = edgeTo[f.from()];
                }
                foundCycle.push(f);

                cycle = foundCycle;
                return;
            }
        }
        onStack[vertex] = false;
    }

    public boolean hasCycle()
    { return cycle != null; }

    public Iterable<DirectedEdge> cycle()
    { return cycle; }
}
