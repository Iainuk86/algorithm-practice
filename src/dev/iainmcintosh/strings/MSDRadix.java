package dev.iainmcintosh.strings;

public class MSDRadix {

    private static final int CUTOFF = 7;

    private static String[] aux;
    private static final int R = 256;

    public static void sort(String[] array)
    {
        int N = array.length;
        aux = new String[N];
        sort(array, 0, N-1, 0);
    }

    private static void sort(String[] array, int lo, int hi, int digit)
    {
        if (hi <= lo + CUTOFF) { StringInsertion.sort(array, lo, hi, digit); return; }
        int[] count = new int[array.length];

        for (int i = lo; i <= hi; i++)
        { count[charAt(array[i], digit)+2]++; }

        for (char r = 0; r < R+1; r++)
        { count[r+1] += count[r]; }

        for (int i = lo; i <= hi; i++)
        { aux[count[charAt(array[i], digit)+1]++] = array[i]; }

        for (int i = lo; i <= hi; i++)
        { array[i] = aux[i - lo]; } // with recursion, the aux array will be a lot shorter so i-lo is needed

        for (int r = 0; r < R; r++)
        { sort(array, lo + count[r], lo + count[r+1] - 1, digit+1); }
    }

    private static int charAt(String string, int digit)
    {
        if (digit < string.length())    return string.charAt(digit);
        else                            return -1;
    }
}
