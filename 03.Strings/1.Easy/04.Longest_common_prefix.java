/*
Question:
Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

Approach:
1. Sort the array of strings lexicographically.
2. Take the first and last string from the sorted array.
3. Compare each character of the first and last string until they don't match or the end of either string is reached.
4. Return the common prefix.

Time Complexity: O(N*M*log(N)), where N is the number of strings and M is the maximum length of the strings.
- Sorting the array of strings takes O(N*log(N)) time.
- Comparing the first and last string takes O(M) time.

Space Complexity: O(1)
- We use constant space.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
