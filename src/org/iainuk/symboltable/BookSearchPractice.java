package org.iainuk.symboltable;

import org.iainuk.strings.SubstringSearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BookSearchPractice {

    public static void main(String[] args) {

        SubstringSearch ss = new SubstringSearch();

        String text = readFileToString("1984.txt");

        ss.printSubstringSearchWithContext("hello there", text);

    }

    public static String readFileToString(String filename)
    {
        try {
            Scanner scanner = new Scanner(new File(filename));
            if (!scanner.hasNextLine())
                return "";

            String result = scanner.useDelimiter(Pattern.compile("\\A")).next();
            return result;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
