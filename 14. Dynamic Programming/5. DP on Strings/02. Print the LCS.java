/*
QUESTION:
Print the Longest Common Subsequence between two strings.

APPROACH:
- We will use dynamic programming to find the longest common subsequence (LCS) between two strings.
- We will create a 2D array 'dp' of size (n+1) x (m+1), where n is the length of the first string and m is the length of the second string.
- dp[i][j] will store the length of LCS between the first i characters of string s1 and the first j characters of string s2.
- We will iterate through the strings and fill up the dp array as follows:
  - If s1[i-1] matches s2[j-1], then dp[i][j] = dp[i-1][j-1] + 1, as we are extending the LCS by one character.
  - Otherwise, dp[i][j] = max(dp[i-1][j], dp[i][j-1]), taking the maximum length LCS till the previous characters.
- Once the dp array is filled, we will backtrack from the bottom-right corner (dp[n][m]) to reconstruct the LCS.
- We will start from dp[n][m] and go to dp[0][0], following the conditions mentioned above. If s1[i-1] matches s2[j-1], we add that character to the LCS.
- Finally, we reverse the obtained LCS to get the correct order.

COMPLEXITY ANALYSIS:
- The time complexity of the dynamic programming part is O(n * m), where n is the length of the first string and m is the length of the second string.
- The space complexity is also O(n * m) due to the dp array.
- The space required for storing the LCS is O(min(n, m)), which is the maximum length of the LCS.
- Overall, the algorithm runs in O(n * m) time and O(n * m) space.

CODE:
*/

import java.io.*;
import java.util.*;

// CODE:-
class PrintLongestCommonSubsequence {

    public static String printLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Step 1: Populate DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Step 2: Backtrack to reconstruct the LCS string
        StringBuilder lcs = new StringBuilder();
        int i = m;
        int j = n;

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--; // Move up
            } else {
                j--; // Move left
            }
        }

        // Step 3: Reverse to obtain characters in original forward order
        return lcs.reverse().toString();
    }

    public static void main(String[] args) {
        // Test Case 1:
        // s1 = "abcde", s2 = "ace" -> Expected: "ace"
        String s1 = "abcde", s2 = "ace";
        System.out.println("Test 1 (\"abcde\", \"ace\"): " + printLCS(s1, s2)); // "ace"

        // Test Case 2:
        // s1 = "abac", s2 = "cab" -> Expected: "ab"
        String s3 = "abac", s4 = "cab";
        System.out.println("Test 2 (\"abac\", \"cab\"): " + printLCS(s3, s4)); // "ab"

        // Test Case 3:
        // s1 = "AGGTAB", s2 = "GXTXAYB" -> Expected: "GTAB"
        String s5 = "AGGTAB", s6 = "GXTXAYB";
        System.out.println("Test 3 (\"AGGTAB\", \"GXTXAYB\"): " + printLCS(s5, s6)); // "GTAB"
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
