package org.iainuk.symboltable;

public class KeyValue<K extends Comparable<K>, V> {

    private K key;
    private V value;

    public KeyValue(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    public K getKey()
    { return this.key; }

    public V getValue()
    { return this.value; }

    public void setKey(K key)
    { this.key = key; }

    public void setValue(V value)
    { this.value = value; }
}
