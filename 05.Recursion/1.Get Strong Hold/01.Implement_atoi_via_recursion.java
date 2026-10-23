/*Your task is to implement the function atoi. The function takes a string (str) as an argument and converts it to an integer and returns it.

Note: You are not allowed to use inbuilt functions.

Example 1:
Input:
str = "123"
Output: 123

Example 2:
Input:
str = "21a"
Output: -1
Explanation: Output is -1 as not all characters are digits.

Approach:
1. We start from the last character of the string and recursively convert each digit to an integer.
2. We use a helper function `getnum` that takes the index `i` and the string `str` as arguments.
3. The base case is when `i` becomes negative, in which case we return 0.
4. For each digit, we convert it to an integer by subtracting the ASCII value of '0'.
5. We check if the digit is within the range of 0 to 9. If it is, we recursively call `getnum` for the previous index `i-1`.
   - If the recursive call returns a valid number (`prev != 1e9`), we multiply it by 10 and add the current digit.
   - Otherwise, we return a large value `1e9` to indicate an invalid conversion.
6. In the main `atoi` function, we initialize `i` to the index of the last character in the string (`str.size()-1`).
7. We call the `getnum` function with `i` and `str`.
8. If the result (`ans`) is equal to `1e9`, it means the conversion was invalid, so we return -1.
9. If the first character of the string is '-', we return the negative value of `ans`.
10. Otherwise, we return `ans`.

Time Complexity: O(N), where N is the length of the string.
Space Complexity: O(N), where N is the length of the string (due to the recursive calls).

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int atoi(String s) {
        s = s.trim();
        if (s.isEmpty()) return -1;
        int sign = 1, i = 0;
        if (s.charAt(0) == '-') { sign = -1; i = 1; }
        else if (s.charAt(0) == '+') { i = 1; }
        return helper(s, i, 0, sign);
    }
    private int helper(String s, int i, int current, int sign) {
        if (i >= s.length()) return current * sign;
        char c = s.charAt(i);
        if (c < '0' || c > '9') return -1;
        return helper(s, i + 1, current * 10 + (c - '0'), sign);
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
