package org.iainuk.stack;

import java.util.Scanner;

/*
 *  Converts an infix expression to postfix.
 *  Assumes input expression is valid.
 */

public class InfixToPostfixNoSpaces {

    public static void main(String[] args) {

        ArrayStack<Character> stack = new ArrayStack<>();
        Scanner scanner = new Scanner(System.in);

        String expression = scanner.nextLine().replaceAll("\\s+", "");
        char c;

        for (int i = 0; i < expression.length(); i++) {
            c = expression.charAt(i);

            if (c == '(')                                   continue;
            else if ("+-*/".contains(String.valueOf(c)))    stack.push(c);
            else if (c == ')')                              System.out.print(stack.pop() + " ");
            else                                            System.out.print(c + " ");
        }
    }
}
