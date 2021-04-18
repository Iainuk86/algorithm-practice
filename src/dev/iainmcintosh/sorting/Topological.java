package dev.iainmcintosh.sorting;

import dev.iainmcintosh.graph.DepthFirstOrder;
import dev.iainmcintosh.graph.Digraph;
import dev.iainmcintosh.graph.DigraphCycleFinder;
import dev.iainmcintosh.graph.shortestpathtrees.NegativeCycleFinder;
import dev.iainmcintosh.graph.shortestpathtrees.EdgeWeightedDigraph;

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
        NegativeCycleFinder cf = new NegativeCycleFinder(graph);

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
