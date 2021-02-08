package org.iainuk.priorityqueue;

import java.util.ArrayList;

public class PQTest {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        // MaxPQ<String> test = new MaxPQ<>(10);

        list.add("12");
        list.add("wtf");
        list.add("Testing yo");
        list.add("gneikgneingolea");
        list.add("Morgzirra");
        list.add("iain woz 'ere");
        list.add("£%!%!£%");

        System.out.println(list);
        System.out.println();

        MaxPQ<String> pq = new MaxPQ<>(list);

        System.out.println(pq);
    }
}
