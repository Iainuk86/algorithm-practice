package org.iainuk.unionfind;

//TODO Error check and research generics
public class UnionFind {
    private int[] id;
    private int[] size;
    private int count;  // Number of components

    public UnionFind() {
        this(100);
    }

    public UnionFind(int max) {
        this.id = new int[max];
        for (int i = 0; i < max; i++) {
            id[i] = i;
        }

        this.size = new int[max];
        for (int i = 0; i < max; i++) {
            size[i] = 1;
        }
        this.count = max;
    }

    public int components()                 { return this.count; }
    public boolean isEmpty()                { return this.count == 0; }
    public boolean connected(int x, int y)  { return id[x] == id[y]; }

    public int find(int x) {
        while (id[x] != x) {
            x = id[x];
        }
        return x;
    }

    public void union(int x, int y) {
        int i = find(x);
        int j = find(y);
        if (i == j) return;

        if (size[i] < size[j])              { id[i] = j; size[j] += size[i]; }
        else                                { id[j] = i; size[i] += size[j]; }

        count--;
    }
}
