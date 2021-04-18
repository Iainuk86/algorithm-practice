package dev.iainmcintosh.stack;

import java.util.Scanner;

/*
 * Takes a string of parentheses and returns whether the parentheses are balanced or not
 */

public class ParenthesesChecker {

    public static void main(String[] args) {

        ArrayStack<Character> stack = new ArrayStack<>();
        Scanner scanner = new Scanner(System.in);
        boolean balanced = true;

        String parens = scanner.nextLine();
        for (int i = 0; i < parens.length() && balanced; i++) {
            char c = parens.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(')  { balanced = false; }
            }
            else if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[')  { balanced = false; }
            }
            else if (c == '}') {
                if (stack.isEmpty() || stack.pop() != '{')  { balanced = false; }
            }
            else throw new IllegalArgumentException("Your input must contain only parentheses.");
        }

        if (stack.isEmpty() && balanced)
            System.out.println("Looks good to me.");
        else System.out.println("Unbalanced, sorry!");
    }
}
