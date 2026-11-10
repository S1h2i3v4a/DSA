/*
QUESTION:
Given a string s, return count of total distinct substrings.

APPROACH:
- Generate all possible substrings s[i..j] and insert them into a HashSet.
- Total distinct substrings = set.size().

TIME COMPLEXITY: O(N^3) substring creation + hashing.
SPACE COMPLEXITY: O(N^3) storing all substrings in memory.
*/

import java.util.HashSet;
import java.util.Set;

class Solution {
    public static int countDistinctSubstrings(String s) {
        Set<String> set = new HashSet<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                set.add(s.substring(i, j));
            }
        }
        return set.size() + 1; // including empty string
    }
}
