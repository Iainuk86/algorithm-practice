package dev.iainmcintosh.graph.shortestpathtrees;

import dev.iainmcintosh.searching.Bag;

public class EdgeWeightedDigraph {

    private int edges;
    private int vertices;
    private Bag<DirectedEdge>[] adjacent;

    public EdgeWeightedDigraph(int vertices)
    {
        edges = 0;
        this.vertices = vertices;
        adjacent = (Bag<DirectedEdge>[]) new Bag[vertices];

        for (int v = 0; v < vertices; v++)
            adjacent[v] = new Bag<DirectedEdge>();
    }

    public int numberOfEdges()
    { return edges; }

    public int numberOfVertices()
    { return vertices; }

    public Iterable<DirectedEdge> adjacent(int vertex)
    { return adjacent[vertex]; }

    public void addEdge(DirectedEdge edge)
    {
        adjacent[edge.from()].add(edge);
        edges++;
    }

    public Iterable<DirectedEdge> edges()
    {
        Bag<DirectedEdge> edges = new Bag<>();

        for (int v = 0; v < vertices; v++)
            for (DirectedEdge e : adjacent(v))
                edges.add(e);

        return edges;
    }
}
