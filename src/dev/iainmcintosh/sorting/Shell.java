package dev.iainmcintosh.sorting;

public class Shell {

    public static void sort(Comparable[] array) {

        int N = array.length;
        int interval = 1;
        while (interval < N/3) interval = 3 * interval + 1;

        while (interval >= 1) {
            for (int i = interval; i < N; i++) {
                for (int j = i; j >= interval && less(array, j, j-interval); j -= interval) {
                    exchange(array, j, j-interval);
                }
            }
            interval /= 3;
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
