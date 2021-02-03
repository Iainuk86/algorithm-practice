package org.iainuk.sorting;

import java.util.Arrays;

public class Selection {

    public static void sort(Comparable[] array) {

        int min;
        int N = array.length;
        for (int i = 0; i < N; i++) {
            min = i;
            for (int j = i+1; j < N; j++) {
                if (less(array, j, min)) min = j;
            }
            exchange(array, i, min);
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
