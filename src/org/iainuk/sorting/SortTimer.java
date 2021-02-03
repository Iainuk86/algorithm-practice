package org.iainuk.sorting;

import java.util.Arrays;
import java.util.Random;

public class SortTimer {

    public static void main(String[] args) {

        Random random = new Random();
        int max = 1000000;
        Integer[] array = new Integer[1000000];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt((2*max)) - max;
        }

        long start = System.nanoTime();
        Shell.sort(array);
        long end = System.nanoTime();
        long duration = end - start;
        System.out.println("That took ... " + (duration / 1000000000.0) + " seconds.");
        
    }





}
