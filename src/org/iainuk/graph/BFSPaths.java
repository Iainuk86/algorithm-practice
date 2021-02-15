package org.iainuk.graph;

import org.iainuk.queue.ArrayQueue;
import org.iainuk.stack.ArrayStack;

public class BFSPaths {

    private int count;
    private int source;
    private int[] edgeTo;
    public boolean[] marked;

    public BFSPaths(UndirectedGraph graph, int source)
    {
        count = 0;
        this.source = source;
        marked = new boolean[graph.numberOfVertices()];
        edgeTo = new int[graph.numberOfVertices()];

        search(graph, source);
    }

    private void search(UndirectedGraph graph, int source)
    {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        queue.enqueue(source);
        while (!queue.isEmpty())
        {
            int vertex = queue.dequeue();
            marked[vertex] = true;
            count++;

            for (int w : graph.adjacent(vertex))
            {
                if (!marked[w])
                {
                    marked[w] = true;
                    edgeTo[w] = vertex;
                    queue.enqueue(w);
                }
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
