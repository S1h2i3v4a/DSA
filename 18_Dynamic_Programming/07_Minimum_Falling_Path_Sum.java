/**
 * Problem Name: Minimum Falling Path Sum
 * Platform: LeetCode (LC 931) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(N^2) where N is the dimension of the square matrix (or M * N for rectangular)
 * Space Complexity:
 *   - Memoization: O(N^2) table + O(N) recursion stack
 *   - Tabulation: O(N^2) table
 *   - Space-Optimized DP: O(N) rolling 1D array
 *
 * Approach:
 *   - A falling path starts at any element in the first row and chooses the element in the next row
 *     that is directly below or diagonally left/right: (r+1, c-1), (r+1, c), or (r+1, c+1).
 *   - Recurrence:
 *       minFalling(r, c) = matrix[r][c] + min(
 *           minFalling(r - 1, c - 1),
 *           minFalling(r - 1, c),
 *           minFalling(r - 1, c + 1)
 *       )
 *     Base Case: For row 0, minFalling(0, c) = matrix[0][c].
 *   - Out-of-bounds columns return infinity (1e9).
 *   - Space-Optimized DP:
 *     We only need the previous row's computed minimums to calculate the current row.
 *     Maintain an array `prev` of size N and compute into `curr`, achieving O(N) auxiliary space.
 */

import java.util.Arrays;

class MinimumFallingPathSum {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int minFallingPathSumMemo(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        int minSum = Integer.MAX_VALUE;
        // The path can terminate at any column in the last row
        for (int c = 0; c < n; c++) {
            minSum = Math.min(minSum, memoHelper(n - 1, c, matrix, dp, n));
        }
        return minSum;
    }

    private static int memoHelper(int r, int c, int[][] matrix, int[][] dp, int n) {
        if (c < 0 || c >= n) return (int) 1e9; // Boundary condition
        if (r == 0) return matrix[0][c];      // Base case: first row
        if (dp[r][c] != Integer.MAX_VALUE) return dp[r][c];

        int upLeft = memoHelper(r - 1, c - 1, matrix, dp, n);
        int up = memoHelper(r - 1, c, matrix, dp, n);
        int upRight = memoHelper(r - 1, c + 1, matrix, dp, n);

        int minPrevious = Math.min(up, Math.min(upLeft, upRight));
        return dp[r][c] = matrix[r][c] + minPrevious;
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int minFallingPathSumTabulation(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];

        // Base case: first row values
        for (int c = 0; c < n; c++) {
            dp[0][c] = matrix[0][c];
        }

        for (int r = 1; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int upLeft = (c > 0) ? dp[r - 1][c - 1] : (int) 1e9;
                int up = dp[r - 1][c];
                int upRight = (c + 1 < n) ? dp[r - 1][c + 1] : (int) 1e9;

                dp[r][c] = matrix[r][c] + Math.min(up, Math.min(upLeft, upRight));
            }
        }

        int minSum = Integer.MAX_VALUE;
        for (int c = 0; c < n; c++) {
            minSum = Math.min(minSum, dp[n - 1][c]);
        }
        return minSum;
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(N) Space
    // ==========================================
    public static int minFallingPathSumSpaceOptimized(int[][] matrix) {
        int n = matrix.length;
        int[] prev = new int[n];

        for (int c = 0; c < n; c++) {
            prev[c] = matrix[0][c];
        }

        for (int r = 1; r < n; r++) {
            int[] curr = new int[n];
            for (int c = 0; c < n; c++) {
                int upLeft = (c > 0) ? prev[c - 1] : (int) 1e9;
                int up = prev[c];
                int upRight = (c + 1 < n) ? prev[c + 1] : (int) 1e9;

                curr[c] = matrix[r][c] + Math.min(up, Math.min(upLeft, upRight));
            }
            prev = curr;
        }

        int minSum = Integer.MAX_VALUE;
        for (int c = 0; c < n; c++) {
            minSum = Math.min(minSum, prev[c]);
        }
        return minSum;
    }

    public static void main(String[] args) {
        // Test Case 1:
        // matrix = [[2,1,3],[6,5,4],[7,8,9]]
        // Falling paths with minimum sums: 1 -> 5 -> 7 = 13, 1 -> 4 -> 8 = 13.
        // Expected: 13
        int[][] matrix1 = {
            {2, 1, 3},
            {6, 5, 4},
            {7, 8, 9}
        };
        System.out.println("Test 1:");
        System.out.println("  Memoization:     " + minFallingPathSumMemo(matrix1)); // 13
        System.out.println("  Tabulation:      " + minFallingPathSumTabulation(matrix1)); // 13
        System.out.println("  Space-Optimized: " + minFallingPathSumSpaceOptimized(matrix1)); // 13

        // Test Case 2:
        // matrix = [[-19,57],[-40,-5]]
        // Optimal: -19 -> -40 = -59
        int[][] matrix2 = {
            {-19, 57},
            {-40, -5}
        };
        System.out.println("\nTest 2:");
        System.out.println("  Memoization:     " + minFallingPathSumMemo(matrix2)); // -59
        System.out.println("  Tabulation:      " + minFallingPathSumTabulation(matrix2)); // -59
        System.out.println("  Space-Optimized: " + minFallingPathSumSpaceOptimized(matrix2)); // -59
    }
}
