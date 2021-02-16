package org.iainuk.sorting;

import org.iainuk.graph.DepthFirstOrder;
import org.iainuk.graph.Digraph;
import org.iainuk.graph.DigraphCycleFinder;
import org.iainuk.graph.shortestpathtrees.EdgeWeightedCycleFinder;
import org.iainuk.graph.shortestpathtrees.EdgeWeightedDigraph;

public class Topological {

    private Iterable<Integer> order;

    public Topological(Digraph graph)
    {
        DigraphCycleFinder cf = new DigraphCycleFinder(graph);

        if (!cf.hasCycle())
        {
            DepthFirstOrder dfs = new DepthFirstOrder(graph);
            order = dfs.reversePost();
        }
    }

    public Topological(EdgeWeightedDigraph graph)
    {
        EdgeWeightedCycleFinder cf = new EdgeWeightedCycleFinder(graph);

        if (!cf.hasCycle())
        {
            DepthFirstOrder dfs = new DepthFirstOrder(graph);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> getOrder()
    { return order; }

    public boolean hasNoCycle()
    { return order != null; }
}
