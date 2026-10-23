/*
QUESTION:
Given an infix expression in the form of string str, convert it to a postfix expression.

Infix expression: The expression of the form a op b. When an operator is in-between every pair of operands.
Postfix expression: The expression of the form a b op. When an operator is followed for every pair of operands.
Note: The order of precedence is: ^ greater than * equals to / greater than + equals to -.

Example:
Input: str = "a+b*(c^d-e)^(f+g*h)-i"
Output: abcd^e-fgh*+^*+i-
Explanation:
After converting the infix expression into postfix expression, the resultant expression will be abcd^e-fgh*+^*+i-

APPROACH:
- We can use a stack to convert the infix expression to postfix.
- We iterate through each character of the input string.
- If the character is an operand (letter or digit), we append it to the output string.
- If the character is an opening parenthesis '(', we push it onto the stack.
- If the character is a closing parenthesis ')', we pop elements from the stack and append them to the output string until we encounter an opening parenthesis. We also discard the opening parenthesis from the stack.
- If the character is an operator, we compare its precedence with the top element of the stack. If the top element has higher precedence, we pop it and append it to the output string. We repeat this process until we find an operator with lower precedence or an opening parenthesis. Then we push the current operator onto the stack.
- After iterating through all characters, we pop any remaining elements from the stack and append them to the output string.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.ArrayDeque;
import java.util.Deque;

class Infix_To_Postfix {

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

    public static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
            } else { // Operator encountered
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    if (c == '^' && stack.peek() == '^') { // Right-associative '^'
                        break;
                    }
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String exp = "a+b*(c^d-e)^(f+g*h)-i";
        System.out.println("Infix Expression: " + exp);
        System.out.println("Postfix Expression: " + infixToPostfix(exp));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
