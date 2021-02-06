package org.iainuk.priorityqueue;

public class MaxPQ<T extends Comparable<T>> {

    private T[] pq;
    private int N = 0;

    public MaxPQ()
    {
        this(65);
    }

    public MaxPQ(int max)
    {
        this.pq = (T[]) new Comparable[max+1];
    }

    public boolean isEmpty()
    { return this.N == 0; }

    public int count()
    { return this.N; }

    public void insert(T key)
    {
        pq[++N] = key;
        swim(N);
    }

    public T delMax()
    {
        T max = pq[1];
        exchange(1, N--);
        pq[N+1] = null;
        sink(1);
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
        while (2*key <= N)
        {
            int j = 2*key;
            if (j < N && less(j, j+1))   j++;
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

    @Override
    public String toString()
    {
        String result = "";
        for (Object o : this.pq) {
            result += o + ", ";
        }
        return result;
    }
}
