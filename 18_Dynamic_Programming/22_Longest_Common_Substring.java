/**
 * Problem Name: Longest Common Substring
 * Platform: GeeksforGeeks / Standard Data Structures & Algorithms
 * Difficulty: Medium
 *
 * Time Complexity: O(M * N) where M = s1.length() and N = s2.length()
 * Space Complexity:
 *   - Tabulation: O(M * N) table
 *   - Space-Optimized DP: O(N) 1D array
 *
 * Approach:
 *   - Unlike Longest Common Subsequence (LCS) where characters can be non-contiguous,
 *     a substring MUST be contiguous.
 *   - Recurrence:
 *     If s1[i - 1] == s2[j - 1]:
 *       dp[i][j] = 1 + dp[i - 1][j - 1]
 *       maxLen = max(maxLen, dp[i][j])
 *     Else:
 *       dp[i][j] = 0  (Reset continuity counter!)
 *   - Space Optimization:
 *     Only the previous row's state is needed. We can use two 1D arrays `prev` and `curr` of size N + 1.
 */

class LongestCommonSubstring {

    // ==========================================
    // Approach 1: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int longestCommonSubstrTabulation(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        int maxLen = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLen = Math.max(maxLen, dp[i][j]);
                } else {
                    dp[i][j] = 0; // Continuity broken
                }
            }
        }

        return maxLen;
    }

    // ==========================================
    // Approach 2: Space-Optimized DP - O(N) Space
    // ==========================================
    public static int longestCommonSubstrSpaceOptimized(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[] prev = new int[n + 1];
        int maxLen = 0;

        for (int i = 1; i <= m; i++) {
            int[] curr = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];
                    maxLen = Math.max(maxLen, curr[j]);
                } else {
                    curr[j] = 0;
                }
            }
            prev = curr;
        }

        return maxLen;
    }

    public static void main(String[] args) {
        // Test Case 1:
        // s1 = "ABCDGH", s2 = "ACDGHR" -> Expected: 4 ("CDGH")
        String s1 = "ABCDGH", s2 = "ACDGHR";
        System.out.println("Test 1 (\"ABCDGH\", \"ACDGHR\"):");
        System.out.println("  Tabulation:      " + longestCommonSubstrTabulation(s1, s2)); // 4
        System.out.println("  Space-Optimized: " + longestCommonSubstrSpaceOptimized(s1, s2)); // 4

        // Test Case 2:
        // s1 = "ABC", s2 = "ACB" -> Expected: 1
        String s3 = "ABC", s4 = "ACB";
        System.out.println("\nTest 2 (\"ABC\", \"ACB\"):");
        System.out.println("  Tabulation:      " + longestCommonSubstrTabulation(s3, s4)); // 1
        System.out.println("  Space-Optimized: " + longestCommonSubstrSpaceOptimized(s3, s4)); // 1
    }
}
