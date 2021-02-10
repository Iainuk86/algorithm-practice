package org.iainuk.symboltable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Counter1984 {

    public static void main(String[] args) {

        BinarySearchTree<String, Integer> freq = new BinarySearchTree<>();
        RedBlackTree<String, Integer> rb = new RedBlackTree<>();
        String word;

        try {
            Scanner scanner = new Scanner(new File("1984.txt"));
            while (scanner.hasNext())
            {
                word = scanner.next();
                if (!freq.contains(word)) freq.put(word, 1);
                else freq.put(word, freq.get(word)+1);

                if (!rb.contains(word)) rb.put(word, 1);
                else rb.put(word, rb.get(word)+1);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        for (String key : freq.keys()) {
//            System.out.println(key + ": " + freq.get(key));
//        }

        System.out.println(freq.height());

        System.out.println();

    }
}
