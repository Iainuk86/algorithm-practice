package dev.iainmcintosh.stack;

public class PracticeClass {
    public static void main(String[] args) {
        ArrayStack<String> first = new ArrayStack<>();
        ArrayStack<String> second;

        first.push("Just a test");
        first.push("Nothing to see here");

        second = first.copy();
        for (String s : second) {
            System.out.println(s);
        }
        System.out.println("--------------------");

        second.push("Third string!");
        for (String s : second) {
            System.out.println(s);
        }
        System.out.println("--------------------");
        for (String s : first) {
            System.out.println(s);
        }
    }
}
