/**
 * Problem Name: Count Square Submatrices with All Ones
 * Platform: LeetCode (LC 1277) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(M * N) where M is rows and N is columns
 * Space Complexity: O(M * N) or O(N) using DP table
 *
 * Approach:
 *   - Given an M x N binary matrix, count all square submatrices that have all ones.
 *   - DP State:
 *     dp[i][j] = side length of the largest square submatrix whose bottom-right corner is at (i, j).
 *   - Notice that if the largest square ending at (i, j) has side length k, it automatically
 *     contributes exactly k squares (of sizes 1x1, 2x2, ..., k x k) ending at (i, j)!
 *   - Recurrence:
 *     If matrix[i][j] == 1:
 *       dp[i][j] = 1 + min(dp[i - 1][j], min(dp[i][j - 1], dp[i - 1][j - 1]))
 *     Else:
 *       dp[i][j] = 0
 *   - Summing all dp[i][j] over the entire matrix yields the total square count.
 */

class CountSquareSubmatricesWithAll1s {

    public static int countSquares(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int totalSquares = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                    }
                    totalSquares += dp[i][j];
                }
            }
        }

        return totalSquares;
    }

    public static void main(String[] args) {
        // Test Case 1:
        // matrix = [
        //   [0,1,1,1],
        //   [1,1,1,1],
        //   [0,1,1,1]
        // ]
        // Expected: 15
        int[][] matrix1 = {
            {0, 1, 1, 1},
            {1, 1, 1, 1},
            {0, 1, 1, 1}
        };
        System.out.println("Test 1: " + countSquares(matrix1)); // 15

        // Test Case 2:
        // matrix = [
        //   [1,0,1],
        //   [1,1,0],
        //   [1,1,0]
        // ]
        // Expected: 7
        int[][] matrix2 = {
            {1, 0, 1},
            {1, 1, 0},
            {1, 1, 0}
        };
        System.out.println("Test 2: " + countSquares(matrix2)); // 7
    }
}
