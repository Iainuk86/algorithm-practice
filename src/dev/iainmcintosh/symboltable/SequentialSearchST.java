package dev.iainmcintosh.symboltable;

import dev.iainmcintosh.queue.ArrayQueue;

public class SequentialSearchST<K, V> {

    private int count;
    private Node first;

    public SequentialSearchST() {}

    public int size()
    { return count; }

    public boolean isEmpty()
    { return count == 0; }

    public boolean contains(K key)
    { return get(key) != null; }

    public V get(K key)
    {
        for (Node x = first; x != null; x = x.next)
        {
            if (x.key.equals(key))
                return x.value;
        }
        return null;
    }

    public void put(K key, V value)
    {
        if (value == null)
        {
            delete(key);
            return;
        }

        for (Node x = first; x != null; x = x.next)
        {
            if (x.key.equals(key))
            {
                x.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        count++;
    }

    public void delete(K key)
    {
        first = delete(first, key);
    }

    private Node delete(Node x, K key)
    {
        if (x == null) return null;
        if (key.equals(x.key))
        {
            count--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public Iterable<K> keys()  {
        ArrayQueue<K> queue = new ArrayQueue<>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }

    private class Node
    {
        private K key;
        private V value;
        private Node next;

        public Node(K key, V value, Node next)
        {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
