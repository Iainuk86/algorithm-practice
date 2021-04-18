package dev.iainmcintosh.graph;

public class DigraphDFS {

    private boolean[] marked;

    public DigraphDFS(Digraph graph, int source)
    {
        marked = new boolean[graph.numberOfVertices()];
        search(graph, source);
    }

    private void search(Digraph graph, int vertex)
    {
        marked[vertex] = true;
        for (int w : graph.adjacent(vertex))
        {
            if (!marked[w])
                search(graph, w);
        }
    }

    public boolean connected(int vertex)
    { return marked[vertex]; }
}
