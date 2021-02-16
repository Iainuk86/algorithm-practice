package org.iainuk.graph.minimumspanningtrees;

import org.iainuk.searching.Bag;

public class EdgeWeightedGraph {

    private int edges;
    private int vertices;
    private Bag<Edge>[] adjacentVertices;

    public EdgeWeightedGraph(int vertices)
    {
        edges = 0;
        this.vertices = vertices;
        adjacentVertices = (Bag<Edge>[]) new Bag[vertices];

        for (int v = 0; v < vertices; v++)
            adjacentVertices[v] = new Bag<>();
    }

    public int numberOfEdges()
    { return edges; }

    public int numberOfVertices()
    { return vertices; }

    public void addEdge(Edge edge)
    {
        int from = edge.either();
        int to = edge.other(from);

        adjacentVertices[from].add(edge);
        adjacentVertices[to].add(edge);
        edges++;
    }

    public Iterable<Edge> adjacent(int vertex)
    { return adjacentVertices[vertex]; }

    public Iterable<Edge> edges()
    {
        Bag<Edge> edges = new Bag<>();

        for (int v = 0; v < vertices; v++)
            for (Edge e : adjacentVertices[v])
                if (e.other(v) > v) edges.add(e);

        return edges;
    }
}
