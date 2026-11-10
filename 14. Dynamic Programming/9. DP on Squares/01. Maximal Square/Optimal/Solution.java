/*
QUESTION:
Maximal Square.

APPROACH:
- Space-Optimized 1D Tabulation:
- Maintain 1D array `dp` of size `n + 1` and `prev` variable for top-left cell.

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(N) auxiliary space.
*/

class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dp = new int[n + 1];
        int maxSide = 0, prev = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = 1 + Math.min(dp[j], Math.min(dp[j - 1], prev));
                    maxSide = Math.max(maxSide, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }

        return maxSide * maxSide;
    }
}
