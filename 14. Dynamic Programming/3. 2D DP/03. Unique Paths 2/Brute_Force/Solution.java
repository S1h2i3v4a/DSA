/*
QUESTION:
Given an m x n grid with obstacles (1 = obstacle, 0 = space), find unique paths from top-left to bottom-right.

APPROACH:
- Pure recursion: if grid[r][c] == 1 or out of bounds, return 0.
- paths(r, c) = paths(r - 1, c) + paths(r, c - 1).

TIME COMPLEXITY: O(2^(M+N)).
SPACE COMPLEXITY: O(M + N) recursion stack.
*/

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        return solve(m - 1, n - 1, obstacleGrid);
    }

    private int solve(int r, int c, int[][] grid) {
        if (r < 0 || c < 0 || grid[r][c] == 1) return 0;
        if (r == 0 && c == 0) return 1;
        return solve(r - 1, c, grid) + solve(r, c - 1, grid);
    }
}
