package org.iainuk.symboltable;

import org.iainuk.strings.SortMapByValues;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class PlayingWith1984 {

    public static void main(String[] args) {

        // FIRST METHOD : USING HASHMAP AND ORDERING BY VALUE

        HashMap<String, Integer> map = new HashMap<>();
        String word;

        try {
            Scanner scanner = new Scanner(new File("1984.txt"));
            while (scanner.hasNext())
            {
                word = scanner.next().toLowerCase();
                word = word.replaceAll("\\W+", "");

                if (!map.containsKey(word)) map.put(word, 1);
                else                        map.put(word, map.get(word)+1);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Map<String, Integer> sorted = SortMapByValues.sortByValue(map);

        Iterator iter = sorted.entrySet().iterator();
        while(iter.hasNext())
        {
            Map.Entry entry = (Map.Entry) iter.next();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\n" + sorted.size() + " unique words.");


        // -----------------------------------------------------------------



        // SECOND METHOD : USING RED BLACK TREE

//        RedBlackTree<String, Integer> freq = new RedBlackTree<>();
//        String word;
//
//        try {
//            Scanner scanner = new Scanner(new File("1984.txt"));
//            while (scanner.hasNext())
//            {
//                word = scanner.next().toLowerCase();
//                word = word.replaceAll("\\W+", "");
//
//                if (!freq.contains(word))   freq.put(word, 1);
//                else                        freq.put(word, freq.get(word)+1);
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        for (String key : freq.keys())
//            System.out.println(key + ": " + freq.get(key));



        // --------------------------------------------------------------------



        // MESSING AROUND WITH SUFFIX ARRAYS

//        SubstringSearch ss = new SubstringSearch();
//
//        String text = readFileToString("1984.txt");
//
//        ss.printSubstringSearchWithContext("hello there", text);

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
