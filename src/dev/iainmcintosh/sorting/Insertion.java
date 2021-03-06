package dev.iainmcintosh.sorting;

public class Insertion {

    public static void sort(Comparable[] array) {
        int N = array.length;

        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(array, j, j-1); j--) {
                exchange(array, j, j-1);
            }
        }
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a, j, j-1); j--) {
                exchange(a, j, j-1);
            }
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
