/**
 * Problem Name: Prefix to Infix Conversion
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is length of prefix string.
 * Space Complexity: O(N) stack depth.
 * 
 * Approach:
 * Right-to-Left Stack Parsing.
 * Traverse prefix string from right to left (index N-1 to 0):
 * - If character is operand, push string representation to stack.
 * - If character is operator, pop `op1` then `op2`.
 * - Combine as `"(" + op1 + operator + op2 + ")"` and push back onto stack.
 */

import java.util.ArrayDeque;
import java.util.Deque;

class Prefix_To_Infix {

    public static String prefixToInfix(String exp) {
        Deque<String> stack = new ArrayDeque<>();

        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                stack.push(c + "");
            } else {
                String op1 = stack.pop();
                String op2 = stack.pop();
                String temp = "(" + op1 + c + op2 + ")";
                stack.push(temp);
            }
        }

        return stack.peek();
    }

    public static void main(String[] args) {
        String exp = "*+AB-CD";
        System.out.println("Prefix Expression: " + exp);
        System.out.println("Infix Expression: " + prefixToInfix(exp));
    }
}
