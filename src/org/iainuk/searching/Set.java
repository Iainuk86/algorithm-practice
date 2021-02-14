package org.iainuk.searching;

import java.util.Iterator;

public class Set<T> implements Iterable<T> {

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
        if (!contains(key)) return;

        for (int i = 0; i < count; i++)
            if (set[i].equals(key))
            {
                while (set[i] != null)
                    set[i] = set[i+1];
            }

        count--;
        if (set.length > INITIAL_CAPACITY && count <= set.length/8)
            resize(set.length/2);
    }

    public void resize(int newCapacity)
    {
        T[] newSet = (T[]) new Object[newCapacity];
        for (int i = 0; i < count; i++)
            newSet[i] = set[i];

        set = newSet;
    }

    public Iterator<T> iterator()
    { return new SetIterator(); }

    private class SetIterator implements Iterator<T>
    {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return set[index] != null;
        }

        @Override
        public T next() {
            T key = set[index];
            index++;
            return key;
        }
    }

}
