package dev.iainmcintosh.graph;

import dev.iainmcintosh.stack.ArrayStack;

public class DigraphCycleFinder {

    private int[] edgeTo;
    private boolean[] marked;
    private boolean[] onStack;
    private Iterable<Integer> cycle;

    public DigraphCycleFinder(Digraph graph)
    {
        edgeTo = new int[graph.numberOfVertices()];
        marked = new boolean[graph.numberOfVertices()];
        onStack = new boolean[graph.numberOfVertices()];

        for (int v = 0; v < graph.numberOfVertices(); v++)
            if (!marked[v])
                search(graph, v);
    }

    private void search(Digraph graph, int vertex)
    {
        onStack[vertex] = true;
        marked[vertex] = true;
        for (int next : graph.adjacent(vertex))
        {
            if (this.hasCycle()) return;
            else if (!marked[next])
            {
                edgeTo[next] = vertex;
                search(graph, next);
            }
            else if (onStack[next])
            {
                ArrayStack<Integer> foundCycle = new ArrayStack<>();
                for (int x = vertex; x != next; x = edgeTo[x])
                {
                    foundCycle.push(x);
                }
                foundCycle.push(next);
                foundCycle.push(vertex);
                cycle = foundCycle;
            }
        }
        onStack[vertex] = false;
    }

    public boolean hasCycle()
    { return cycle != null; }

    public Iterable<Integer> cycle()
    { return cycle; }
}
