package dev.iainmcintosh.searching;

import java.util.Iterator;

public class Bag<T> implements Iterable<T>{

    private static final int INITIAL_CAPACITY = 4;

    private int count;
    private T[] items;

    public Bag()
    { this(INITIAL_CAPACITY); }

    public Bag(int capacity)
    {
        count = 0;
        items = (T[]) new Object[capacity];
    }

    public int size()
    { return count; }

    public boolean isEmpty()
    { return count == 0; }

    public void add(T key)
    {
        items[count] = key;
        count++;

        if (count >= items.length/2) resize(2*items.length);
    }

    private void resize(int newCapacity)
    {
        T[] newItems = (T[]) new Object[newCapacity];

        for (int i = 0; i < count; i++)
            newItems[i] = items[i];

        items = newItems;
    }

    public Iterator<T> iterator()
    { return new BagIterator(); }

    private class BagIterator implements Iterator<T>
    {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < count;
        }

        @Override
        public T next() {
            T item = items[i];
            i++;
            return item;
        }
    }
}
