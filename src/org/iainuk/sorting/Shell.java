package org.iainuk.sorting;

import java.util.Arrays;

public class Shell {

    public static void sort(Comparable[] array) {
        System.out.println("Initial array:");
        System.out.println(Arrays.toString(array));

        int N = array.length;
        int interval = 1;
        while (interval < N/3) interval = 3 * interval + 1;

        while (interval >= 1) {
            System.out.println("\nAfter interval of = " + interval);
            for (int i = interval; i < N; i++) {
                for (int j = i; j >= interval && less(array, j, j-interval); j -= interval) {
                    exchange(array, j, j-interval);
                }
            }
            interval /= 3;
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
