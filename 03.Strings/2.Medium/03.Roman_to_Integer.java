/*
Question:
Given a Roman numeral as a string, convert it to an integer.

Example 1:

Input: s = "III"
Output: 3
Explanation: III = 3.
Example 2:

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.


Approach:
1. Create a map to store the values of each Roman symbol.
2. Initialize `result` as the value of the first symbol in the input string.
3. Iterate through each character `c` in the input string `s`, starting from the second character.
    a. If the value of the current symbol is greater than the value of the previous symbol, subtract twice the value of the previous symbol from `result` and add the value of the current symbol.
    b. Otherwise, add the value of the current symbol to `result`.
4. Return `result` as the converted integer.

Time Complexity: O(n), where n is the length of the input string `s`.
Space Complexity: O(1)
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.HashMap;
import java.util.Map;

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

        int total = 0, prev = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = map.get(s.charAt(i));
            if (curr < prev) {
                total -= curr;
            } else {
                total += curr;
            }
            prev = curr;
        }
        return total;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
