package org.iainuk.strings;

public class SuffixArray {

    private final int N;
    private final String[] suffixes;

    public SuffixArray(String text)
    {
        N = text.length();
        suffixes = new String[N];
        for (int i = 0; i < N; i++)
            suffixes[i] = text.substring(i);
        Quick3WayString.sort(suffixes);
    }

    public int length()
    { return N; }

    public int originalIndex(int i)
    { return N - suffixes[i].length(); }

    public String select(int index)
    { return suffixes[index]; }

    public int rank(String key)
    {
        int lo = 0, hi = suffixes.length-1;

        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(suffixes[mid]);
            if      (cmp < 0) hi = mid-1;
            else if (cmp > 0) lo = mid+1;
            else return mid;
        }

        return lo;
    }

    public int longestCommonPrefix(int index)
    { return longestCommonPrefix(suffixes[index], suffixes[index-1]); }

    public int longestCommonPrefix(String a, String b)
    {
        int min = Math.min(a.length(), b.length());

        for (int i = 0; i < min; i++)
            if (a.charAt(i) != b.charAt(i)) return i;

        return min;
    }
}
