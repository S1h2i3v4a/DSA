/*
QUESTION:
You are given a string that represents the postfix form of a valid mathematical expression. Convert it to its infix form.

Example:
Input:
ab*c+ 
Output: 
((a*b)+c)
Explanation: 
The above output is its valid infix form.

APPROACH:
- We can use a stack to convert the postfix expression to infix.
- We iterate through each character of the input string.
- If the character is an alphanumeric character, we push it onto the stack.
- If the character is an operator, we pop two operands from the stack, concatenate them with the operator in the proper order (operand1 + operator + operand2), and push the result back onto the stack.
- After iterating through all characters, the top of the stack will contain the final infix expression.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
