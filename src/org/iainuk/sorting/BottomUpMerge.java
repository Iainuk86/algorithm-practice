package org.iainuk.sorting;

public class BottomUpMerge {

    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];

        for (int size = 1; size < N; size *= 2) {
            for (int lo = 0; lo < N - size; lo += size+size) {
                int mid = lo+size-1;
                int hi = Math.min(lo+size+size-1, N-1);
                merge(a, aux, lo, mid, hi);
            }
        }
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)               { a[k] = aux[j++]; }
            else if (j > hi)                { a[k] = aux[i++]; }
            else if (less(aux[j], aux[i]))  { a[k] = aux[j++]; }
            else                            { a[k] = aux[i++]; }
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}
