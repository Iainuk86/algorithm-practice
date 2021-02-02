package org.iainuk.unionfind;

public class UnionFind {
    private int[] id;       // id[i] = Component containing i
    private int[] size;     // size[i] = Number of sites in tree rooted at i
    private int count;      // Number of components

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
    public boolean connected(int x, int y)  { return find(x) == find(y); }
    public int getId(int x) { return id[x]; }

    /**
     * Returns the root of the component containing {@code x}.
     * While looping through to root, id of each element is changed to root.
     *
     * @param   x an element
     * @return  the root of component containing {@code x}
     * @throws  IllegalArgumentException unless {@code 0 <= x < N}
     */
    public int find(int x) {
        validate(x);
        int root = x;

        while (root != id[root]) {
            root = id[root];
        }

        while (x != root) {
            id[x] = root;
            x = id[x];
        }

        return root;
    }

    /**
     * Merges component containing x with component containing y.
     * Smallest component is added to the largest.
     *
     * @param   x one element
     * @param   y the other element
     * @throws  IllegalArgumentException unless both
     *          {@code 0 <= x < N} and {@code 0 <= y < N}
     *
     */
    public void union(int x, int y) {
        int i = find(x);
        int j = find(y);
        if (i == j) return;

        if (size[i] < size[j])              { id[i] = j; size[j] += size[i]; }
        else                                { id[j] = i; size[i] += size[j]; }

        count--;
    }

    // Validate that argument x is a valid index
    private void validate(int x) {
        int N = id.length;
        if (x < 0 || x >= N) {
            throw new IllegalArgumentException("Index " + x + " must be between 0 and " + (N-1));
        }
    }
}
