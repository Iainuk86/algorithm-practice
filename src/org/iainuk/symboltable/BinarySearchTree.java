package org.iainuk.symboltable;

public class BinarySearchTree<K extends Comparable<K>, V> {

    private Node root;

    


    private class Node
    {
        private K key;
        private V val;
        private Node left, right;
        private int count;

        public Node(K key, V val, int n)
        {
            this.key = key;
            this.val = val;
            this.count = n;
        }
    }
}
