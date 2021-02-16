package org.iainuk.graph.minimumspanningtrees;

public class Edge implements Comparable<Edge> {

    private int from;
    private int to;
    private double weight;

    public Edge(int from, int to, int weight)
    {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int either()
    { return from; }

    public int other(int vertex)
    {
        if      (vertex == from) return to;
        else if (vertex == to) return from;
        else    throw new IllegalArgumentException("Vertex " + vertex + " is not connected");
    }

    public double weight()
    { return weight; }

    public int compareTo(Edge that)
    {
        if (this.weight > that.weight)      return 1;
        else if (this.weight < that.weight) return -1;
        else                                return 0;
    }

    public String toString()
    {
        return String.format("%d-%d, %5.2f", from, to, weight);
    }
}
