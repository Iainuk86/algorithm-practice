package org.iainuk.symboltable;

import org.iainuk.queue.ArrayQueue;

import java.util.NoSuchElementException;

public class BinarySearchTree<K extends Comparable<K>, V> {

    private Node root;

    public int size()
    { return size(root); }

    private int size(Node x)
    {
        if (x == null)  return 0;
        else            return x.count;
    }

    public boolean isEmpty()
    { return size(root) == 0; }

    public boolean contains(K key)
    { return get(key) != null; }

    public V get(K key)
    {
        Node x = get(root, key);
        if (x == null) return null;
        return x.val;
    }

    private Node get(Node x, K key)
    {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if      (cmp < 0)   return get(x.left, key);
        else if (cmp > 0)   return get(x.right, key);
        else                return x;
    }

    public void put (K key, V value)
    { root = put(root, key, value); }

    private Node put(Node x, K key, V value)
    {
        if (x == null) x = new Node(key, value, 1);

        int cmp = key.compareTo(x.key);
        if      (cmp < 0)   x.left = put(x.left, key, value);
        else if (cmp > 0)   x.right = put(x.right, key, value);
        else                x.val = value;

        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public K min()
    {
        Node x = min(root);
        return x.key;
    }

    private Node min(Node x)
    {
        if (size(root) == 0) throw new NoSuchElementException("Tree is empty");
        if (x.left == null) return x;
        return min(x.left);
    }

    public K max()
    {
        if (size(root) == 0) throw new NoSuchElementException("Tree is empty");
        Node x = max(root);
        return x.key;
    }

    private Node max(Node x)
    {
        if (x.right == null) return x;
        return max(x.right);
    }

    public K floor(K key)
    {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, K key)
    {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if      (cmp == 0)  return x;
        else if (cmp < 0)   return floor(x.left, key);

        Node t = floor(x.right, key);
        if (t != null)  return t;
        else            return x;
    }

    public K ceiling(K key)
    {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x, K key)
    {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if      (cmp == 0)  return x;
        else if (cmp > 0)   return ceiling(x.right, key);

        Node t = ceiling(x.left, key);
        if (t != null)  return t;
        else            return x;
    }

    public K select(int index)
    {
        Node x = select(root, index);
        if (x == null) return null;
        return x.key;
    }

    private Node select(Node x, int index)
    {
        if (x == null) return null;

        int k = size(x.left);
        if      (index < k) return select(x.left, index);
        else if (index > k) return select(x.right, index-k-1);
        else                return x;
    }

    public int rank(K key)
    { return rank(root, key); }

    private int rank(Node x, K key)
    {
        if (x == null) return 0;

        int cmp = key.compareTo(x.key);
        if      (cmp < 0)   return rank(x.left, key);
        else if (cmp > 0)   return 1 + size(x.left) + rank(x.right, key);
        else                return size(x.left);
    }

    public void deleteMin()
    { root = deleteMin(root); }

    private Node deleteMin(Node x)
    {
        if (x.left == null) return x.right;

        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void deleteMax()
    { root = deleteMax(root); }

    private Node deleteMax(Node x)
    {
        if (x.right == null) return x.left;

        x.right = deleteMax(x.right);
        x.count = 1 + size(x.right) + size(x.left);
        return x;
    }

    public void delete(K key)
    { root = delete(root, key); }

    private Node delete(Node x, K key)
    {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else
        {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;

            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }

        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Iterable<K> keys()
    { return keys(min(), max()); }

    public Iterable<K> keys(K lo, K hi)
    {
        ArrayQueue<K> q = new ArrayQueue();
        keys(root, q, lo, hi);
        return q;
    }

    private void keys(Node x, ArrayQueue<K> q, K lo, K hi)
    {
        if (x == null) return;

        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0)                  keys(x.left, q, lo, hi);
        if (cmplo <= 0 && cmphi >= 0)   q.enqueue(x.key);
        if (cmphi > 0)                  keys(x.right, q, lo, hi);
    }

    public int height()
    { return height(root); }

    private int height(Node x)
    {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    private class Node
    {
        private K key;
        private V val;
        private Node left, right;
        private int count;

        public Node(K key, V val, int count)
        {
            this.key = key;
            this.val = val;
            this.count = count;
        }
    }
}
