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

        exchange(this,1, count--);
        sink(this,1);
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
        sink(this, uniqueIndexToHeapIndex[i]);
    }

    public void delete(int i)
    {
        // validate i
        // if not contains
        int index = uniqueIndexToHeapIndex[i];
        exchange(this, index, count--);
        swim(index);
        sink(this, index);
        values[i] = null;
        uniqueIndexToHeapIndex[i] = -1;
    }

    private void validateIndex(int i)
    {
        // TODO
    }

    public T[] asSortedArray()
    {
        T[] sorted = (T[]) new Comparable[maxN];
        IndexMaxPQ<T> copy = new IndexMaxPQ<>(maxN);

        for (int i = 1; i <= count; i++)
            copy.insert(heapIndexToUniqueIndex[i], values[heapIndexToUniqueIndex[i]]);

        int index = 0;
        for (Object o : copy) {
            sorted[index++] = copy.delMax();
        }
        return sorted;

    }

    private boolean less(IndexMaxPQ pq, int i, int j)
    { return pq.values[pq.heapIndexToUniqueIndex[i]]
                .compareTo(pq.values[pq.heapIndexToUniqueIndex[j]]) < 0; }

    private void exchange(IndexMaxPQ pq, int i, int j)
    {
        int temp = pq.heapIndexToUniqueIndex[i];
        pq.heapIndexToUniqueIndex[i] = pq.heapIndexToUniqueIndex[j];
        pq.heapIndexToUniqueIndex[j] = temp;

        pq.uniqueIndexToHeapIndex[pq.heapIndexToUniqueIndex[i]] = i;
        pq.uniqueIndexToHeapIndex[pq.heapIndexToUniqueIndex[j]] = j;
    }

    private void swim(int k)
    {
        while (k > 1 && less(this,k/2, k))
        {
            exchange(this, k, k/2);
            k = k/2;
        }
    }

    private void sink(IndexMaxPQ pq, int k)
    {
        while (2*k <= pq.count)
        {
            int j = 2*k;
            if (j < pq.count && less(pq, j, j+1))   j++;
            if (!less(pq, k, j))                     return;
            exchange(pq, k, j);
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
            int N = heapIndexToUniqueIndex.length;
            copy = new IndexMaxPQ<>(N-1);
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
