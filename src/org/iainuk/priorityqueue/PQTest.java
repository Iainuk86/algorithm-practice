package org.iainuk.priorityqueue;

public class PQTest {
    public static void main(String[] args) {

        MaxPQ<String> test = new MaxPQ<>(10);

        test.insert("12");
        test.insert("wtf");
        test.insert("Testing yo");
        test.insert("gneikgneingolea");
        test.insert("Morgzirra");
        test.insert("iain woz 'ere");
        test.insert("£%!%!£%");

        System.out.println(test);
        System.out.println();

        while (!test.isEmpty())
        {
            System.out.println(test.delMax());
        }
    }
}
