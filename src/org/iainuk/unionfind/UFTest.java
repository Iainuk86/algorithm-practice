package org.iainuk.unionfind;

public class UFTest {
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);

        uf.union(9, 0);
        uf.union(3, 4);
        uf.union(5, 8);
        uf.union(7, 2);
        uf.union(2, 1);
        uf.union(5, 7);
        uf.union(0, 3);
        uf.union(4, 2);
        //poo
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + uf.getId(i));
        }

        System.out.println(uf.connected(0, 3));
        System.out.println(uf.connected(4, 8));

        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + uf.getId(i));
        }
    }
}
