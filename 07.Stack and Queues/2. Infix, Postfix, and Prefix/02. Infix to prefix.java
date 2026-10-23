/*
QUESTION:
Given an infix expression in the form of string str. Convert this infix expression to postfix expression.

Infix expression: The expression of the form a op b. When an operator is in-between every pair of operands.
Postfix expression: The expression of the form a b op. When an operator is followed for every pair of operands.
Note: The order of precedence is: ^ greater than * equals to / greater than + equals to -.

Example:
Input: str = "((A-(B/C))*((A/K)-L))"
Output: "*-A/BC-/AKL"
Explanation:
After converting the infix expression into prefix expression, the resultant expression will be *-A/BC-/AKL.

APPROACH:
- We can use a stack to convert the infix expression to postfix.
- We iterate through each character of the input string from right to left.
- If the character is an alphanumeric character, we append it to the output string.
- If the character is an closing parenthesis '(', we push it onto the stack.
- If the character is a opening parenthesis ')', we pop operators from the stack and append them to the output string until we encounter an closing parenthesis '('.
- If the character is an operator, we compare its precedence with the top of the stack and pop operators with higher or equal precedence and append them to the output string. Then we push the current operator onto the stack.
- After iterating through all characters, we pop any remaining operators from the stack and append them to the output string.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.ArrayDeque;
import java.util.Deque;

class Infix_To_Prefix {

    private static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public static String infixToPrefix(String exp) {
        // Step 1: Reverse infix expression and swap brackets
        StringBuilder reversed = new StringBuilder();
        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);
            if (c == '(') {
                reversed.append(')');
            } else if (c == ')') {
                reversed.append('(');
            } else {
                reversed.append(c);
            }
        }

        // Step 2: Convert modified expression to postfix
        StringBuilder postfix = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else { // Operator
                while (!stack.isEmpty() && precedence(c) < precedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                while (!stack.isEmpty() && precedence(c) == precedence(stack.peek()) && c != '^') {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        // Step 3: Reverse postfix expression to get prefix
        return postfix.reverse().toString();
    }

    public static void main(String[] args) {
        String exp = "(A+B)*C-D^E";
        System.out.println("Infix Expression: " + exp);
        System.out.println("Prefix Expression: " + infixToPrefix(exp));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
