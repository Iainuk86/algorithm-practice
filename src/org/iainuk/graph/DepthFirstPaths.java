package org.iainuk.graph;

import org.iainuk.stack.ArrayStack;

public class DepthFirstPaths {

    private int count;
    private int source;
    private int[] edgeTo;
    public boolean[] marked;

    public DepthFirstPaths(UndirectedGraph G, int source)
    {
        count = 0;
        this.source = source;
        marked = new boolean[G.numberOfVertices()];
        edgeTo = new int[G.numberOfVertices()];

        search(G, source);
    }

    private void search(UndirectedGraph G, int vertex)
    {
        marked[vertex] = true;
        count++;
        for (int w : G.adjacent(vertex))
        {
            if (!marked[w])
            {
                edgeTo[w] = vertex;
                search(G, w);
            }
        }
    }

    public int connectedVertices()
    { return count; }

    public boolean hasPathTo(int vertex)
    { return marked[vertex]; }

    public Iterable<Integer> pathTo(int vertex)
    {
        if (!hasPathTo(vertex)) throw new IllegalArgumentException("No path to vertex " + vertex + " found.");

        ArrayStack<Integer> path = new ArrayStack<>();
        for (int x = vertex; x != source; x = edgeTo[x])
        {
            path.push(x);
        }

        path.push(source);
        return path;
    }
}
