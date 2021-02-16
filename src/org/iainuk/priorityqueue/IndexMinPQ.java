package org.iainuk.priorityqueue;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class IndexMinPQ<T extends Comparable<T>> implements Iterable<T> {

    private int maxN;
    private int count;
    private int[] heapIndexToUniqueIndex;
    private int[] uniqueIndexToHeapIndex;
    private T[] values;

    public IndexMinPQ()
    {
        this(65);
    }

    public IndexMinPQ(int max)
    {
        maxN = max;
        count = 0;
        heapIndexToUniqueIndex = new int[max+1];
        uniqueIndexToHeapIndex = new int[max+1];
        values = (T[]) new Comparable[max];
        for (int i = 0; i <= max; i++)
        {
            uniqueIndexToHeapIndex[i] = -1;
        }
    }

    public IndexMinPQ(List<T> list)
    {
        int N = list.size();
        maxN = N;
        count = 0;
        heapIndexToUniqueIndex = new int[N+1];
        uniqueIndexToHeapIndex = new int[N+1];
        values = (T[]) new Comparable[N];
        for (int i = 0; i <= N; i++)
        {
            uniqueIndexToHeapIndex[i] = -1;
        }

        for (int i = 0; i < N; i++)
        {
            this.insert(i, list.get(i));
        }
    }

    public IndexMinPQ(T[] array)
    {
        int N = array.length;
        maxN = N;
        count = 0;
        heapIndexToUniqueIndex = new int[N+1];
        uniqueIndexToHeapIndex = new int[N+1];
        values = (T[]) new Comparable[N];
        for (int i = 0; i <= N; i++)
        {
            uniqueIndexToHeapIndex[i] = -1;
        }

        for (int i = 0; i < N; i++)
        {
            this.insert(i, array[i]);
        }
    }

    public int size()
    { return count; }

    public boolean isEmpty()
    { return count == 0; }

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

    public int indexOfMinimumValue()
    {
        if (count == 0) throw new NoSuchElementException("Priority queue is empty");
        return heapIndexToUniqueIndex[1];
    }

    public T minValue()
    {
        if (count == 0) throw new NoSuchElementException("Priority queue is empty");
        return values[heapIndexToUniqueIndex[1]];
    }

    public T delMin()
    {
        if (count == 0) throw new NoSuchElementException("Priority queue is empty");

        int indexOfMin = heapIndexToUniqueIndex[1];
        T minValue = values[indexOfMin];

        exchange(this,1, count--);
        sink(this,1);
        assert indexOfMin == heapIndexToUniqueIndex[count+1];

        values[indexOfMin] = null;
        heapIndexToUniqueIndex[count+1] = -1;
        uniqueIndexToHeapIndex[indexOfMin] = -1;
        return minValue;
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
        IndexMinPQ<T> copy = new IndexMinPQ<>(maxN);

        for (int i = 1; i <= count; i++)
            copy.insert(heapIndexToUniqueIndex[i], values[heapIndexToUniqueIndex[i]]);

        int index = 0;
        for (Object o : copy) {
            sorted[index++] = copy.delMin();
        }
        return sorted;

    }

    private boolean less(IndexMinPQ pq, int i, int j)
    { return pq.values[pq.heapIndexToUniqueIndex[i]]
            .compareTo(pq.values[pq.heapIndexToUniqueIndex[j]]) < 0; }

    private void exchange(IndexMinPQ pq, int i, int j)
    {
        int temp = pq.heapIndexToUniqueIndex[i];
        pq.heapIndexToUniqueIndex[i] = pq.heapIndexToUniqueIndex[j];
        pq.heapIndexToUniqueIndex[j] = temp;

        pq.uniqueIndexToHeapIndex[pq.heapIndexToUniqueIndex[i]] = i;
        pq.uniqueIndexToHeapIndex[pq.heapIndexToUniqueIndex[j]] = j;
    }

    private void swim(int k)
    {
        while (k > 1 && less(this, k, k/2))
        {
            exchange(this, k, k/2);
            k = k/2;
        }
    }

    private void sink(IndexMinPQ pq, int k)
    {
        while (2*k <= pq.count)
        {
            int j = 2*k;
            if (j < pq.count && less(pq, j+1, j)) j++;
            if (!less(pq, j, k)) break;
            exchange(pq, k, j);
            k = j;
        }
    }

    private void validateIndex(int index)
    {
        if (index < 0 || index >= maxN) throw new IndexOutOfBoundsException();
    }

    private void validateAndCheckIfExists(int index)
    {
        if (index < 0 || index >= maxN) throw new IndexOutOfBoundsException();
        if (!this.containsIndex(index)) throw new NoSuchElementException("Index not in priority queue");
    }

    public Iterator<T> iterator()
    { return new HeapIterator(); }

    private class HeapIterator implements Iterator<T>
    {
        IndexMinPQ<T> copy;

        public HeapIterator()
        {
            int N = heapIndexToUniqueIndex.length;
            copy = new IndexMinPQ<>(N-1);
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
            return copy.delMin();
        }
    }
}
