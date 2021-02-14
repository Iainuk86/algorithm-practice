package org.iainuk.searching;

public class Set<T> {

    private static final int INITIAL_CAPACITY = 4;

    private T[] set;
    private int count;

    public Set()
    {
        this(INITIAL_CAPACITY);
    }

    public Set(int size)
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
        for (int i = 0; i < count; i++)
            if (set[i].equals(key))
                return true;
        return false;
    }

    public void add(T key)
    {
        if (count >= set.length/2) resize(2*set.length);

        int i;
        for (i = 0; i < count; i++)
            if (set[i].equals(key)) return;
        set[i] = key;
        count++;
    }

    public void delete(T key)
    {
        for (int i = 0; i < count; i++)
            if (set[i].equals(key))
            {

            }
    }

    public void resize(int newCapacity)
    {
        T[] newSet = (T[]) new Object[newCapacity];
        for (int i = 0; i < count; i++)
            newSet[i] = set[i];

        set = newSet;
    }

}
