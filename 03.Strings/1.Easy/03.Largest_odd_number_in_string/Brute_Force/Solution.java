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
Iterate from the end to find any substring ending with an odd digit. A number is odd if its last digit is odd, and any prefix ending with an odd digit is greater than shorter prefixes.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            int digit = num.charAt(i) - '0';
            if (digit % 2 != 0) {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
