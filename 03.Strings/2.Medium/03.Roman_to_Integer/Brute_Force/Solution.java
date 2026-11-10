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
Map-based Left-to-Right lookup: For each character, check if current numeral is smaller than next numeral. If so, subtract current; otherwise, add current.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int current = map.get(s.charAt(i));
            if (i + 1 < s.length() && current < map.get(s.charAt(i + 1))) {
                total -= current;
            } else {
                total += current;
            }
        }
        return total;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
