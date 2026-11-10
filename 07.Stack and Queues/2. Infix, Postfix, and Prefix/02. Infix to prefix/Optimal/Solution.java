/*
QUESTION:
Given an infix expression, convert it to a prefix expression.

APPROACH:
1. Reverse the infix expression.
2. Invert brackets: '(' becomes ')' and ')' becomes '('.
3. Convert modified infix to postfix (with equal precedence preference to right associativity).
4. Reverse the postfix expression to get the prefix expression.

TIME COMPLEXITY: O(N) where N is the length of expression.
SPACE COMPLEXITY: O(N) for stack and string transformations.
*/

import java.util.Stack;

class Solution {
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
            default:
                return -1;
        }
    }

    public static String infixToPrefix(String exp) {
        StringBuilder sb = new StringBuilder();
        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);
            if (c == '(') sb.append(')');
            else if (c == ')') sb.append('(');
            else sb.append(c);
        }

        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty()) stack.pop();
            } else {
                if (c == '^') {
                    while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                        postfix.append(stack.pop());
                    }
                } else {
                    while (!stack.isEmpty() && precedence(c) < precedence(stack.peek())) {
                        postfix.append(stack.pop());
                    }
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        return postfix.reverse().toString();
    }
}
