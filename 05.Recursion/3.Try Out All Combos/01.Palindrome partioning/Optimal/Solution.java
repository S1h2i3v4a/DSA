/*
QUESTION:-
Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

Example:
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
*/

/*
APPROACH:-
Backtracking with In-place Palindrome Check: Check palindrome directly on character indices without creating substring objects unless it is confirmed to be a palindrome.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        solve(0, s, new ArrayList<>(), result);
        return result;
    }

    private static void solve(int index, String s, List<String> path, List<List<String>> result) {
        if (index == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (isPalindrome(s, index, i)) {
                path.add(s.substring(index, i + 1));
                solve(i + 1, s, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }
}

// TIME COMPLEXITY = O(2^N * N)
// SPACE COMPLEXITY = O(N)
