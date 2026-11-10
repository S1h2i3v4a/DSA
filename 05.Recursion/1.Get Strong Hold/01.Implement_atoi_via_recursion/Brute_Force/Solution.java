/*
QUESTION:-
Implement the myAtoi(string s) function using recursion, which converts a string to a 32-bit signed integer.

Example:
Input: s = "-42"
Output: -42
*/

/*
APPROACH:-
Iterative approach: Skip leading whitespace, determine sign, and use a while loop to accumulate digits.
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
        long result = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i) - '0');
            if (sign == 1 && result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && -result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
            i++;
        }
        return (int) (result * sign);
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
