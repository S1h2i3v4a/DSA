/*
QUESTION:-
Given a string 's', you've to count the number of distinct substrings (including the empty substring) of the given string.

Example:
Input: s = "ab"
Output: 4
Explanation: Substrings are "", "a", "b", "ab".
*/

/*
APPROACH:-
Generate All Substrings & Hash Set: Extract all substrings using nested loops and insert into a HashSet. Return set.size() + 1 (for empty substring).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static int distinctSubsequences(String s) {
        Set<String> set = new HashSet<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                set.add(s.substring(i, j));
            }
        }
        return set.size() + 1;
    }
}

// TIME COMPLEXITY = O(N^3)
// SPACE COMPLEXITY = O(N^3)
