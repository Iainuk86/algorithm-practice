package org.iainuk.symboltable;

public class STTesting {

    public static void main(String[] args) {

        BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();

        bst.put("E", bst.size());
        bst.put("A", bst.size());
        bst.put("S", bst.size());
        bst.put("Y", bst.size());
        bst.put("Q", bst.size());
        bst.put("U", bst.size());
        bst.put("E", bst.size());
        bst.put("S", bst.size());
        bst.put("T", bst.size());
        bst.put("I", bst.size());
        bst.put("O", bst.size());
        bst.put("N", bst.size());

        for (String key : bst.keys()) {
            System.out.println(key + ": " + bst.get(key));
        }

        System.out.println();

        for (String key : bst.keys("E", "Q")) {
            System.out.println(key + ": " + bst.get(key));
        }

    }


}
