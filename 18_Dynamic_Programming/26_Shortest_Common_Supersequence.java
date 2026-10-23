/**
 * Problem Name: Shortest Common Supersequence
 * Platform: LeetCode (LC 1092) / GeeksforGeeks
 * Difficulty: Hard
 *
 * Time Complexity: O(M * N) to build LCS table + O(M + N) to reconstruct string
 * Space Complexity: O(M * N) for the DP table and O(M + N) for the result string
 *
 * Approach:
 *   - A supersequence of str1 and str2 is a string that contains both str1 and str2 as subsequences.
 *   - The shortest such string includes common characters (LCS) only ONCE, while including
 *     all unique characters from both strings in order.
 *   - Length of SCS = length(str1) + length(str2) - LCS(str1, str2).
 *   - Reconstruction Algorithm:
 *     1. Fill the (M + 1) x (N + 1) DP table for LCS(str1, str2).
 *     2. Backtrack from (M, N):
 *        - If str1[i - 1] == str2[j - 1]:
 *          Include common char once: append str1[i - 1], i--, j--.
 *        - Else if dp[i - 1][j] > dp[i][j - 1]:
 *          Include str1[i - 1] and move up: i--.
 *        - Else:
 *          Include str2[j - 1] and move left: j--.
 *     3. Add remaining characters from str1 or str2 (if any).
 *     4. Reverse the accumulated string.
 */

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
