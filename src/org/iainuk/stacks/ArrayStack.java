package org.iainuk.stacks;

import java.util.Iterator;

public class ArrayStack<T> implements Iterable<T> {
    private T[] stack;
    private int N;

    public ArrayStack() {
        stack = (T[]) new Object[1];
    }

    public int size() { return N; }
    public T peek() { return stack[N-1]; }
    public boolean isEmpty() { return N == 0; }

    public void push(T newItem) {
        if (N == stack.length) resize(2*stack.length);
        this.stack[N++] = newItem;
    }

    public T pop() {
        T item = stack[--N];
        stack[N] = null;    // to prevent loitering
        if (N > 0 && N == stack.length/4) resize(stack.length/2);
        return item;
    }

    public ArrayStack<T> copy() {
        ArrayStack<T> newStack = new ArrayStack<>();
        for (int i = 0; i <= N-1; i++) {
            newStack.push(this.stack[i]);
        }
        return newStack;
    }

    private void resize(int newSize) {
        T[] temp = (T[]) new Object[newSize];
        for (int i = 0; i < stack.length; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }

    @Override
    public Iterator<T> iterator() { return new StackIterator(); }

    private class StackIterator implements Iterator<T> {

        private int i = N;

        @Override
        public boolean hasNext() {
            return i != 0;
        }

        @Override
        public T next() {
            return stack[--i];
        }
    }
}
