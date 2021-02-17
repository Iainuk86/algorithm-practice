package org.iainuk.strings;

public class StringInsertion {

    public static void sort(String[] array, int lo, int hi, int digit)
    {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(array[j], array[j-1], digit); j--)
                exchange(array, j, j-1);
    }

    private static boolean less(String first, String second, int digit)
    { return first.substring(digit).compareTo(second.substring(digit)) < 0; }

    private static void exchange(String[] array, int first, int second)
    {
        String temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
}
