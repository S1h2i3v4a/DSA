/**
 * Problem Name: Infix to Prefix Conversion
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is length of infix string.
 * Space Complexity: O(N) operator stack & result buffer.
 * 
 * Approach:
 * Reversed Shunting Yard Algorithm.
 * 1. Reverse the infix string and swap '(' with ')' and vice-versa.
 * 2. Obtain Postfix of the modified expression (with strict > condition for right associativity handling).
 * 3. Reverse the resulting Postfix expression to obtain the Prefix expression.
 */

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
