package dev.iainmcintosh.graph;

import dev.iainmcintosh.graph.shortestpathtrees.DirectedEdge;
import dev.iainmcintosh.graph.shortestpathtrees.EdgeWeightedDigraph;
import dev.iainmcintosh.queue.ArrayQueue;
import dev.iainmcintosh.stack.ArrayStack;

public class DepthFirstOrder {

    private boolean[] marked;
    private ArrayQueue<Integer> preOrder;
    private ArrayQueue<Integer> postOrder;
    private ArrayStack<Integer> reversePost;

    public DepthFirstOrder(Digraph graph)
    {
        marked = new boolean[graph.numberOfVertices()];
        preOrder = new ArrayQueue<>();
        postOrder = new ArrayQueue<>();
        reversePost = new ArrayStack<>();

        for (int v = 0; v < graph.numberOfVertices(); v++)
            if (!marked[v])
                search(graph, v);
    }

    private void search(Digraph graph, int vertex)
    {
        preOrder.enqueue(vertex);
        marked[vertex] = true;
        for (int next : graph.adjacent(vertex))
        {
            if (!marked[next])
                search(graph, next);
        }

        postOrder.enqueue(vertex);
        reversePost.push(vertex);
    }

    public DepthFirstOrder(EdgeWeightedDigraph graph)
    {
        marked = new boolean[graph.numberOfVertices()];
        preOrder = new ArrayQueue<>();
        postOrder = new ArrayQueue<>();
        reversePost = new ArrayStack<>();

        for (int v = 0; v < graph.numberOfVertices(); v++)
            if (!marked[v])
                search(graph, v);
    }

    private void search(EdgeWeightedDigraph graph, int vertex)
    {
        preOrder.enqueue(vertex);
        marked[vertex] = true;
        for (DirectedEdge e : graph.adjacent(vertex))
        {
            if (!marked[e.to()])
                search(graph, e.to());
        }

        postOrder.enqueue(vertex);
        reversePost.push(vertex);
    }

    public Iterable<Integer> preOrder()
    { return preOrder; }

    public Iterable<Integer> postOrder()
    { return postOrder; }

    public Iterable<Integer> reversePost()
    { return reversePost; }
}
