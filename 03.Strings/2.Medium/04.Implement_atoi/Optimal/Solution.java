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
In-place parsing without trim or 64-bit types: Skip whitespace with index pointer, handle sign, and check overflow directly using 32-bit arithmetic (Integer.MAX_VALUE / 10).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int myAtoi(String s) {
        int i = 0, n = s.length(), sign = 1;
        while (i < n && s.charAt(i) == ' ') i++;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        int result = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;
            i++;
        }
        return result * sign;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
