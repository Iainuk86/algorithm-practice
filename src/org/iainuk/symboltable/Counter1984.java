package org.iainuk.symboltable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.Map.Entry;

public class Counter1984 {

        private static boolean ASC = true;
        private static boolean DESC = false;

    public static void main(String[] args) {

        BinarySearchTree<String, Integer> freq = new BinarySearchTree<>();
        String word;

        try {
            Scanner scanner = new Scanner(new File("1984.txt"));
            while (scanner.hasNext())
            {
                word = scanner.next();
                if (freq.get(word) == null) freq.put(word, 1);
                else freq.put(word, freq.get(word)+1);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        for (String key : freq.keys()) {
//            System.out.println(key + ": " + freq.get(key));
//        }
        System.out.println(freq.height());

    }
}
