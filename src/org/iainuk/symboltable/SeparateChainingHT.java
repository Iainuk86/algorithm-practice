package org.iainuk.symboltable;

import org.iainuk.queue.ArrayQueue;

public class SeparateChainingHT<K, V> {
    private static final int INITIAL_CAPACITY = 4;

    private int count;
    private int tableSize;
    private SequentialSearchST<K, V>[] table;

    public SeparateChainingHT()
    { this(INITIAL_CAPACITY); }

    public SeparateChainingHT(int tableSize)
    {
        count = 0;
        this.tableSize = tableSize;
        table = (SequentialSearchST<K, V>[]) new SequentialSearchST[tableSize];

        for (int i = 0; i < tableSize; i++)
        {
            table[i] = new SequentialSearchST<>();
        }
    }

    public int size()
    { return count; }

    public boolean isEmpty()
    { return count == 0;}

    public boolean contains(K key)
    { return get(key) != null; }

    public int hash(K key)
    { return (key.hashCode() & 0x7fffffff) % tableSize; }

    public V get(K key)
    {
        int hash = hash(key);
        return table[hash].get(key);
    }

    public void put(K key, V value)
    {
        if (count >= 10*tableSize) resize(2*tableSize);

        int hash = hash(key);
        table[hash].put(key, value);
    }

    public void delete(K key)
    {
        int hash = hash(key);
        if (table[hash].contains(key))
        {
            table[hash].delete(key);
            count--;
        }

        if (tableSize > INITIAL_CAPACITY && count <= tableSize*2)
            resize(tableSize/2);
    }

    public Iterable<K> keys()
    {
        ArrayQueue<K> queue = new ArrayQueue<>();
        for (int i = 0; i < tableSize; i++)
        {
            for (K key : table[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }

    private void resize(int newCapacity)
    {
        SeparateChainingHT<K, V> temp = new SeparateChainingHT<>(newCapacity);
        for (int i = 0; i < tableSize; i++) {
            for (K key : table[i].keys()) {
                temp.put(key, table[i].get(key));
            }
        }
        this.tableSize = temp.tableSize;
        this.count = temp.count;
        this.table = temp.table;
    }
}
