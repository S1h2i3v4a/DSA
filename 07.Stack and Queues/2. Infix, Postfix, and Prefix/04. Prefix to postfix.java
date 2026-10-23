/*
QUESTION:
You are given a string that represents the prefix form of a valid mathematical expression. Convert it to its postfix form.

Example:
Input: 
*-A/BC-/AKL
Output: 
ABC/-AK/L-*
Explanation: 
The above output is its valid postfix form.

APPROACH:
- We can use a stack to convert the prefix expression to postfix.
- We iterate through each character of the input string in reverse order.
- If the character is an alphanumeric character, we push it onto the stack.
- If the character is an operator, we pop two operands from the stack, concatenate them with the operator, and push the result back onto the stack.
- After iterating through all characters, the top of the stack will contain the final postfix expression.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.ArrayDeque;
import java.util.Deque;

class Prefix_To_Postfix {

    public static String prefixToPostfix(String exp) {
        Deque<String> stack = new ArrayDeque<>();

        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                stack.push(c + "");
            } else {
                String op1 = stack.pop();
                String op2 = stack.pop();
                String temp = op1 + op2 + c;
                stack.push(temp);
            }
        }

        return stack.peek();
    }

    public static void main(String[] args) {
        String exp = "*+AB-CD";
        System.out.println("Prefix Expression: " + exp);
        System.out.println("Postfix Expression: " + prefixToPostfix(exp));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
