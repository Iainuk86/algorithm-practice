package dev.iainmcintosh.queue;

import java.util.Iterator;

public class CircularLinkedListQueue<T> implements Iterable<T> {

    private Node first;
    private Node last;
    private int N;  // number of items on list

    public CircularLinkedListQueue() {

    }

    public int size() { return N; }
    public boolean isEmpty() { return N == 0; }

    public void enqueue(T value) {
        Node oldLast = last;
        last = new Node();
        last.value = value;
        last.next = first;
        if (first == null)  first = last;
        else                oldLast.next = last;
        N++;
    }

    public T dequeue() {
        if (first == null)      throw new NullPointerException("List is empty");
        T value = first.value;
        first = first.next;
        N--;
        return value;
    }

    @Override
    public Iterator<T> iterator()       { return new CircularLinkedListIterator(); }

    private class CircularLinkedListIterator implements Iterator<T> {

        private Node current = first;
        private int i = 0;

        @Override
        public boolean hasNext() {
            return current != null && i != N;
        }

        @Override
        public T next() {
            Node temp = current;
            current = current.next;
            i++;
            return temp.value;
        }
    }

    private class Node {
        private T value;
        private Node next;
    }
}
