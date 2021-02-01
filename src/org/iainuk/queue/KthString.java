package org.iainuk.queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KthString {

    public static void main(String[] args) {

        ArrayQueue<String> inputStrings = new ArrayQueue<>();

        try {
            Scanner fileScanner = new Scanner(new File("KthStringLines.txt"));
            while (fileScanner.hasNextLine()) inputStrings.enqueue(fileScanner.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner userInput = new Scanner(System.in);
        int k = Integer.parseInt(userInput.next());
        int N = inputStrings.size();

        // SORT THIS OUT
        if (k >= N) throw new IndexOutOfBoundsException();

        for (int i = 0; i < N - 1 - k; i++) {
            inputStrings.dequeue();
        }

        System.out.println(inputStrings.dequeue());
    }
}
