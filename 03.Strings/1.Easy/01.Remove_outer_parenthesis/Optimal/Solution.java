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
Counter-based approach: Maintain a count of opened parentheses. If current char is '(' and opened > 0, append it to result, then opened++. If current char is ')' and opened > 1, append it to result, then opened--.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int opened = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (opened > 0) sb.append(c);
                opened++;
            } else {
                if (opened > 1) sb.append(c);
                opened--;
            }
        }
        return sb.toString();
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
