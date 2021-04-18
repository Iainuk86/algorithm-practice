package dev.iainmcintosh.graph;

import dev.iainmcintosh.stack.ArrayStack;

public class DepthFirstPaths {

    private int count;
    private int source;
    private int[] edgeTo;
    public boolean[] marked;

    public DepthFirstPaths(UndirectedGraph graph, int source)
    {
        count = 0;
        this.source = source;
        marked = new boolean[graph.numberOfVertices()];
        edgeTo = new int[graph.numberOfVertices()];

        search(graph, source);
    }

    private void search(UndirectedGraph graph, int vertex)
    {
        marked[vertex] = true;
        count++;
        for (int w : graph.adjacent(vertex))
        {
            if (!marked[w])
            {
                edgeTo[w] = vertex;
                search(graph, w);
            }
        }
    }

    public int source()
    { return source; }

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
