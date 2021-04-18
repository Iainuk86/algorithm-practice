package dev.iainmcintosh.sorting;

import java.util.Arrays;

public class DedupTest {
    public static void main(String[] args) {
        String[] test = {"Well", "I'll", "12", "12", "12", "12", "gienoe", "Morgzirra", "Well", "Hey wtf"};

        String[] hopeful = dedup(test);

        System.out.println(Arrays.toString(hopeful));
    }

    public static String[] dedup(String[] array)
    {
        String[] copy = array;
        int N = copy.length;
        Quick.sort(copy);

        int duplicateCount = 0;
        for (int i = 1; i < N; i++)
        {
            if (copy[i].compareTo(copy[i-1]) == 0)
            {
                int j;
                for (j = i+1; j < N; j++)
                {
                    copy[j-1] = copy[j];
                }
                copy[j-1] = null;
                N--; i--;
                duplicateCount++;
            }
        }

        String[] result = new String[copy.length - duplicateCount];

        for (int i = 0; i < result.length; i++)
        {
            result[i] = copy[i];
        }

        return result;
    }
}
