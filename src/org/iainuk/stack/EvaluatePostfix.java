package org.iainuk.stack;

import java.util.Scanner;

/*
 *  Takes a postfix expression from the command line and prints the final value.
 *  Assumes input expression is valid and characters separated by whitespace.
 *  Press [Ctrl-d] in console to signify end of file / input.
 *
 *  Example:
 *  1 2 + 3 4 * *
 *  [Ctrl-d]
 *  36
 */

public class EvaluatePostfix {

    public static void main(String[] args) {

        ArrayStack<Integer> runningTotal = new ArrayStack<>();
        Scanner scanner = new Scanner(System.in);
        String token;

        while (scanner.hasNext()) {
            token = scanner.next();

            if (token.matches("\\d+")) { runningTotal.push(Integer.parseInt(token)); }

            else if (token.matches("[*/+-]")) {
                int firstOperand = (runningTotal.pop());
                if (token.equals("+")) {
                    runningTotal.push(runningTotal.pop() + firstOperand);
                } else if (token.equals("-")) {
                    runningTotal.push(runningTotal.pop() - firstOperand);
                } else if (token.equals("*")) {
                    runningTotal.push(runningTotal.pop() * firstOperand);
                } else if (token.equals("/")) {
                    runningTotal.push(runningTotal.pop() / firstOperand);
                }
            }

            else throw new IllegalArgumentException("One or more of your input characters is invalid.");

        }
        System.out.println(runningTotal.pop());
    }
}
