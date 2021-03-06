package dev.iainmcintosh.symboltable;

import dev.iainmcintosh.queue.ArrayQueue;

public class LinearProbingHT<K, V> {
    private static final int INITIAL_CAPACITY = 4;

    private int count;
    private int tableSize;
    private K[] keys;
    private V[] values;

    public LinearProbingHT()
    { this(INITIAL_CAPACITY); }

    public LinearProbingHT(int tableSize)
    {
        count = 0;
        this.tableSize = tableSize;
        keys =      (K[])  new Object[tableSize];
        values =    (V[])  new Object[tableSize];
    }

    public int size()
    { return count; }

    public boolean isEmpty()
    { return count == 0; }

    public boolean contains(K key)
    { return get(key) != null; }

    public int getHash(K key)
    { return (key.hashCode() & 0x7fffffff) % tableSize; }

    public V get(K key)
    {
        for (int i = getHash(key); keys[i] != null; i = (i+1) % tableSize)
        {
            if (keys[i].equals(key)) return values[i];
        }
        return null;
    }

    public void put(K key, V value)
    {
        if (count >= tableSize/2)   resize(2*tableSize);

        int i;
        for (i = getHash(key); keys[i] != null; i = (i+1) % tableSize)
        {
            if (keys[i].equals(key)) { values[i] = value; return; }
        }
        keys[i] = key;
        values[i] = value;
        count++;
    }

    public void delete(K key)
    {
        if (!contains(key)) return;

        int i = getHash(key);
        while (!keys[i].equals(key))
        {
            i = (i+1) % tableSize;
        }

        keys[i] = null;
        values[i] = null;
        i = (i+1) % tableSize;

        while (keys[i] != null)
        {
            K keyToReput = keys[i];
            V valueToReput = values[i];
            keys[i] = null;
            values[i] = null;
            count--;
            put(keyToReput, valueToReput);
            i = (i+1) % tableSize;
        }
        count--;

        if (count > 0 && count <= tableSize/8) resize(tableSize/2);
    }

    public Iterable<K> keys()
    {
        ArrayQueue<K> queue = new ArrayQueue<>();
        for (int i = 0; i < tableSize; i++)
        {
            if (keys[i] != null) queue.enqueue(keys[i]);
        }
        return queue;
    }

    public void resize(int newSize)
    {
        K[] newKeys = (K[]) new Object[newSize];
        V[] newVals = (V[]) new Object[newSize];

        for (int i = 0; i < tableSize; i++)
        {
            newKeys[i] = keys[i];
            newVals[i] = values[i];
        }

        tableSize = newSize;
        keys = newKeys;
        values = newVals;
    }
}
