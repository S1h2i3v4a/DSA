/**
 * Problem Name: Word Break
 * Platform: LeetCode (139)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N^2) where N is length of string s.
 * Space Complexity: O(N) for HashSet and Memoization array.
 * 
 * Approach:
 * Backtracking with Memoization (Top-Down DP).
 * We check prefixes of s starting from index `start`. If s.substring(start, end) is present in dictionary,
 * we recurse for `end`. Memoize evaluated index failures using Boolean memo array to avoid redundant recursive branches.
 */

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
