/*
QUESTION:-
Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

Example:
Input: strs = ["flower","flow","flight"]
Output: "fl" 
*/

/*
APPROACH:-
Sorting Comparison: Sort the array of strings lexicographically. The longest common prefix of the whole array must be the common prefix between the first and last strings in sorted order.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];
        int i = 0;
        while (i < first.length() && i < last.length() && first.charAt(i) == last.charAt(i)) {
            i++;
        }
        return first.substring(0, i);
    }
}

// TIME COMPLEXITY = O(N * M log N)
// SPACE COMPLEXITY = O(1)
