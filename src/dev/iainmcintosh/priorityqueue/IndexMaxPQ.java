package dev.iainmcintosh.priorityqueue;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class IndexMaxPQ<T extends Comparable<T>> implements Iterable<T> {

    private int maxN;
    private int count;
    private int[] heapIndexToUniqueIndex;
    private int[] uniqueIndexToHeapIndex;
    private T[] values;

    public IndexMaxPQ()
    {
        this(65);
    }

    public IndexMaxPQ(int max)
    {
        this.maxN = max;
        this.count = 0;
        this.heapIndexToUniqueIndex = new int[max+1];
        this.uniqueIndexToHeapIndex = new int[max+1];
        this.values = (T[]) new Comparable[max];
        for (int i = 0; i <= max; i++)
        {
            this.uniqueIndexToHeapIndex[i] = -1;
        }
    }

    public IndexMaxPQ(List<T> list)
    {
        int N = list.size();
        this.maxN = N;
        this.count = 0;
        this.heapIndexToUniqueIndex = new int[N+1];
        this.uniqueIndexToHeapIndex = new int[N+1];
        this.values = (T[]) new Comparable[N];
        for (int i = 0; i <= N; i++)
        {
            this.uniqueIndexToHeapIndex[i] = -1;
        }

        for (int i = 0; i < N; i++)
        {
            this.insert(i, list.get(i));
        }
    }

    public IndexMaxPQ(T[] array)
    {
        int N = array.length;
        this.maxN = N;
        this.count = 0;
        this.heapIndexToUniqueIndex = new int[N+1];
        this.uniqueIndexToHeapIndex = new int[N+1];
        this.values = (T[]) new Comparable[N];
        for (int i = 0; i <= N; i++)
        {
            this.uniqueIndexToHeapIndex[i] = -1;
        }

        for (int i = 0; i < N; i++)
        {
            this.insert(i, array[i]);
        }
    }

    public int size()
    { return this.count; }

    public boolean isEmpty()
    { return this.count == 0; }

    public boolean containsIndex(int i)
    {
        validateIndex(i);
        return uniqueIndexToHeapIndex[i] != -1;
    }

    public void insert(int uniqueIndex, T value)
    {
        validateIndex(uniqueIndex);
        if (this.containsIndex(uniqueIndex))
            throw new IllegalArgumentException("Index " + uniqueIndex + " already in use.");

        count++;
        uniqueIndexToHeapIndex[uniqueIndex] = count;
        heapIndexToUniqueIndex[count] = uniqueIndex;
        values[uniqueIndex] = value;
        swim(count);
    }

    public int indexOfMaximumValue()
    {
        if (this.count == 0) throw new NoSuchElementException("Priority queue is empty");
        return heapIndexToUniqueIndex[1];
    }

    public T maxValue()
    {
        if (this.count == 0) throw new NoSuchElementException("Priority queue is empty");
        return values[heapIndexToUniqueIndex[1]];
    }

    public T delMax()
    {
        if (this.count == 0) throw new NoSuchElementException("Priority queue is empty");

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
        validateAndCheckIfExists(i);
        return values[i];
    }

    public void changeValue(int i, T newValue)
    {
        validateAndCheckIfExists(i);

        values[i] = newValue;
        swim(uniqueIndexToHeapIndex[i]);
        sink(this, uniqueIndexToHeapIndex[i]);
    }

    public void delete(int i)
    {
        validateAndCheckIfExists(i);

        int index = uniqueIndexToHeapIndex[i];
        exchange(this, index, count--);
        swim(index);
        sink(this, index);
        values[i] = null;
        uniqueIndexToHeapIndex[i] = -1;
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
            if (j < pq.count && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exchange(pq, k, j);
            k = j;
        }
    }

    private void validateIndex(int index)
    {
        if (index < 0 || index >= this.maxN) throw new IndexOutOfBoundsException();
    }

    private void validateAndCheckIfExists(int index)
    {
        if (index < 0 || index >= this.maxN) throw new IndexOutOfBoundsException();
        if (!this.containsIndex(index)) throw new NoSuchElementException("Index not in priority queue");
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
