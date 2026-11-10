/*
QUESTION:-
Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
Algorithm:
1. Ignore leading whitespace.
2. Check sign ('+' or '-').
3. Read in digits until next non-digit character.
4. Clamp to [-2^31, 2^31 - 1] if integer overflow occurs.

Example:
Input: s = "   -42"
Output: -42
*/

/*
APPROACH:-
Iterative parsing with long accumulator: Trim whitespace, determine sign, parse digits into a 64-bit long, and clamp when exceeding Integer bounds.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int myAtoi(String s) {
        s = s.trim();
        if (s.isEmpty()) return 0;

        int sign = 1, i = 0;
        if (s.charAt(0) == '-') {
            sign = -1;
            i++;
        } else if (s.charAt(0) == '+') {
            i++;
        }

        long result = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i) - '0');
            if (sign == 1 && result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && -result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
            i++;
        }
        return (int) (result * sign);
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
