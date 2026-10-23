/*
QUESTION:-
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example 1:
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

Approach:
1. We can solve this problem using dynamic programming with memoization (top-down approach).
2. We define a helper function fmemo(i, j, grid, dp) that calculates the minimum sum path to reach the cell (i, j) on the grid.
3. The function checks if the minimum sum path for the cell (i, j) is already calculated and stored in the dp array. If yes, it returns the value from dp.
4. If i or j is less than 0 (out of bounds), the function returns a large value (e.g., 1e9) to represent an invalid path.
5. The base case is when i and j are both 0, in which case the function returns the value in the cell (0, 0) as the minimum sum path.
6. Otherwise, it calculates the minimum sum path for the cell (i, j) by taking the minimum of the minimum sum paths from the cell above (i-1, j) and the cell to the left (i, j-1), and then adding the value in the current cell (grid[i][j]).
7. The result is stored in the dp array to avoid recomputation when the function is called with the same parameters again.

Complexity Analysis:
- The time complexity of the fmemo function is O(m x n) because we are calculating the minimum sum path for each cell only once and storing it in the dp array.
- The space complexity is also O(m x n) to store the dp array.

*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
