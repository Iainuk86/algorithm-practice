package org.iainuk.priorityqueue;

import java.util.ArrayList;

public class PQTest {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();

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

//        String[] strings = new String[7];
//        strings[0] = "12";
//        strings[1] = "wtf";
//        strings[2] = "Testing yo";
//        strings[3] = "gengoieanigoa";
//        strings[4] = "Morgzirra";
//        strings[5] = "iain woz 'ere";
//        strings[6] = "£%£%£!Y&&&";
//
//        MaxPQ<String> pq = new MaxPQ<>(strings);
//
//        System.out.println(pq);
    }
}
