package org.iainuk.strings;

public class LSDSort {

    public static void sort(String[] array)
    { sort(array, 0); }

    public static void sort(String[] array, int index)
    {
        int R = 256;
        int N = array.length;
        String[] aux = new String[N];

        for (int d = N-1-index; d >= 0; d--)
        {
            int[] count = new int[R+1];

            for (int i = 0; i < N; i++)
            { count[array[i].charAt(d)+1]++; }

            for (char r = 0; r < R; r++)
            { count[r+1] += count[r]; }

            for (int i = 0; i < N; i++)
            { aux[count[array[i].charAt(d)]++] = array[i]; }

            for (int i = 0; i < N; i++)
            { array[i] = aux[i]; }
        }
    }
}
