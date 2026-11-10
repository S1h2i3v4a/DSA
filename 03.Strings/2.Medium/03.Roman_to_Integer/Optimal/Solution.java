/*
QUESTION:-
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
Given a roman numeral, convert it to an integer.

Example:
Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
*/

/*
APPROACH:-
Right-to-Left Traversal with Switch Case: Traverse backwards from right to left using a fast switch statement without HashMap overhead. If current value < previous value, subtract; otherwise add and update prev.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int romanToInt(String s) {
        int total = 0, prev = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = getValue(s.charAt(i));
            if (curr < prev) {
                total -= curr;
            } else {
                total += curr;
                prev = curr;
            }
        }
        return total;
    }

    private int getValue(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
