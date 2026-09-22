/**
 * Problem Name: Infix to Postfix Conversion
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is length of infix string.
 * Space Complexity: O(N) for operator stack and result string.
 * 
 * Approach:
 * Shunting Yard Algorithm.
 * 1. Operands (letters/digits) appended directly to result.
 * 2. '(' pushed to stack.
 * 3. ')' pops operators to result until '(' is encountered.
 * 4. Operators (+, -, *, /, ^) pop higher/equal precedence operators from stack to result before being pushed.
 */

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
