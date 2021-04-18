package dev.iainmcintosh.linkedlist;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {

    private Node root;
    private Node last;
    private int N;  // number of items on list

    public DoublyLinkedList() {
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
        last.prev = oldLast;
        oldLast.next = last;
        N++;
    }

    public void insert(int index, T value) {
        if (index > N || index < 0) { throw new IndexOutOfBoundsException(); }
        if (index == N)             { add(value); N++; return; }

        int i = 0;
        for (Node x = root; x != null; x = x.next) {
            if (i == index) {
                Node oldNext = x.next;
                Node insertedNode = new Node();
                insertedNode.value = value;
                insertedNode.next = oldNext;
                insertedNode.prev = x;
                oldNext.prev = insertedNode;
                x.next = insertedNode;
                N++; return;
            }
            i++;
        }
    }

    public void insertBefore(T oldValue, T newValue) {
        // inserts argument B before argument A in linked list
        for (Node x = root.next; x != null; x = x.next) {
            if (x.value.equals(oldValue)) {
                Node temp = x;
                x = x.prev;
                Node insertedNode = new Node();
                insertedNode.value = newValue;
                insertedNode.next = temp;
                insertedNode.prev = x;
                x.next = insertedNode;
                temp.prev = insertedNode;
                N++;
                return;
            }
        }
        throw new NullPointerException("Value not found");
    }

    public void insertAfter(T oldValue, T newValue) {
        // Inserts argument B after argument A in linked list
        for (Node x = root.next; x != null; x = x.next) {
            if (x.value.equals(oldValue) && x.equals(last)) {
                add(newValue);
                return;
            }

            if (x.value.equals(oldValue)) {
                Node temp = x;
                x = x.next;
                Node insertedNode = new Node();
                insertedNode.value = newValue;
                insertedNode.next = x;
                insertedNode.prev = temp;
                x.prev = insertedNode;
                temp.next = insertedNode;
                N++;
                return;
            }
        }
        throw new NullPointerException("Value not found");
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
            if (x.next == null) {
                value = x.value;
                x = x.prev;
                x.next = null;
                last = x;
                N--;
                break;
            }
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
        if (index >= N || index < 0)        { throw new IndexOutOfBoundsException(); }
        if (index == N-1)                   { pop(); return; }

        int i = 0;
        for (Node x = root; x != null; x = x.next) {
            if (i++ == index) {
                x = x.prev;
                x.next = x.next.next;
                x.next.prev = x;
                N--; return;
            }
        }
    }

    public void delete(T value) {
        // deletes first instance of value
        for (Node x = root; x != null; x = x.next) {
            if (x.next.equals(last) && x.next.value.equals(value))     { last = x; x.next = null; N--; return; }
            if (x.next.equals(last)) throw new NullPointerException("Value not found");

            if (x.next.value.equals(value)) {
                x = x.prev;
                x.next = x.next.next;
                x.next.prev = x;
                N--; return;
            }
        }
    }

    public void remove(T value) {
        // removes all instances of value
        for (Node x = root.next; x != null; x = x.next) {
            if (N == 0)             { throw new NullPointerException("List is empty!"); }
            if (x == null)          { return; }

            if (x.value.equals(value) && N == 1) {
                delete(0); return;
            }

            if (x.value.equals(value)) {
                x = x.prev;
                x.next = x.next.next;
                x.next.prev = x;
                N--;
            }

            if (x.next == null) { return; }
            if (x.next.equals(last) && x.next.value.equals(value))      { last = x; x.next = null; N--; }
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
        private Node prev;
    }
}
