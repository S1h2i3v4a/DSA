/*
QUESTION:-
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Example:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
*/

/*
APPROACH:-
Pure Recursion: For each prefix s[0..i], if prefix is in wordDict, recursively check whether the remaining suffix s[i..n] can be segmented.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        return canBreak(0, s, dict);
    }

    private static boolean canBreak(int start, String s, Set<String> dict) {
        if (start == s.length()) return true;
        for (int end = start + 1; end <= s.length(); end++) {
            if (dict.contains(s.substring(start, end)) && canBreak(end, s, dict)) {
                return true;
            }
        }
        return false;
    }
}

// TIME COMPLEXITY = O(2^N)
// SPACE COMPLEXITY = O(N)
