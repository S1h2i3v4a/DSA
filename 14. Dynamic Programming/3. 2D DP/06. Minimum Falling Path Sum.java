/*
QUESTION:-
Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.

A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).

Example:
Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
Output: 13
Explanation: There are two falling paths with a minimum sum as shown.

Approach:
1. We can solve this problem using dynamic programming with memoization (top-down approach).
2. We define a helper function fmemo(i, j, mat, dp) that calculates the minimum sum of any falling path starting from cell (i, j).
3. The function checks if the minimum sum for the current cell (i, j) is already calculated and stored in the dp array. If yes, it returns the value from dp.
4. If i is 0, it means we are at the first row, so the minimum sum starting from this cell is the value in the current cell (mat[i][j]).
5. Otherwise, the function calculates the minimum sum for the current cell by taking the minimum of the minimum sums of the cells above it (i-1, j-1), (i-1, j), and (i-1, j+1), and then adding the value in the current cell (mat[i][j]).
6. The result is stored in the dp array to avoid recomputation when the function is called with the same parameters again.
7. Finally, we find the minimum falling path sum for all cells in the first row and return the smallest value.

Complexity Analysis:
- The time complexity of the fmemo function is O(n^2) since we are calculating the minimum sum for each cell only once and storing it in the dp array.
- The space complexity is also O(n^2) to store the dp array.

*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
