package org.iainuk.strings;

public class SubstringSearch {

    public boolean bruteForceSearch(String search, String text)
    {
        int N = text.length();
        int M = search.length();

        for (int i = 0; i < N-M; i++)
        {
            int j;
            for (j = 0; j < M; j++)
                if (text.charAt(i + j) != search.charAt(j)) break;
            if (j == M) return true;
        }

        return false;
    }

    public String longestInPlaceCommonString(String a, String b)
    {   // very ugly brute force version

        String shortest = "";
        String longest = "";

        if (a.length() < b.length()) {
            shortest = a;
            longest = b;
        }
        else if (b.length() <= a.length()) {
            shortest = b;
            longest = a;
        }

        String result = "";

        for (int from = 0; from < shortest.length(); from++)
        {
            int to = from;
            while (shortest.charAt(to) == longest.charAt(to))
            {
                to++;
            }

            String potential = shortest.substring(from, to);
            if (potential.length() > result.length())
                result = potential;
        }

        return result;
    }

    public String longestCommonPrefix(String a, String b)
    {
        int min = Math.min(a.length(), b.length());

        for (int i = 0; i < min; i++)
            if (a.charAt(i) != b.charAt(i)) return a.substring(0, i);

        return a.substring(0, min);
    }
}
