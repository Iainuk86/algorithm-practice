package org.iainuk.sorting;

public class Test {
    public static void main(String[] args) {
        Character[] testArray =
                {'E', 'A', 'S', 'Y', 'S', 'H', 'E', 'L', 'L', 'S', 'O', 'R', 'T', 'Q', 'U', 'E', 'S', 'T', 'I', 'O', 'N'};

        final long begin = System.nanoTime();
        Insertion.sort(testArray);
        final long end = System.nanoTime();
        System.out.println("Finished in " + (end - begin) / 1000000.0 + "ms");


        // ANOTHER FUCKING TEST
    }
}
