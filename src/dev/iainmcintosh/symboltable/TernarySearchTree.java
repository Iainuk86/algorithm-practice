package dev.iainmcintosh.symboltable;

import dev.iainmcintosh.queue.ArrayQueue;

public class TernarySearchTree<V> {

    private Node root;
    private int count;

    public int size()
    { return count; }

    public boolean isEmpty()
    { return count == 0; }

    public boolean contains(String key)
    { return get(key) != null; }

    public V get(String key)
    {
        Node x = get(root, key, 0);
        if (x == null || x.value == null) return null;
        return x.value;
    }

    private Node get(Node x, String key, int digit)
    {
        if (x == null) return null;

        char c = key.charAt(digit);
        if      (c < x.c)                   return get(x.left, key, digit);
        else if (c > x.c)                   return get(x.right, key, digit);
        else if (digit < key.length()-1)    return get(x.mid, key, digit+1);
        else                                return x;
    }

    public void put(String key, V value)
    {
        if (key.length() == 0) throw new IllegalArgumentException("String can not be empty");
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, V value, int digit)
    {
        char c = key.charAt(digit);
        if (x == null)
        {
            x = new Node();
            x.c = c;
        }

        if      (c < x.c)                    x.left = put(x.left, key, value, digit);
        else if (c > x.c)                    x.right = put(x.right, key, value, digit);
        else if (digit < key.length()-1)     x.mid = put(x.mid, key, value, digit+1);
        else if (x.value != null)
        {
            x.value = value; return x;
        }
        else
        {
            x.value = value;
            count++;
        }
        return x;
    }

    public void delete(String key)
    { root = delete(root, key, 0); }

    private Node delete(Node x, String key, int digit)
    {
        // TODO test this, may need more work
        if (x == null) return null;

        if (digit == key.length()) x.value = null;
        else
        {
            char c = key.charAt(digit);
            if      (c < x.c)                   x.left = delete(x.left, key, digit);
            else if (c > x.c)                   x.right = delete(x.right, key, digit);
            else if (digit < key.length()-1)    x.mid = delete(x.mid, key, digit+1);
        }

        if (x.left != null || x.right != null || x.mid != null)
            return x;

        if (x.value != null)
            return x;

        return null;
    }

    public String longestPrefixOf(String key)
    {
        int length = search(root, key, 0, 0);
        return key.substring(0, length);
    }

    public Iterable<String> keysWithPrefix(String pre)
    {
        ArrayQueue<String> queue = new ArrayQueue<>();
        collect(get(root, pre, 0), pre, queue);
        return queue;
    }

    public Iterable<String> keysThatMatch(String key)
    {
        ArrayQueue<String> queue = new ArrayQueue<>();
        collect(root, "", key, queue);
        return queue;
    }

    public Iterable<String> keys()
    { return keysWithPrefix(""); }


    private int search(Node x, String key, int digit, int length)
    {
        if (x == null) return length;
        if (x.value != null) length = digit;
        if (digit == key.length()) return length;

        char c = key.charAt(digit);
        if      (c < x.c)                   return search(x.left, key, digit, length);
        else if (c > x.c)                   return search(x.right, key, digit, length);

        return search(x.mid, key, digit+1, length);
    }

    private void collect(Node x, String pre, ArrayQueue<String> queue)
    {
        if (x == null) return;
        if (x.value != null) queue.enqueue(pre);

        collect(x.left, pre + x.left.c, queue);
        collect(x.mid, pre + x.mid.c, queue);
        collect(x.right, pre + x.right.c, queue);
    }

    private void collect(Node x, String pre, String key, ArrayQueue<String> queue)
    {
        if (x == null) return;

        int digit = pre.length();
        if (digit == key.length() && x.value != null) queue.enqueue(pre);
        if (digit == key.length()) return;

        char c = key.charAt(digit);
        if (c == '?')
        {
            collect(x.left, pre + x.left.c, key, queue);
            collect(x.mid, pre + x.mid.c, key, queue);
            collect(x.right, pre + x.right.c, key, queue);
        }

        else if (c < x.c) collect(x.left, pre, key, queue);
        else if (c > x.c) collect(x.right, pre, key, queue);
        else collect(x.mid, pre + x.mid.c, key, queue);
    }

    private class Node
    {
        private char c;
        private V value;
        private Node left, right, mid;
    }
}
