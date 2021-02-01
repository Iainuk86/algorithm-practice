package org.iainuk.stack;

import java.util.Scanner;

/*
 *  Converts an infix expression to postfix.
 *  Assumes input expression is valid with characters separated by whitespace.
 *  Press [ctrl-d] in console to signify end of file / input.
 */

public class InfixToPostfixWithSpaces {

    public static void main(String[] args) {

        ArrayStack<String> result = new ArrayStack<>();
        Scanner scanner = new Scanner(System.in);
        String token;

        while (scanner.hasNext()) {
            token = scanner.next();

            if (token.equals("("))                    continue;
            else if (token.matches("[*/+-]"))   result.push(token);
            else if (token.equals(")"))               System.out.print(result.pop() + " ");
            else                                      System.out.print(token + " ");
        }
    }
}
