/**
 * Problem Name: Print Longest Common Subsequence
 * Platform: GeeksforGeeks / Standard Data Structures & Algorithms
 * Difficulty: Medium
 *
 * Time Complexity: O(M * N) to build table + O(M + N) to backtrack and reconstruct string
 * Space Complexity: O(M * N) for the DP table and O(min(M, N)) for the reconstructed string
 *
 * Approach:
 *   - While finding the LCS length only requires maintaining counts, retrieving the actual
 *     subsequence string requires reconstructing the path taken through the DP table.
 *   - Step 1: Fill the (M + 1) x (N + 1) DP table where dp[i][j] stores the LCS length
 *     of prefixes s1[0..i-1] and s2[0..j-1].
 *   - Step 2: Backtrack from the bottom-right cell (M, N):
 *     - If s1[i - 1] == s2[j - 1]:
 *       This character was part of the common subsequence!
 *       Append s1[i - 1] to StringBuilder and move diagonally to (i - 1, j - 1).
 *     - Else:
 *       Move in the direction that provided the larger value:
 *       If dp[i - 1][j] > dp[i][j - 1], move up to (i - 1, j).
 *       Else, move left to (i, j - 1).
 *   - Step 3: Reverse the accumulated string (since we traversed from end to start).
 */

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
