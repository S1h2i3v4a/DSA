/*
QUESTION:
Given a prefix expression, convert it to an infix expression.

APPROACH:
- Scan the prefix expression from right to left.
- If operand, push it as a string onto the stack.
- If operator, pop two operands `op1` and `op2` from the stack.
  Combine them as `("(" + op1 + op + op2 + ")")` and push back onto stack.
- Final element in stack is the infix expression.

TIME COMPLEXITY: O(N) where N is length of string.
SPACE COMPLEXITY: O(N) for stack storing sub-expressions.
*/

import java.util.Stack;

class Solution {
    public static String prefixToInfix(String exp) {
        Stack<String> stack = new Stack<>();

        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                stack.push(String.valueOf(c));
            } else {
                String op1 = stack.pop();
                String op2 = stack.pop();
                String expr = "(" + op1 + c + op2 + ")";
                stack.push(expr);
            }
        }
        return stack.peek();
    }
}
