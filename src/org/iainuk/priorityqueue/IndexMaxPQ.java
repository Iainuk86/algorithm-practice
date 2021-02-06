package org.iainuk.priorityqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMaxPQ<T extends Comparable<T>> implements Iterable<T> {
    private int maxN;
    private int count;
    private int[] heapIndexToUniqueIndex;
    private int[] uniqueIndexToHeapIndex; // used to support contains functionality
    private T[] values;

    public IndexMaxPQ(int max)
    {
        this.maxN = max;
        this.count = 0;
        this.heapIndexToUniqueIndex = new int[max+1];
        this.uniqueIndexToHeapIndex = new int[max+1];
        this.values = (T[]) new Comparable[max+1];
        for (int i = 0; i <= max; i++)
        {
            uniqueIndexToHeapIndex[i] = -1;
        }
    }

    public int size()
    { return this.count; }

    public boolean isEmpty()
    { return this.count == 0; }

    public boolean containsIndex(int i)
    {
        // validate
        return uniqueIndexToHeapIndex[i] != -1;
    }

    public void insert(int uniqueIndex, T value)
    {
        // validate
        // if contains
        count++;
        uniqueIndexToHeapIndex[uniqueIndex] = count;
        heapIndexToUniqueIndex[count] = uniqueIndex;
        values[uniqueIndex] = value;
        swim(count);
    }

    public int indexOfMaximumValue()
    {
        // if n == 0
        return heapIndexToUniqueIndex[1];
    }

    public T maxValue()
    {
        // if n == 0
        return values[heapIndexToUniqueIndex[1]];
    }

    public T delMax()
    {
        // if n == 0
        int indexOfMax = heapIndexToUniqueIndex[1];
        T maxValue = values[indexOfMax];

        exchange(1, count--);
        sink(1);
        assert indexOfMax == heapIndexToUniqueIndex[count+1];

        values[indexOfMax] = null;
        heapIndexToUniqueIndex[count+1] = -1;
        uniqueIndexToHeapIndex[indexOfMax] = -1;
        return maxValue;
    }

    public T valueOf(int i)
    {
        // validate i
        // if not contains i
        return values[i];
    }

    public void changeValue(int i, T newValue)
    {
        // validate i
        // if not contains i
        values[i] = newValue;
        swim(uniqueIndexToHeapIndex[i]);
        sink(uniqueIndexToHeapIndex[i]);
    }

    public void delete(int i)
    {
        // validate i
        // if not contains
        int index = uniqueIndexToHeapIndex[i];
        exchange(index, count--);
        swim(index);
        sink(index);
        values[i] = null;
        uniqueIndexToHeapIndex[i] = -1;
    }

    private void validateIndex(int i)
    {
        // TODO
    }

    private boolean less(int i, int j)
    { return values[heapIndexToUniqueIndex[i]].compareTo(values[heapIndexToUniqueIndex[j]]) < 0; }

    private void exchange(int i, int j)
    {
        int temp = heapIndexToUniqueIndex[i];
        heapIndexToUniqueIndex[i] = heapIndexToUniqueIndex[j];
        heapIndexToUniqueIndex[j] = temp;

        uniqueIndexToHeapIndex[heapIndexToUniqueIndex[i]] = i;
        uniqueIndexToHeapIndex[heapIndexToUniqueIndex[j]] = j;
    }

    private void swim(int k)
    {
        while (k > 1 && less(k/2, k))
        {
            exchange(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k)
    {
        while (2*k <= count)
        {
            int j = 2*k;
            if (j < count && less(j, j+1))   j++;
            if (!less(k, j))                     return;
            exchange(k, j);
            k = j;
        }
    }

    public Iterator<T> iterator()
    { return new HeapIterator(); }

    private class HeapIterator implements Iterator<T>
    {
        IndexMaxPQ<T> copy;

        public HeapIterator()
        {
            copy = new IndexMaxPQ<T>(heapIndexToUniqueIndex.length-1);
            for (int i = 1; i <= count; i++)
                copy.insert(heapIndexToUniqueIndex[i], values[heapIndexToUniqueIndex[i]]);
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }


}
