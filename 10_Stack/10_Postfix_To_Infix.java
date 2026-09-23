/**
 * Problem Name: Postfix to Infix Conversion
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is length of postfix string.
 * Space Complexity: O(N) stack size.
 * 
 * Approach:
 * Left-to-Right Stack Parsing.
 * Traverse postfix string from left to right (index 0 to N-1):
 * - If character is operand, push string representation to stack.
 * - If character is operator, pop `op2` then `op1`.
 * - Combine as `"(" + op1 + operator + op2 + ")"` and push back onto stack.
 */

import java.util.ArrayDeque;
import java.util.Deque;

class Postfix_To_Infix {

    public static String postfixToInfix(String exp) {
        Deque<String> stack = new ArrayDeque<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                stack.push(c + "");
            } else {
                String op2 = stack.pop();
                String op1 = stack.pop();
                String temp = "(" + op1 + c + op2 + ")";
                stack.push(temp);
            }
        }

        return stack.peek();
    }

    public static void main(String[] args) {
        String exp = "ab*c+";
        System.out.println("Postfix Expression: " + exp);
        System.out.println("Infix Expression: " + postfixToInfix(exp));
    }
}
