/*
QUESTION:-
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Example:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
*/

/*
APPROACH:-
Memoized DFS / Dynamic Programming: Use a Boolean[] memo array of size n to store whether substring starting at index start can be segmented into dictionary words.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[s.length()];
        return backtrack(0, s, dict, memo);
    }

    private static boolean backtrack(int start, String s, Set<String> dict, Boolean[] memo) {
        if (start == s.length()) return true;
        if (memo[start] != null) return memo[start];

        for (int end = start + 1; end <= s.length(); end++) {
            if (dict.contains(s.substring(start, end)) && backtrack(end, s, dict, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(N)
