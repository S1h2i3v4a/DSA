/*
QUESTION:
Given a boolean 2D matrix `grid` of size `n * m`, find the number of distinct islands where a group of connected 1s forms an island.
Two islands are considered to be distinct if one cannot be translated into another.

APPROACH:
- Perform DFS on each island.
- Represent island shape by the relative coordinates `(r - baseR, c - baseC)` of each cell from the island's starting cell.
- Store serialized relative coordinate shapes in a `HashSet<String>`.
- Number of distinct islands = size of the set.

TIME COMPLEXITY: O(N * M).
SPACE COMPLEXITY: O(N * M) for visited set.
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Solution {
    private void dfs(int r, int c, int[][] grid, boolean[][] visited, ArrayList<String> shape, int baseR, int baseC) {
        visited[r][c] = true;
        shape.add((r - baseR) + "," + (c - baseC));

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        for (int k = 0; k < 4; k++) {
            int nr = r + dRow[k];
            int nc = c + dCol[k];
            if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length &&
                grid[nr][nc] == 1 && !visited[nr][nc]) {
                dfs(nr, nc, grid, visited, shape, baseR, baseC);
            }
        }
    }

    int countDistinctIslands(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        Set<ArrayList<String>> uniqueIslands = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    ArrayList<String> shape = new ArrayList<>();
                    dfs(i, j, grid, visited, shape, i, j);
                    uniqueIslands.add(shape);
                }
            }
        }
        return uniqueIslands.size();
    }
}
