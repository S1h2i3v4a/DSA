/*
QUESTION:
Given an n x n array of integers matrix, return minimum sum of any falling path.

APPROACH:
- Space-Optimized Tabulation:
- Maintain 1D array `prev` of size n for previous row values.
- Compute `curr` row values and update `prev`.

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(N) auxiliary space.
*/

class Solution {
    public int minFallingPathSum(int[][] matrix) {
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

        int ans = Integer.MAX_VALUE;
        for (int c = 0; c < n; c++) {
            ans = Math.min(ans, prev[c]);
        }
        return ans;
    }
}
