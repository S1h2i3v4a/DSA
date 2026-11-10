/*
QUESTION:
Given a postfix expression, convert it to an infix expression.

APPROACH:
- Scan the postfix expression from left to right.
- If operand, push it onto stack.
- If operator, pop two operands `op2` and `op1` (notice order!).
  Form `("(" + op1 + operator + op2 + ")")` and push onto stack.
- Top of stack is the infix expression.

TIME COMPLEXITY: O(N) where N is length of expression.
SPACE COMPLEXITY: O(N) for stack.
*/

import java.util.Stack;

class Solution {
    public static String postfixToInfix(String exp) {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                stack.push(String.valueOf(c));
            } else {
                String op2 = stack.pop();
                String op1 = stack.pop();
                String expr = "(" + op1 + c + op2 + ")";
                stack.push(expr);
            }
        }
        return stack.peek();
    }
}
