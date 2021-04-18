package dev.iainmcintosh.symboltable;

import dev.iainmcintosh.queue.ArrayQueue;

public class Trie<V> {

    private int count;
    private Node root;
    private static final int R = 256;

    public int size()
    { return count; }

    public boolean isEmpty()
    { return count == 0; }

    public boolean contains(String key)
    { return get(key) != null; }

    public V get(String key)
    {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (V) x.value;
    }

    private Node get(Node x, String key, int digit)
    {
        if (x == null) return null;
        if (digit == key.length() && x.value != null) return x;
        if (digit == key.length()) return null;

        char c = key.charAt(digit);
        return get(x.next[c], key, digit+1);
    }

    public void put(String key, V value)
    { root = put(root, key, value, 0); }

    private Node put(Node x, String key, V value, int digit)
    {
        if (x == null) x = new Node();
        // its not length-1 because you're starting at the root
        if (digit == key.length() && x.value != null)
        {
            x.value = value; return x;
        }

        if (digit == key.length())
        {
            x.value = value;
            count++;
            return x;
        }

        char c = key.charAt(digit);
        x.next[c] = put(x.next[c], key, value, digit+1);
        return x;
    }

    public void delete(String key)
    { root = delete(root, key, 0); }

    private Node delete(Node x, String key, int digit)
    {
        if (x == null) return null;

        if (digit == key.length()) x.value = null;
        else
        {
            char c = key.charAt(digit);
            x.next[c] = delete(x.next[c], key, digit+1);
        }

        for (char r = 0; r < R; r++)
            if (x.next[r] != null)
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

    private void collect(Node x, String pre, ArrayQueue<String> queue)
    {
        if (x == null) return;
        if (x.value != null) queue.enqueue(pre);

        for (char c = 0; c < R; c++)
            collect(x.next[c], pre+c, queue);
    }

    private void collect(Node x, String pre, String key, ArrayQueue<String> queue)
    {
        if (x == null) return;

        int digit = pre.length();
        if (digit == key.length() && x.value != null) queue.enqueue(pre);
        if (digit == key.length()) return;

        char next = key.charAt(digit);
        for (char c = 0; c < R; c++)
        {
            if (next == c || next == '?') // Whatever token is used for wildcard
                collect(x.next[c], pre+c, key, queue);
        }
    }

    private int search(Node x, String key, int digit, int length)
    {
        if (x == null) return length;
        if (x.value != null) length = digit;
        if (digit == key.length()) return length;

        char c = key.charAt(digit);
        return search(x.next[c], key, digit+1, length);
    }

    private static class Node
    {
        private Object value;
        private Node[] next = new Node[R];
    }
}
