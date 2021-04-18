package dev.iainmcintosh.sorting;

import java.util.Random;

public class Quick {
    private static final int cutoff = 10;

    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo + cutoff)  { Insertion.sort(a, lo, hi); return; }
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi+1;
        Comparable v = a[lo];

        while (true) {
            while (less(a[++i], v))     if (i == hi) break;
            while (less(v, a[--j]))     if (j == lo) break;
            if (i >= j) break;
            exchange(a, i, j);
        }
        exchange(a, lo, j);
        return j;
    }

    private static void shuffle(Comparable[] a) {
        int N = a.length;
        Random random = new Random();

        for (int i = N-1; i > 0; i--) {
            int index = random.nextInt(i+1);
            exchange(a, i, index);
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exchange(Comparable[] array, int a, int b) {
        Comparable temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
