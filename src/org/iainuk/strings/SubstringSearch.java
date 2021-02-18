package org.iainuk.strings;

public class SubstringSearch {

    public String longestRepeatedSubstring(String text)
    {
        int N = text.length();
        SuffixArray suffixArray = new SuffixArray(text);

        String longest = "";
        for (int i = 1; i < N; i++)
        {
            int length = suffixArray.longestCommonPrefix(i);
            if (length > longest.length())
                longest = suffixArray.select(i).substring(0, length);
        }

        return longest;
    }

    public void printSubstringSearchWithContext(String query, String text)
    { printSubstringSearchWithContext(query, text, 15); }

    public void printSubstringSearchWithContext(String query, String text, int context)
    {
        String newText = text.replaceAll("\\s+", " ");
        int N = newText.length();
        SuffixArray suffixArray = new SuffixArray(newText);

        for (int i = suffixArray.rank(query); i < N && suffixArray.select(i).startsWith(query); i++)
        {
            int from = Math.max(0, suffixArray.originalIndex(i) - context);
            int to = Math.min(N-1, from + query.length() + 2*context);
            System.out.println(newText.substring(from, to));
        }
        System.out.println();
    }

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

        String shortest;
        String longest;

        if (a.length() < b.length()) {
            shortest = a;
            longest = b;
        } else {
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
