package dev.iainmcintosh.queue;

import java.util.Iterator;

public class ArrayQueue<T> implements Iterable<T> {
    private T[] queue;
    private int first = 0;
    private int last = 0;
    private int N;  // number of items on queue

    public ArrayQueue() {
        this.queue = (T[]) new Object[1];
    }

    public int size() { return N; }
    public boolean isEmpty() { return N == 0; }

    public void enqueue(T item) {
        if (last == queue.length) resize(2*queue.length);
        queue[last++] = item;
        N++;
    }

    public T dequeue() {
        if (first == last) throw new IllegalArgumentException("Queue is empty.");
        T item = queue[first++];
        queue[first-1] = null;   // to prevent loitering
        if (N-- == queue.length/4) resize(queue.length/2);
        return item;
    }

    private void resize(int newSize) {
        T[] temp = (T[]) new Object[newSize];
        int tracker = 0;
        for (int i = 0; i < queue.length; i++) {
            if (queue[i] != null) {
                temp[tracker] = queue[i];
                tracker++;
            }
        }
        queue = temp;
        first = 0; last = N;
    }

    @Override
    public Iterator<T> iterator() { return new QueueIterator(); }

    private class QueueIterator implements Iterator<T> {

        private int i = 0;

        @Override
        public boolean hasNext() {
            return i != N;
        }

        @Override
        public T next() {
            return queue[i++];
        }
    }
}
