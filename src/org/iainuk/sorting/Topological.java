package org.iainuk.sorting;

import org.iainuk.graph.DepthFirstOrder;
import org.iainuk.graph.Digraph;
import org.iainuk.graph.DigraphCycleFinder;

public class Topological {

    private Iterable<Integer> order;

    public Topological(Digraph graph, int source)
    {
        DigraphCycleFinder cf = new DigraphCycleFinder(graph);

        if (!cf.hasCycle())
        {
            DepthFirstOrder dfs = new DepthFirstOrder(graph, source);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> getOrder()
    { return order; }

    public boolean hasNoCycle()
    { return order != null; }
}
