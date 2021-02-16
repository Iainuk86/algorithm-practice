package org.iainuk.graph;

import org.iainuk.searching.Bag;

public class Digraph {

    private int edges;
    private int vertices;
    private Bag<Integer>[] adjacentVertices;

    public Digraph(int vertices)
    {
        edges = 0;
        this.vertices = vertices;
        adjacentVertices = (Bag<Integer>[]) new Bag[vertices];

        for (int v = 0; v < vertices; v++)
            adjacentVertices[v] = new Bag<>();
    }

    public int numberOfEdges()
    { return edges; }

    public int numberOfVertices()
    { return vertices; }

    public void addEdge(int from, int to)
    {
        adjacentVertices[from].add(to);
        edges++;
    }

    public Iterable<Integer> adjacent(int vertex)
    { return adjacentVertices[vertex]; }

    public Digraph reverse()
    {
        Digraph reverse = new Digraph(vertices);

        for (int v = 0; v < vertices; v++)
        {
            for (int w : adjacentVertices[v])
                reverse.addEdge(w, v);
        }

        return reverse;
    }
}
