/*
QUESTION:
Given a string s. In one step you can insert any character at any index of the string.
Return the minimum number of steps to make s palindrome.

APPROACH:
- The problem can be reduced to finding the minimum number of insertions needed to make a string a palindrome.
- We can find the Longest Common Subsequence (LCS) between the given string and its reverse.
- The length of the LCS gives us the length of the longest palindromic subsequence in the string.
- The minimum number of insertions required to make the string a palindrome is equal to the difference between the length of the string and the length of the LCS.
- Return this difference as the answer.

COMPLEXITY ANALYSIS:
- The time complexity of finding the LCS is O(n^2), where n is the length of the string.
- The space complexity is O(n^2) due to the dp array.
- Overall, the algorithm runs in O(n^2) time and O(n^2) space.

CODE:
*/

import java.io.*;
import java.util.*;

// CODE:-
class MinimumInsertionsToMakeStringPalindrome {

    public static int minInsertions(String s) {
        int n = s.length();
        int lps = longestPalindromeSubseq(s);
        return n - lps;
    }

    private static int longestPalindromeSubseq(String s) {
        String reversed = new StringBuilder(s).reverse().toString();
        int n = s.length();
        int[] prev = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == reversed.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            prev = curr;
        }

        return prev[n];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // s = "zzazz" -> Expected: 0 (Already a palindrome)
        System.out.println("Test 1 (\"zzazz\"): " + minInsertions("zzazz")); // 0

        // Test Case 2:
        // s = "mbadm" -> Expected: 2 (Insert 'b' and 'd' -> "mbdadbm")
        System.out.println("Test 2 (\"mbadm\"): " + minInsertions("mbadm")); // 2

        // Test Case 3:
        // s = "leetcode" -> Expected: 5
        System.out.println("Test 3 (\"leetcode\"): " + minInsertions("leetcode")); // 5
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
