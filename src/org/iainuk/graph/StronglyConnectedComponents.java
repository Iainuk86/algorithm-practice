package org.iainuk.graph;

public class StronglyConnectedComponents {

    private int count;
    private int[] id;
    private boolean[] marked;

    public StronglyConnectedComponents(Digraph graph)
    {
        count = 0;
        id = new int[graph.numberOfVertices()];
        marked = new boolean[graph.numberOfVertices()];

        DepthFirstOrder dfs = new DepthFirstOrder(graph.reverse());

        for (int vertex : dfs.reversePost())
        {
            if (!marked[vertex])
                search(graph, vertex);
                count++;
        }
    }

    private void search(Digraph graph, int vertex)
    {
        marked[vertex] = true;
        id[vertex] = count;

        for (int next : graph.adjacent(vertex))
        {
            if (!marked[next])
                search(graph, next);
        }
    }

    public int id(int vertex)
    { return id[vertex]; }

    public int numberOfComponents()
    { return count; }

    public boolean stronglyConnected(int vertex, int otherVertex)
    { return id[vertex] == id[otherVertex]; }
}
