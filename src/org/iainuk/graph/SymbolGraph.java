package org.iainuk.graph;

import org.iainuk.symboltable.LinearProbingHT;

public class SymbolGraph {

    private LinearProbingHT<String, Integer> st;
    private UndirectedGraph graph;
    private String[] keys;

    public SymbolGraph(int vertices)
    {
        st = new LinearProbingHT<>(vertices);
        keys = new String[vertices];
        graph = new UndirectedGraph(vertices);
    }

    public void addEdge(String from, String to)
    {
        if (!st.contains(from))
        {
            keys[st.size()] = from;
            st.put(from, st.size());
        }

        if (!st.contains(to))
        {
            keys[st.size()] = to;
            st.put(to, st.size());
        }

        graph.addEdge(st.get(from), st.get(to));
    }

    public boolean contains(String key)
    { return st.contains(key); }

    public int index(String key)
    { return st.get(key); }

    public String name(int vertex)
    { return keys[vertex]; }

    public UndirectedGraph getGraph()
    { return graph; }
}
