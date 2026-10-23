/*
QUESTION:
Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
In one step, you can delete exactly one character in either string.

APPROACH:
- The problem can be reduced to finding the length of the Longest Common Subsequence (LCS) between the two strings.
- The idea is to find the length of the LCS and then subtracting it from the sum of the lengths of the two strings.
- The reason is that the characters not present in the LCS need to be deleted from both strings to make them the same.
- Therefore, the minimum number of steps required is equal to the sum of the lengths of the strings minus twice the length of the LCS.

COMPLEXITY ANALYSIS:
- The time complexity of finding the LCS is O(n*m), where n and m are the lengths of the two strings.
- The space complexity is O(n*m) due to the dp array.
- Overall, the algorithm runs in O(n*m) time and O(n*m) space.

CODE:
*/

import java.io.*;
import java.util.*;

// CODE:-
class DeleteOperationForTwoStrings {

    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int lcs = lcsSpaceOptimized(word1, word2);

        return (m - lcs) + (n - lcs);
    }

    private static int lcsSpaceOptimized(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[] prev = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int[] curr = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
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
        // word1 = "sea", word2 = "eat" -> LCS = "ea" (len 2) -> (3-2) + (3-2) = 2
        System.out.println("Test 1 (\"sea\", \"eat\"): " + minDistance("sea", "eat")); // 2

        // Test Case 2:
        // word1 = "leetcode", word2 = "etco" -> LCS = "etco" (len 4) -> (8-4) + (4-4) = 4
        System.out.println("Test 2 (\"leetcode\", \"etco\"): " + minDistance("leetcode", "etco")); // 4
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
