/*
QUESTION:
Given an m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes sum of all numbers.

APPROACH:
- Pure recursion: minPath(r, c) = grid[r][c] + min(minPath(r - 1, c), minPath(r, c - 1)).

TIME COMPLEXITY: O(2^(M+N)).
SPACE COMPLEXITY: O(M + N) recursion stack.
*/

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        return solve(m - 1, n - 1, grid);
    }

    private int solve(int r, int c, int[][] grid) {
        if (r == 0 && c == 0) return grid[0][0];
        if (r < 0 || c < 0) return (int) 1e9;
        int up = grid[r][c] + solve(r - 1, c, grid);
        int left = grid[r][c] + solve(r, c - 1, grid);
        return Math.min(up, left);
    }
}
