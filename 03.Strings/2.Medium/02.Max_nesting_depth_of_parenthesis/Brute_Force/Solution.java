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
Stack simulation: Push '(' onto a stack and record the maximum size of the stack at any point. Pop when ')' is encountered.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int maxDepth(String s) {
        Stack<Character> stack = new Stack<>();
        int max = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
                max = Math.max(max, stack.size());
            } else if (c == ')') {
                stack.pop();
            }
        }
        return max;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
