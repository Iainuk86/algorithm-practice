package dev.iainmcintosh.priorityqueue;

import java.util.List;

public class MaxPQ<T extends Comparable<T>> {

    private T[] pq;
    private int count;

    public MaxPQ()
    {
        this(1);
    }

    public MaxPQ(int max)
    {
        count = 0;
        pq = (T[]) new Comparable[max+1];
    }

    public MaxPQ(List<T> list)
    {
        count = 0;
        int N = list.size();
        pq = (T[]) new Comparable[N+1];
        for (int i = 0; i < N; i++)
        {
            this.insert(list.get(i));
        }
    }

    public MaxPQ(T[] array)
    {
        count = 0;
        int N = array.length;
        pq = (T[]) new Comparable[N+1];
        for (int i = 0; i < N; i++)
        {
            this.insert(array[i]);
        }
    }

    public boolean isEmpty()
    { return count == 0; }

    public int count()
    { return count; }

    public void insert(T key)
    {
        if (count == pq.length) resize(2*pq.length);
        pq[++count] = key;
        swim(count);
    }

    public T delMax()
    {
        T max = pq[1];
        exchange(1, count--);
        pq[count +1] = null;
        sink(1);

        if (count > 0 && count == pq.length/4)
            resize(pq.length/2);

        return max;
    }

    public void swim(int key)
    {
        while (key > 1 && less(key/2, key))
        {
            exchange(key, key/2);
            key = key/2;
        }
    }

    public void sink(int key)
    {
        while (2*key <= count)
        {
            int j = 2*key;
            if (j < count && less(j, j+1))   j++;
            if (pq[key].compareTo(pq[j]) >= 0) return;
            exchange(key, j);
            key = j;
        }
    }

    private boolean less(int a, int b)
    {
        return pq[a].compareTo(pq[b]) < 0;
    }

    private void exchange(int a, int b) {
        T temp = pq[a];
        pq[a] = pq[b];
        pq[b] = temp;
    }

    private void resize(int newSize)
    {
        T[] temp = (T[]) new Object[newSize];
        for (int i = 0; i < pq.length; i++)
        {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    @Override
    public String toString()
    {
        String result = "";
        for (int i = 1; i < pq.length-1; i++)
        {
            result += pq[i] + ", ";
        }
        result += pq[count];
        return result;
    }
}
