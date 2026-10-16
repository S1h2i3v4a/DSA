/**
 * Problem Name: Unique Paths
 * Platform: LeetCode (LC 62) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity:
 *   - Memoization / Tabulation / Space-Optimized: O(M * N)
 *   - Combinatorics: O(min(M, N))
 * Space Complexity:
 *   - Memoization: O(M * N) table + O(M + N) recursion stack
 *   - Tabulation: O(M * N) table
 *   - Space-Optimized: O(N) 1D array
 *   - Combinatorics: O(1) auxiliary space
 *
 * Approach:
 *   - A robot starts at top-left corner (0, 0) of an M x N grid and wants to reach (M-1, N-1).
 *   - Allowed moves: Down or Right only.
 *   - Dynamic Programming Recurrence:
 *     paths(r, c) = paths(r - 1, c) + paths(r, c - 1)
 *     Base case: paths(0, 0) = 1.
 *   - Combinatorial Formula:
 *     Total moves required = (M - 1) down moves + (N - 1) right moves = M + N - 2 total moves.
 *     We choose (M - 1) down moves out of (M + N - 2) total steps: C(M + N - 2, M - 1).
 */

import java.util.Arrays;

class UniquePaths {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int uniquePathsMemo(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return memoHelper(m - 1, n - 1, dp);
    }

    private static int memoHelper(int r, int c, int[][] dp) {
        if (r == 0 && c == 0) return 1;
        if (r < 0 || c < 0) return 0;
        if (dp[r][c] != -1) return dp[r][c];

        int up = memoHelper(r - 1, c, dp);
        int left = memoHelper(r, c - 1, dp);

        return dp[r][c] = up + left;
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int uniquePathsTabulation(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {
                    int up = (i > 0) ? dp[i - 1][j] : 0;
                    int left = (j > 0) ? dp[i][j - 1] : 0;
                    dp[i][j] = up + left;
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(N) Space
    // ==========================================
    public static int uniquePathsSpaceOptimized(int m, int n) {
        int[] prev = new int[n];
        Arrays.fill(prev, 1); // Row 0 has only 1 way for all columns

        for (int i = 1; i < m; i++) {
            int[] curr = new int[n];
            curr[0] = 1; // Column 0 has only 1 way
            for (int j = 1; j < n; j++) {
                curr[j] = prev[j] + curr[j - 1];
            }
            prev = curr;
        }

        return prev[n - 1];
    }

    // ==========================================
    // Approach 4: Combinatorics Formula - O(min(M, N)) Time, O(1) Space
    // ==========================================
    public static int uniquePathsCombinatorics(int m, int n) {
        int totalSteps = m + n - 2;
        int r = Math.min(m - 1, n - 1);
        double result = 1.0;

        for (int i = 1; i <= r; i++) {
            result = result * (totalSteps - r + i) / i;
        }

        return (int) Math.round(result);
    }

    public static void main(String[] args) {
        // Test Case 1: m = 3, n = 7 -> Expected: 28
        System.out.println("Test 1 (m=3, n=7):");
        System.out.println("  Memoization:     " + uniquePathsMemo(3, 7));
        System.out.println("  Tabulation:      " + uniquePathsTabulation(3, 7));
        System.out.println("  Space-Optimized: " + uniquePathsSpaceOptimized(3, 7));
        System.out.println("  Combinatorics:   " + uniquePathsCombinatorics(3, 7));

        // Test Case 2: m = 3, n = 2 -> Expected: 3
        System.out.println("\nTest 2 (m=3, n=2):");
        System.out.println("  Memoization:     " + uniquePathsMemo(3, 2));
        System.out.println("  Tabulation:      " + uniquePathsTabulation(3, 2));
        System.out.println("  Space-Optimized: " + uniquePathsSpaceOptimized(3, 2));
        System.out.println("  Combinatorics:   " + uniquePathsCombinatorics(3, 2));
    }
}
