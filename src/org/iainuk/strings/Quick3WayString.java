package org.iainuk.strings;

import java.util.Random;

public class Quick3WayString {

    private static final int CUTOFF = 7;

    public static void sort(String[] array)
    {
        shuffle(array);
        int N = array.length;
        sort(array, 0, N-1, 0);
    }

    private static void sort(String[] array, int lo, int hi, int digit)
    {
        if (hi <= lo + CUTOFF) { StringInsertion.sort(array, lo, hi, digit); return; }

        int lessThan = lo, greaterThan = hi;
        int firstChar = charAt(array[lo], digit);

        int i = lo+1;
        while(i <= greaterThan)
        {
            int secondChar = charAt(array[i], digit);
            if      (secondChar < firstChar)    exchange(array, lessThan++, i++);
            else if (firstChar < secondChar)    exchange(array, i, greaterThan--);
            else                                i++;
        }

        sort(array, lo, lessThan-1, digit);
        if (firstChar >= 0) sort(array, lessThan, greaterThan, digit+1);
        sort(array, greaterThan+1, hi, digit);
    }

    private static void shuffle(String[] array)
    {
        int N = array.length;
        Random random = new Random();

        for (int i = N-1; i > 0; i--)
        {
            int index = random.nextInt(i+1);
            exchange(array, i, index);
        }
    }

    private static void exchange(String[] array, int first, int second)
    {
        String temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    private static int charAt(String string, int digit)
    {
        if (digit < string.length())    return string.charAt(digit);
        else                            return -1;
    }
}
