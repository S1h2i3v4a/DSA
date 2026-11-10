/*
QUESTION:
Given a prefix expression, convert it to a postfix expression.

APPROACH:
- Scan the prefix expression from right to left.
- If operand, push it onto the stack.
- If operator, pop two operands `op1` and `op2` from stack.
  Form postfix string `op1 + op2 + operator` and push back onto stack.
- Top of stack is the postfix expression.

TIME COMPLEXITY: O(N) where N is length of string.
SPACE COMPLEXITY: O(N) for stack.
*/

import java.util.Stack;

class Solution {
    public static String prefixToPostfix(String exp) {
        Stack<String> stack = new Stack<>();

        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                stack.push(String.valueOf(c));
            } else {
                String op1 = stack.pop();
                String op2 = stack.pop();
                String expr = op1 + op2 + c;
                stack.push(expr);
            }
        }
        return stack.peek();
    }
}
