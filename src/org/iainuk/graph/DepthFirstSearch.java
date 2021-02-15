package org.iainuk.graph;

public class DepthFirstSearch {

    private int count;
    private boolean[] marked;

    public DepthFirstSearch(UndirectedGraph G, int source)
    {
        count = 0;
        marked = new boolean[G.numberOfVertices()];
        search(G, source);
    }

    private void search(UndirectedGraph G, int vertex)
    {
        marked[vertex] = true;
        count++;
        for (int w : G.adjacent(vertex))
        {
            if (!marked[w])
                search(G, w);
        }
    }

    public int connectedVertices()
    { return count; }

    public boolean connectedToSource(int vertex)
    { return marked[vertex]; }

    public boolean connectedToEachother(int from, int to)
    { return marked[from] && marked[to]; }
}
