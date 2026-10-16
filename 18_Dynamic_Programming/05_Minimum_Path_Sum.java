/**
 * Problem Name: Minimum Path Sum
 * Platform: LeetCode (LC 64) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(M * N) where M is rows and N is columns
 * Space Complexity:
 *   - Memoization: O(M * N) table + O(M + N) recursion stack
 *   - Tabulation: O(M * N) table
 *   - Space-Optimized DP: O(N) 1D array
 *
 * Approach:
 *   - We are given an M x N grid with non-negative integers.
 *   - We need to find a path from top-left (0, 0) to bottom-right (M-1, N-1)
 *     minimizing the sum of numbers along the path.
 *   - Permissible moves: Down or Right only.
 *   - Recurrence:
 *     minCost(r, c) = grid[r][c] + min(minCost(r - 1, c), minCost(r, c - 1))
 *     Base case: minCost(0, 0) = grid[0][0].
 *   - Boundary handling: Out-of-bounds cells return infinity (Integer.MAX_VALUE / large constant).
 *   - We provide:
 *     1. Top-Down DP with Memoization
 *     2. Bottom-Up DP with Tabulation
 *     3. Space-Optimized DP using an O(N) 1D array.
 */

import java.util.Arrays;

class MinimumPathSum {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int minPathSumMemo(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return memoHelper(m - 1, n - 1, grid, dp);
    }

    private static int memoHelper(int r, int c, int[][] grid, int[][] dp) {
        if (r == 0 && c == 0) return grid[0][0];
        if (r < 0 || c < 0) return (int) 1e9; // Represent infinity safely
        if (dp[r][c] != -1) return dp[r][c];

        int up = grid[r][c] + memoHelper(r - 1, c, grid, dp);
        int left = grid[r][c] + memoHelper(r, c - 1, grid, dp);

        return dp[r][c] = Math.min(up, left);
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int minPathSumTabulation(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[0][0];
                } else {
                    int up = (i > 0) ? dp[i - 1][j] : (int) 1e9;
                    int left = (j > 0) ? dp[i][j - 1] : (int) 1e9;
                    dp[i][j] = grid[i][j] + Math.min(up, left);
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(N) Space
    // ==========================================
    public static int minPathSumSpaceOptimized(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] dp = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[j] = grid[0][0];
                } else {
                    int up = (i > 0) ? dp[j] : (int) 1e9;
                    int left = (j > 0) ? dp[j - 1] : (int) 1e9;
                    dp[j] = grid[i][j] + Math.min(up, left);
                }
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // grid = [[1,3,1],[1,5,1],[4,2,1]]
        // Optimal path: 1 -> 3 -> 1 -> 1 -> 1 = 7
        int[][] grid1 = {
            {1, 3, 1},
            {1, 5, 1},
            {4, 2, 1}
        };
        System.out.println("Test 1:");
        System.out.println("  Memoization:     " + minPathSumMemo(grid1)); // 7
        System.out.println("  Tabulation:      " + minPathSumTabulation(grid1)); // 7
        System.out.println("  Space-Optimized: " + minPathSumSpaceOptimized(grid1)); // 7

        // Test Case 2:
        // grid = [[1,2,3],[4,5,6]]
        // Optimal path: 1 -> 2 -> 3 -> 6 = 12
        int[][] grid2 = {
            {1, 2, 3},
            {4, 5, 6}
        };
        System.out.println("\nTest 2:");
        System.out.println("  Memoization:     " + minPathSumMemo(grid2)); // 12
        System.out.println("  Tabulation:      " + minPathSumTabulation(grid2)); // 12
        System.out.println("  Space-Optimized: " + minPathSumSpaceOptimized(grid2)); // 12
    }
}
