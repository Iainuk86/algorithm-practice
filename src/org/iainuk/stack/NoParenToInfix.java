package org.iainuk.stack;

import java.util.Scanner;

/*
 *  Takes an expression from the command line without left parentheses, and prints the equivalent infix expression.
 *  Input expression must be separated by whitespace.
 *  Press [ctrl-d] in console to signify end of file / input.
 */

public class NoParenToInfix {

    public static void main(String[] args) {

        ArrayStack<String> operands = new ArrayStack<>();
        ArrayStack<String> operators = new ArrayStack<>();
        Scanner scanner = new Scanner(System.in);
        String token;

        while (scanner.hasNext()) {
            token = scanner.next();

            if (token.matches("\\d+"))              { operands.push(token); }
            else if (token.matches("[*/+-]"))       { operators.push(token); }
            else if (token.equals(")")) {
                String operand = operands.pop();
                String operator = operators.pop();
                operands.push("(" + operands.pop() + operator + operand + ")");
            }
            else throw new IllegalArgumentException("One of your input characters is invalid");
        }

        System.out.println(operands.pop());
    }
}
