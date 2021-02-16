package org.iainuk.graph;

public class ConnectedComponents {

    private int count;
    private int[] id;
    private boolean[] marked;

    public ConnectedComponents(UndirectedGraph g)
    {
        count = 0;
        marked = new boolean[g.numberOfVertices()];
        id = new int[g.numberOfVertices()];

        for (int v = 0; v < g.numberOfVertices(); v++)
        {
            if (!marked[v])
                search(g, v);
                count++;
        }
    }

    private void search(UndirectedGraph graph, int vertex)
    {
        marked[vertex] = true;
        id[vertex] = count;
        for (int w : graph.adjacent(vertex))
        {
            if (!marked[w])
                search(graph, w);
        }
    }

    public int numberOfComponents()
    { return count; }

    public boolean connected(int vertex, int otherVertex)
    { return id[vertex] == id[otherVertex]; }

    public int id(int vertex)
    { return id[vertex]; }
}
