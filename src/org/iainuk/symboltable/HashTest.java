package org.iainuk.symboltable;

public class HashTest {
    public static void main(String[] args) {

        LinearProbingHT<String, Integer> test = new LinearProbingHT();

        test.put("First String", 12);
        test.put("Second String", 12);
        test.put("Third String", 11);

        for (String key : test.keys())
            System.out.println(key);

        System.out.println(test.size());
    }
}
