package org.iainuk.priorityqueue;

import java.util.Arrays;

public class IndexPQTest {
    public static void main(String[] args) {
        // insert a bunch of strings
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

//        IndexMaxPQ<String> pq = new IndexMaxPQ<>(strings.length);
//        for (int i = 0; i < strings.length; i++) {
//            pq.insert(i, strings[i]);
//        }

        IndexMaxPQ<String> pq = new IndexMaxPQ(Arrays.asList(strings));

        // delete and print each key
        while (!pq.isEmpty()) {
            int i = pq.indexOfMaximumValue();
            System.out.println(i + ": " + strings[i]);
            pq.delMax();
        }
        System.out.println();

        // reinsert the same strings
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // print each key using the iterator
        for (Object o : pq) {
            System.out.println(o);
        }

        System.out.println();
        System.out.println(Arrays.toString(pq.asSortedArray()));
    }
}
