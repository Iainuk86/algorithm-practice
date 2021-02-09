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

        HashMap<String, Integer> freq = new HashMap<>();
        String word;

        try {
            Scanner scanner = new Scanner(new File("1984.txt"));
            while (scanner.hasNext())
            {
                word = scanner.next();
                freq.putIfAbsent(word, 1);
                freq.put(word, freq.get(word)+1);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HashMap<String, Integer> sorted = (HashMap<String, Integer>) sortByComparator(freq, DESC);

        printMap(sorted);

    }

    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortedMap, final boolean order)
    {
        List<Entry<String, Integer>> list = new LinkedList<>(unsortedMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, (o1, o2) -> {

            if (order)
            {
                return o1.getValue().compareTo(o2.getValue());
            }
            else
            {
                return o2.getValue().compareTo(o1.getValue());

            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<>();

        for (Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    private static void printMap(Map<String, Integer> map)
    {
        map.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
