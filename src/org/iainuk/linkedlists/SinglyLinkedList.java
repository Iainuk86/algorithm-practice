package org.iainuk.linkedlists;

import java.util.Iterator;

/*
 * Singly linked list that can store items from different classes.
 * Similar to the list data structure in Python, but currently without slice functionality.
 * Max and Min functionality not yet implemented.
 */

public class SinglyLinkedList<T> implements Iterable<T> {

    private Node root;
    private Node last;
    private int N;  // number of items on list

    public SinglyLinkedList() {
        root = new Node();
        last = root;
    }

    public int size() { return N; }
    public boolean isEmpty() { return N == 0; }

    public void add(T value) {
        Node oldLast = last;
        last = new Node();
        last.value = value;
        last.next = null;
        oldLast.next = last;
        N++;
    }

    public void insert(int index, T value) {
        if (index > N || index < 0) throw new IndexOutOfBoundsException();
        if (index == N)             { add(value); N++; return; }

        int i = 0;
        for (Node x = root; x != null; x = x.next) {
            if (i == index) {
                Node oldNext = x.next;
                Node insertedNode = new Node();
                insertedNode.value = value;
                insertedNode.next = oldNext;
                x.next = insertedNode;
                N++; return;
            }
            i++;
        }
    }

    public T get(int index) {
        if (index >= N || index < 0) throw new IndexOutOfBoundsException();

        T value = null;
        int i = 0;
        for (Node x = root.next; x != null; x = x.next) {
            if (i++ == index)       { value = x.value; break; }
        }
        return value;
    }

    public T pop() {
        if (N == 0) throw new NullPointerException("List is empty");

        T value = null;
        for (Node x = root; x != null; x = x.next) {
            if (x.next == null)     { N--; value = x.value; break; }
        }
        return value;
    }

    public int indexOf(T value) {
        int i = 0;
        for (Node x = root.next; x != null; x = x.next) {
            if (x.value.equals(value)) {
                return i;
            }
            i++;
        }
        return -1;  // Not found
    }

    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    public void change(int index, T value) {
        if (index >= N || index < 0) throw new IndexOutOfBoundsException();

        int i = 0;
        for (Node x = root.next; x != null; x = x.next) {
            if (i++ == index)       { x.value = value; return; }
        }
    }

    public void delete(int index) {
        if (index >= N || index < 0) throw new IndexOutOfBoundsException();

        int i = 0;
        for (Node x = root; x != null; x = x.next) {
            if (i == index)     { x.next = x.next.next; N--; return; }
        }
    }

    public void delete(T value) {
        // deletes first instance of value
        for (Node x = root; x != null; x = x.next) {
            if (x.next.value.equals(value))     { x.next = x.next.next; N--; return; }
        }
        throw new NullPointerException("Value not found");
    }

    public void remove(T value) {
        // removes all instances of value
        for (Node x = root; x != null; x = x.next) {
            if (x.next.value.equals(value))     { x.next = x.next.next; N--; }
        }
    }

    @Override
    public Iterator<T> iterator() { return new LinkedListIterator(); }

    private class LinkedListIterator implements Iterator<T> {

        private Node current = root;

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            Node temp = current.next;
            current = current.next;
            return temp.value;
        }
    }

    private class Node {
        private T value;
        private Node next;
    }
}
