package org.iainuk.searching;

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
    }

    public void delete(T key)
    {

    }

    public void resize(int newCapacity)
    {
        T[] newSet = (T[]) new Object[newCapacity];
        for (int i = 0; i < count; i++)
            newSet[i] = set[i];

        set = newSet;
    }
}
