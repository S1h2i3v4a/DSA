/*
QUESTION:
Count Square Submatrices with All Ones.

APPROACH:
- Bottom-Up DP:
- `dp[i][j]` = largest side of all-ones square having bottom-right corner at (i, j).
- `dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])`.
- The sum of all `dp[i][j]` values is the total number of square submatrices with all 1s.

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(M * N) (or O(N) space).
*/

class Solution {
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int total = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                    }
                    total += dp[i][j];
                }
            }
        }

        return total;
    }
}
