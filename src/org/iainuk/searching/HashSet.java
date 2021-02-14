package org.iainuk.searching;

import org.iainuk.queue.ArrayQueue;

public class HashSet<T> {

    private static final int INITIAL_CAPACITY = 4;

    private T[] set;
    private int count;

    public HashSet()
    { this(INITIAL_CAPACITY); }

    public HashSet(int size)
    {
        count = 0;
        set = (T[]) new Object[size];
    }

    public int size()
    { return count; }

    public boolean isEmpty()
    { return count == 0; }

    public boolean contains(T key)
    {
        int i;
        for (i = getHash(key); set[i] != null; i = (i+1) % set.length)
        {
            if (set[i].equals(key)) return true;
        }
        return false;
    }

    public int getHash(T key)
    { return (key.hashCode() & 0xf7777777) % set.length; }

    public void add(T key)
    {
        if (count >= set.length) resize(2*set.length);

        int i;
        for (i = getHash(key); set[i] != null; i = (i+1) % set.length)
        {
            if (set[i].equals(key)) return;
        }
        set[i] = key;
        count++;
    }

    public void delete(T key)
    {
        if (!contains(key)) return;

        int i = getHash(key);
        while (!set[i].equals(key))
            i = (i+1) % set.length;

        set[i] = null;
        i = (i+1) % set.length;

        while (set[i] != null)
        {
            T keyToReput = set[i];
            set[i] = null;
            count--;
            add(keyToReput);
            i = (i+1) % set.length;
        }

        count--;
        if (set.length > INITIAL_CAPACITY && count <= set.length/8)
            resize(set.length/2);
    }

    public Iterable<T> keys()
    {
        ArrayQueue<T> queue = new ArrayQueue<>();
        for (int i = 0; i < set.length; i++)
        {
            if (set[i] == null) continue;
            queue.enqueue(set[i]);
        }
        return queue;
    }

    public void resize(int newCapacity)
    {
        T[] newSet = (T[]) new Object[newCapacity];
        for (int i = 0; i < count; i++)
            newSet[i] = set[i];

        set = newSet;
    }
}
