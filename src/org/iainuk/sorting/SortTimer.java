package org.iainuk.sorting;

import java.util.Random;

/**
 * one more test before bed
 */
public class SortTimer {

    public static void main(String[] args) {

        for (int N = 125; true; N += N) {
            double time = timeTrial(N);
            System.out.printf("%d: %5.3fs", N, time);
            System.out.println();
        }
    }

    public static double timeTrial(int N) {

        Random random = new Random();
        int max = 1000000;
        Integer[] array = new Integer[N];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt((2*max)) - max;
        }

        long start = System.nanoTime();
        Quick.sort(array);
        long end = System.nanoTime();
        long duration = end - start;

        return duration / 1000000000.0;
    }




}
