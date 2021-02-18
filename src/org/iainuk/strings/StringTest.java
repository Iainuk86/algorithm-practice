package org.iainuk.strings;

public class StringTest {
    public static void main(String[] args) {
        SubstringSearch ss = new SubstringSearch();

        String answer = ss.longestInPlaceCommonString("gnikaewngew", "gejkanlkan ga");

        System.out.println(answer);
    }
}
