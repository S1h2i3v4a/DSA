/*
QUESTION:-
Implement the myAtoi(string s) function using recursion, which converts a string to a 32-bit signed integer.

Example:
Input: s = "-42"
Output: -42
*/

/*
APPROACH:-
Recursive approach: Skip leading whitespace, parse sign, then call a recursive helper function helper(s, i, current, sign) that accumulates each digit until a non-digit character is encountered.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int atoi(String s) {
        int i = 0, n = s.length(), sign = 1;
        while (i < n && s.charAt(i) == ' ') i++;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }
        return helper(s, i, 0L, sign);
    }

    private int helper(String s, int i, long current, int sign) {
        if (i >= s.length() || !Character.isDigit(s.charAt(i))) {
            return (int) (current * sign);
        }
        current = current * 10 + (s.charAt(i) - '0');
        if (sign == 1 && current > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (sign == -1 && -current < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return helper(s, i + 1, current, sign);
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N) (call stack)
