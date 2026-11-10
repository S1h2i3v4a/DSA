/*
QUESTION:-
You are given a string num, representing a large integer. Return the largest-valued odd integer (as a string) that is a non-empty substring of num, or an empty string "" if no odd integer exists.

Example 1:
Input: num = "52"
Output: "5"
Explanation: The only non-empty substrings are "5", "2", and "52". "5" is the only odd number.

Example 2:
Input: num = "4206"
Output: ""
Explanation: There are no odd numbers in "4206".
*/

/*
APPROACH:-
Scan from right to left: The largest odd number substring must start from index 0 to maximize magnitude. Therefore, find the last index i where num.charAt(i) is odd, and return substring(0, i + 1).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            char c = num.charAt(i);
            if ((c - '0') % 2 == 1) {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
