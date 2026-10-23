/*
QUESTION:
Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. 
If there are multiple valid strings, return any of them.

APPROACH:
- This problem can be solved using dynamic programming to find the length of the shortest common supersequence (SCS).
- The SCS is the shortest string that contains both str1 and str2 as subsequences.
- The idea is to first find the length of the SCS using a dynamic programming approach.
- Once the length is found, we can construct the SCS using the dp array and backtracking.
- We start from the last cell of the dp array and move either diagonally (when characters match) or towards the direction with the greater value.
- During backtracking, we append the characters from str1 or str2 to the SCS string based on the movement direction.

COMPLEXITY ANALYSIS:
- The time complexity of finding the length of the SCS is O(n*m), where n and m are the lengths of the two strings.
- The space complexity is O(n*m) due to the dp array.
- The backtracking process takes O(n + m) time.
- Overall, the algorithm runs in O(n*m) time and O(n*m) space.

CODE:
*/

import java.io.*;
import java.util.*;

// CODE:-
class ShortestCommonSupersequence {

    public static String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Step 1: Fill LCS DP Table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Step 2: Reconstruct SCS string by backtracking
        StringBuilder scs = new StringBuilder();
        int i = m, j = n;

        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                scs.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                scs.append(str1.charAt(i - 1));
                i--;
            } else {
                scs.append(str2.charAt(j - 1));
                j--;
            }
        }

        // Step 3: Append remaining characters
        while (i > 0) {
            scs.append(str1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            scs.append(str2.charAt(j - 1));
            j--;
        }

        return scs.reverse().toString();
    }

    public static void main(String[] args) {
        // Test Case 1:
        // str1 = "abac", str2 = "cab" -> Expected: "cabac" (len 5)
        String str1 = "abac", str2 = "cab";
        System.out.println("Test 1 (\"abac\", \"cab\"): " + shortestCommonSupersequence(str1, str2));

        // Test Case 2:
        // str1 = "aaaaaaaa", str2 = "aaaaaaaa" -> Expected: "aaaaaaaa"
        System.out.println("Test 2 (\"aaaaaaaa\", \"aaaaaaaa\"): " + shortestCommonSupersequence("aaaaaaaa", "aaaaaaaa"));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
