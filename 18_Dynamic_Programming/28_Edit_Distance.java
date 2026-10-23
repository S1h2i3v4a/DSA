/**
 * Problem Name: Edit Distance
 * Platform: LeetCode (LC 72) / GeeksforGeeks
 * Difficulty: Hard
 *
 * Time Complexity: O(M * N) where M = word1.length(), N = word2.length()
 * Space Complexity:
 *   - Memoization: O(M * N) table + O(M + N) recursion stack
 *   - Tabulation: O(M * N) table
 *   - Space-Optimized DP: O(N) using two 1D arrays
 *
 * Approach:
 *   - Given two words word1 and word2, find the minimum number of operations
 *     (Insert a character, Delete a character, Replace a character) to convert word1 into word2.
 *   - Recurrence:
 *     If word1[i - 1] == word2[j - 1]:
 *       edit(i, j) = edit(i - 1, j - 1)  (No operation needed)
 *     Else:
 *       edit(i, j) = 1 + min(
 *           edit(i, j - 1),     // Insert into word1
 *           edit(i - 1, j),     // Delete from word1
 *           edit(i - 1, j - 1)  // Replace in word1
 *       )
 *     Base Cases:
 *       edit(0, j) = j (Need j insertions to form word2[0..j-1] from empty word1)
 *       edit(i, 0) = i (Need i deletions to reduce word1[0..i-1] to empty word2)
 */

import java.util.Arrays;

class EditDistance {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int minDistanceMemo(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return memoHelper(m, n, word1, word2, dp);
    }

    private static int memoHelper(int i, int j, String s1, String s2, int[][] dp) {
        if (i == 0) return j;
        if (j == 0) return i;
        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            return dp[i][j] = memoHelper(i - 1, j - 1, s1, s2, dp);
        } else {
            int insert = memoHelper(i, j - 1, s1, s2, dp);
            int delete = memoHelper(i - 1, j, s1, s2, dp);
            int replace = memoHelper(i - 1, j - 1, s1, s2, dp);

            return dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
        }
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int minDistanceTabulation(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Base cases
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insert = dp[i][j - 1];
                    int delete = dp[i - 1][j];
                    int replace = dp[i - 1][j - 1];
                    dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
                }
            }
        }

        return dp[m][n];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(N) Space
    // ==========================================
    public static int minDistanceSpaceOptimized(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[] prev = new int[n + 1];

        for (int j = 0; j <= n; j++) {
            prev[j] = j;
        }

        for (int i = 1; i <= m; i++) {
            int[] curr = new int[n + 1];
            curr[0] = i; // Base case: j = 0
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    curr[j] = prev[j - 1];
                } else {
                    int insert = curr[j - 1];
                    int delete = prev[j];
                    int replace = prev[j - 1];
                    curr[j] = 1 + Math.min(insert, Math.min(delete, replace));
                }
            }
            prev = curr;
        }

        return prev[n];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // word1 = "horse", word2 = "ros" -> Expected: 3
        String w1 = "horse", w2 = "ros";
        System.out.println("Test 1 (\"horse\", \"ros\"):");
        System.out.println("  Memoization:     " + minDistanceMemo(w1, w2)); // 3
        System.out.println("  Tabulation:      " + minDistanceTabulation(w1, w2)); // 3
        System.out.println("  Space-Optimized: " + minDistanceSpaceOptimized(w1, w2)); // 3

        // Test Case 2:
        // word1 = "intention", word2 = "execution" -> Expected: 5
        String w3 = "intention", w4 = "execution";
        System.out.println("\nTest 2 (\"intention\", \"execution\"):");
        System.out.println("  Memoization:     " + minDistanceMemo(w3, w4)); // 5
        System.out.println("  Tabulation:      " + minDistanceTabulation(w3, w4)); // 5
        System.out.println("  Space-Optimized: " + minDistanceSpaceOptimized(w3, w4)); // 5
    }
}
