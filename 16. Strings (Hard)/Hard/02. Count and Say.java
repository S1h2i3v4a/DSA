/*
QUESTION:
The count-and-say sequence is a sequence of digit strings defined by the recursive formula:

countAndSay(1) = "1"
countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a different digit string.
To determine how you "say" a digit string, split it into the minimal number of substrings such that each substring contains exactly one unique digit. Then for each substring, say the number of digits, then say the digit. Finally, concatenate every said digit.

Input: n = 4
Output: "1211"
Explanation:
countAndSay(1) = "1"
countAndSay(2) = say "1" = one 1 = "11"
countAndSay(3) = say "11" = two 1's = "21"
countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"

Approach:
- We can solve this problem using recursion.
- The base case is when n = 1, in which case we return "1".
- Otherwise, we calculate the count-and-say string for n-1 using recursion.
- Then, we iterate through the previous string and build the new string based on the count of consecutive characters.

Complexity Analysis:
- Since we are using recursion to generate the count-and-say sequence, the time complexity is O(2^n), where n is the given input.
- The space complexity is O(1) as we are using a constant amount of extra space for each recursion level.

CODE:
*/

import java.io.*;
import java.util.*;

// CODE:-
class Solution {
    public String countAndSay(int n) {
        if (n <= 0) return "";
        String s = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (int j = 1; j < s.length(); j++) {
                if (s.charAt(j) == s.charAt(j - 1)) {
                    count++;
                } else {
                    sb.append(count).append(s.charAt(j - 1));
                    count = 1;
                }
            }
            sb.append(count).append(s.charAt(s.length() - 1));
            s = sb.toString();
        }
        return s;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
