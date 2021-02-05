package org.iainuk.priorityqueue;

public class PQTest {
    public static void main(String[] args) {

        MaxPQ<Integer> test = new MaxPQ<>(10);

        test.insert(12);
        test.insert(15353);
        test.insert(0);
        test.insert(443);
        test.insert(98);
        test.insert(7832);
        test.insert(555555);

        System.out.println(test);

        System.out.println(test.delMax());
        System.out.println(test.delMax());
        System.out.println(test.delMax());
    }
}
