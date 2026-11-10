/*
QUESTION:
Given an infix expression `exp`, convert it to a postfix expression.

APPROACH:
- Use a stack for operators.
- Scan expression left-to-right:
  - If operand (alphanumeric), append to output.
  - If '(', push onto stack.
  - If ')', pop from stack to output until '(' is encountered.
  - If operator, pop operators from stack with greater or equal precedence (or strictly greater for right-associative '^'), then push current operator.
- Pop all remaining operators from stack.

TIME COMPLEXITY: O(N) where N is length of string.
SPACE COMPLEXITY: O(N) for the stack and output builder.
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

    public static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

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
                if (!stack.isEmpty()) stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    if (c == '^' && stack.peek() == '^') break;
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
}
