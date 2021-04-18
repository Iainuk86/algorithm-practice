package dev.iainmcintosh.graph;

public class DepthFirstSearch {

    private int count;
    private boolean[] marked;

    public DepthFirstSearch(UndirectedGraph graph, int source)
    {
        count = 0;
        marked = new boolean[graph.numberOfVertices()];
        search(graph, source);
    }

    private void search(UndirectedGraph graph, int vertex)
    {
        marked[vertex] = true;
        count++;
        for (int w : graph.adjacent(vertex))
        {
            if (!marked[w])
                search(graph, w);
        }
    }

    public int connectedVertices()
    { return count; }

    public boolean connectedToSource(int vertex)
    { return marked[vertex]; }

    public boolean connectedToEachOther(int from, int to)
    { return marked[from] && marked[to]; }
}
