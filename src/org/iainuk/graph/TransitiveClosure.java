package org.iainuk.graph;

public class TransitiveClosure {

    private DigraphDFS[] all;

    public TransitiveClosure(Digraph graph)
    {
        all = new DigraphDFS[graph.numberOfVertices()];

        for (int v = 0; v < graph.numberOfVertices(); v++)
        {
            all[v] = new DigraphDFS(graph, v);
        }
    }

    public boolean connected(int vertex, int otherVertex)
    { return all[vertex].connected(otherVertex); }
}
