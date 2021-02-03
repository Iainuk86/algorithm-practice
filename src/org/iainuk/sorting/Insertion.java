package org.iainuk.sorting;

import java.util.Arrays;

public class Insertion {

    public static void sort(Comparable[] array) {
        System.out.println(Arrays.toString(array));
        int N = array.length;

        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(array, j, j-1); j--) {
                exchange(array, j, j-1);
            }
            System.out.println(Arrays.toString(array));
        }
    }

    private static boolean less(Comparable[] array, int a, int b) {
        return array[a].compareTo(array[b]) < 0;
    }

    private static void exchange(Comparable[] array, int a, int b) {
        Comparable temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
