/**
 * QUESTION: Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * 
 * Example:
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Approach:
 * We can solve this problem using a recursive approach with backtracking.
 * 1. Start from the beginning of the string and try to find a word from the dictionary that matches a substring starting at the current index.
 * 2. If we find a match, recursively call the function on the remaining substring.
 * 3. Repeat this process until we reach the end of the string.
 * 4. If we can successfully break the string into words, we return true. Otherwise, we return false.
 *
 * Time Complexity: O(2^N), where N is the length of the string. In the worst case, we can have 2^N recursive calls.
 * Space Complexity: O(N), where N is the length of the string. The recursion stack can go up to N in the worst case.
 */

import java.util.*;
import java.io.*;

// CODE:-
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Word_Break {

    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[s.length()];
        return backtrack(0, s, dict, memo);
    }

    private static boolean backtrack(int start, String s, Set<String> dict, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }

        if (memo[start] != null) {
            return memo[start];
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String prefix = s.substring(start, end);
            if (dict.contains(prefix) && backtrack(end, s, dict, memo)) {
                return memo[start] = true;
            }
        }

        return memo[start] = false;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        System.out.println("Can \"" + s + "\" be broken into dictionary words? " + wordBreak(s, wordDict));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
