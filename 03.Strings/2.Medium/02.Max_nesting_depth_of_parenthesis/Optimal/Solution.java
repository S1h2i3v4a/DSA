/*
QUESTION:-
A string is a valid parentheses string (denoted VPS) if it meets one of the following:
It is an empty string "", or a single character not equal to "(" or ")",
It can be written as AB (A concatenated with B), where A and B are VPS's, or
It can be written as (A), where A is a VPS.
Given a VPS s, return the nesting depth of s.

Example:
Input: s = "(1+(2*3)+((8)/4))+1"
Output: 3
*/

/*
APPROACH:-
Counter-based single pass: Maintain current depth currentDepth. Increment on '(', update maxDepth, and decrement on ')'.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int maxDepth(String s) {
        int currentDepth = 0, maxDepth = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                currentDepth++;
                maxDepth = Math.max(maxDepth, currentDepth);
            } else if (c == ')') {
                currentDepth--;
            }
        }
        return maxDepth;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
