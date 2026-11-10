/*
QUESTION:
Given an n x n array of integers matrix, return minimum sum of any falling path.
Falling path starts at any element in first row and chooses element in next row that is either directly below or diagonally left/right.

APPROACH:
- Pure recursion: From row 0, try each starting column `c`.
- At (r, c), path sum = matrix[r][c] + min(f(r-1, c-1), f(r-1, c), f(r-1, c+1)).

TIME COMPLEXITY: O(N * 3^N) exponential branching.
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int ans = Integer.MAX_VALUE;
        for (int c = 0; c < n; c++) {
            ans = Math.min(ans, solve(n - 1, c, matrix, n));
        }
        return ans;
    }

    private int solve(int r, int c, int[][] matrix, int n) {
        if (c < 0 || c >= n) return (int) 1e9;
        if (r == 0) return matrix[0][c];
        int upLeft = solve(r - 1, c - 1, matrix, n);
        int up = solve(r - 1, c, matrix, n);
        int upRight = solve(r - 1, c + 1, matrix, n);
        return matrix[r][c] + Math.min(up, Math.min(upLeft, upRight));
    }
}
