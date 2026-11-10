/*
QUESTION:-
A valid parentheses string is either empty "", "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.
A valid parentheses string s is primitive if it is nonempty, and it cannot be split into s = A + B, with A and B nonempty valid parentheses strings.
Return s after removing the outermost parentheses of every primitive string in the primitive decomposition of s.

Example:
Input: s = "(()())(())"
Output: "()()()"
Explanation: The input string is "(()())" + "(())", with primitive parts "(()())" and "(())".
Removing outermost parentheses yields "()()" + "()" = "()()()".
*/

/*
APPROACH:-
Stack-based approach: Use a stack to track parentheses. When encountering '(', if stack is not empty, it's an inner parenthesis so append it; then push '('. When encountering ')', pop from stack; if stack is not empty after popping, it's an inner parenthesis so append it.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (!stack.isEmpty()) sb.append(c);
                stack.push(c);
            } else {
                stack.pop();
                if (!stack.isEmpty()) sb.append(c);
            }
        }
        return sb.toString();
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
