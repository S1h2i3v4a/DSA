/*
QUESTION:
You are given an `m x n` binary matrix `grid`, where 0 represents a sea cell and 1 represents a land cell.
A move consists of walking from one land cell to another 4-directionally adjacent land cell or walking off the boundary of the grid.
Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

APPROACH:
- Any 1 connected to the boundary can walk off the grid.
- Run DFS/BFS from all boundary 1s and change them to 0.
- Count remaining 1s in the grid.

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(M * N) recursion stack.
*/

class Solution {
    private void dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != 1) return;
        grid[r][c] = 0;
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }

    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) dfs(grid, i, 0);
            if (grid[i][n - 1] == 1) dfs(grid, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1) dfs(grid, 0, j);
            if (grid[m - 1][j] == 1) dfs(grid, m - 1, j);
        }

        int enclaves = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) enclaves++;
            }
        }
        return enclaves;
    }
}
