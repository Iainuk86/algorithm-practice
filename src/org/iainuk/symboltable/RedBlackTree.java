package org.iainuk.symboltable;

import java.util.NoSuchElementException;

public class RedBlackTree<K extends  Comparable<K>, V> {

    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

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
        return x.value;
    }

    private Node get(Node x, K key)
    {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if      (cmp < 0)   return get(x.left, key);
        else if (cmp > 0)   return get(x.right, key);
        else                return x;
    }

    public void put(K key, V value)
    {
        root = put(root, key, value);
        root.colour = BLACK;
    }

    private Node put(Node x, K key, V value)
    {
        if (x == null) x = new Node(key, value, 1, RED);

        int cmp = key.compareTo(x.key);
        if      (cmp < 0)   x.left = put(x.left, key, value);
        else if (cmp > 0)   x.right = put(x.right, key, value);
        else                x.value = value;

        if (isRed(x.right) && !isRed(x.left))       x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left))    x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right))        flipColours(x);

        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public K min()
    {
        if (size(root) == 0) throw new NoSuchElementException("Tree is empty");
        Node x = min(root);
        return x.key;
    }

    private Node min(Node x)
    {
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
    {
        if (isEmpty()) throw new NoSuchElementException("Tree is empty");

        if (!isRed(root.left) && !isRed(root.right))
            root.colour = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.colour = BLACK;
    }

    private Node deleteMin(Node x)
    {
        if (x.left == null) return null;

        if (!isRed(x.left) && !isRed(x.left.left))
            x = moveRedLeft(x);

        x.left = deleteMin(x.left);
        return balance(x);
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Tree is empty");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.colour = RED;

        root = deleteMax(root);
        if (!isEmpty()) root.colour = BLACK;
        // assert check();
    }

    // delete the key-value pair with the maximum key rooted at h
    private Node deleteMax(Node h) {
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
    }

    public void delete(K key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.colour = RED;

        root = delete(root, key);
        if (!isEmpty()) root.colour = BLACK;
        // assert check();
    }

    // delete the key-value pair with the given key rooted at x
    private Node delete(Node x, K key) {
        // assert get(x, key) != null;

        if (key.compareTo(x.key) < 0)  {
            if (!isRed(x.left) && !isRed(x.left.left))
                x = moveRedLeft(x);
            x.left = delete(x.left, key);
        }
        else {
            if (isRed(x.left))
                x = rotateRight(x);
            if (key.compareTo(x.key) == 0 && (x.right == null))
                return null;
            if (!isRed(x.right) && !isRed(x.right.left))
                x = moveRedRight(x);
            if (key.compareTo(x.key) == 0) {
                Node t = min(x.right);
                x.key = t.key;
                x.value = t.value;
                // x.val = get(x.right, min(x.right).key);
                // x.key = min(x.right).key;
                x.right = deleteMin(x.right);
            }
            else x.right = delete(x.right, key);
        }
        return balance(x);
    }

    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    private Node moveRedLeft(Node x)
    { // Assuming that h is red and both h.left and h.left.left
        // are black, make h.left or one of its children red.
        flipColours(x);
        if (isRed(x.right.left))
        {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColours(x);
        }
        return x;
    }

    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColours(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColours(h);
        }
        return h;
    }

    private Node balance(Node x)
    {
        if (isRed(x.right) && !isRed(x.left))       x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left))    x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right))        flipColours(x);

        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    private boolean isRed(Node x)
    {
        if (x == null) return false;
        return x.colour == RED;
    }

    private Node rotateLeft(Node old)
    {
        Node x = old.right;
        old.right = x.left;
        x.left = old;
        x.colour = old.colour;
        old.colour = RED;
        x.count = old.count;
        old.count = 1 + size(old.left) + size(old.right);
        return x;
    }

    private Node rotateRight(Node old)
    {
        Node x = old.left;
        old.left = x.right;
        x.right = old;
        x.colour = old.colour;
        old.colour = RED;
        x.count = old.count;
        old.count = 1 + size(old.left) + size(old.right);
        return x;
    }

    private void flipColours(Node x)
    {
        x.colour = !x.colour;
        x.left.colour = !x.left.colour;
        x.right.colour = !x.right.colour;
    }

    private class Node
    {
        private K key;
        private V value;
        private Node left, right;
        private int count;
        private boolean colour;

        public Node(K key, V value, int count, boolean colour)
        {
            this.key = key;
            this.value = value;
            this.count = count;
            this.colour = colour;
        }
    }
}
