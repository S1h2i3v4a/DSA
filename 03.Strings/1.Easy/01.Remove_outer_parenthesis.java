/*
Question:
Given a valid parentheses string `s`, remove the outermost parentheses of every primitive string in the primitive decomposition of `s`.

Approach:
- We can iterate through the characters of the string and keep track of the number of open parentheses encountered.
- Whenever we encounter an opening parenthesis, if the count of open parentheses is greater than 0, we append it to the result string.
- When we encounter a closing parenthesis, we decrement the count of open parentheses and append it to the result string only if the count is greater than 1.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int opened = 0;
        for (char c : s.toCharArray()) {
            if (c == '(' && opened++ > 0) sb.append(c);
            if (c == ')' && opened-- > 1) sb.append(c);
        }
        return sb.toString();
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
