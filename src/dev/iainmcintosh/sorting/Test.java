package dev.iainmcintosh.sorting;

import java.util.Arrays;
import java.util.Random;

public class Test {
    public static void main(String[] args) {

        int N = 100;
        Random random = new Random();
        int max = 1000000;
        Integer[] array = new Integer[N];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt((2*max)) - max;
        }

        Quick3Way.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
