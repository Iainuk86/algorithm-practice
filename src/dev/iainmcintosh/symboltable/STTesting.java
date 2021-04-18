package dev.iainmcintosh.symboltable;

public class STTesting {

    public static void main(String[] args) {

        BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
        RedBlackTree<String, Integer> rb = new RedBlackTree<>();

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

        rb.put("E", rb.size());
        rb.put("A", rb.size());
        rb.put("S", rb.size());
        rb.put("Y", rb.size());
        rb.put("Q", rb.size());
        rb.put("U", rb.size());
        rb.put("E", rb.size());
        rb.put("S", rb.size());
        rb.put("T", rb.size());
        rb.put("I", rb.size());
        rb.put("O", rb.size());
        rb.put("N", rb.size());

        System.out.println(rb.min());
        rb.deleteMin();
        System.out.println(rb.min());
        System.out.println(rb.max());
        rb.deleteMax();
        System.out.println(rb.max());

    }


}
