/*
Question:
Given a VPS represented as a string s, return the nesting depth of s.

Approach:
1. Initialize `opened` as 0 and `ans` as 0 to keep track of the number of opened parentheses and the maximum nesting depth respectively.
2. Iterate through each character `c` in the string `s`.
    a. If `c` is an opening parenthesis '(', increment `opened` by 1 and update `ans` if it is greater than the current value of `ans`.
    b. If `c` is a closing parenthesis ')', decrement `opened` by 1.
3. Return `ans` as the maximum nesting depth.

CODE:-

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
