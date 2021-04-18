package dev.iainmcintosh.sorting;

import java.util.Random;

public class Quick3Way {

    private static final int cutoff = 15;

    public static void sort(Comparable[] a)
    {
        shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo + cutoff)  { Insertion.sort(a, lo, hi); return; }
        int lt = lo, gt = hi, i = lo+1;
        Comparable v = a[lo];

        while (i <= gt)
        {
            if      (less(a[i], v))     exchange(a, lt++, i++);
            else if (less(v, a[i]))     exchange(a, i, gt--);
            else                        i++;
        }

        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
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
