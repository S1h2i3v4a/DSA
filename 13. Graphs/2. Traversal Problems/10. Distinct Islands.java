/*
QUESTION:
Given a boolean 2D matrix grid of size n * m. You have to find the number of distinct islands where a group of connected 1s (horizontally or vertically) forms an island. Two islands are considered to be distinct if and only if one island is not equal to another (not rotated or reflected).

APPROACH:
- We can model this problem as a graph where each group of connected 1s forms an island.
- We can use Depth-First Search (DFS) to traverse the grid and identify each island.
- During the DFS, we keep track of the path taken to traverse each island. The path can be represented as a string, where each character in the string represents the direction of movement during the DFS (U for Up, D for Down, R for Right, and L for Left).
- We use a set to store the paths of all distinct islands. As sets store unique elements, we will have only unique paths in the set.
- Finally, we return the size of the set, which gives us the number of distinct islands.

COMPLEXITY ANALYSIS:
- Time Complexity: O(n * m), where n is the number of rows and m is the number of columns in the grid. We visit each cell at most once during the DFS.
- Space Complexity: O(n * m), where n is the number of rows and m is the number of columns in the grid. We use additional space to store the visited status of each cell and the paths of the islands in the set.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int countDistinctIslands(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        HashSet<ArrayList<String>> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !vis[i][j]) {
                    ArrayList<String> vec = new ArrayList<>();
                    dfs(i, j, vis, grid, vec, i, j);
                    set.add(vec);
                }
            }
        }
        return set.size();
    }
    private void dfs(int row, int col, boolean[][] vis, int[][] grid, ArrayList<String> vec, int r0, int c0) {
        vis[row][col] = true;
        vec.add((row - r0) + " " + (col - c0));
        int[] delrow = {-1, 0, 1, 0};
        int[] delcol = {0, -1, 0, 1};
        for (int i = 0; i < 4; i++) {
            int nrow = row + delrow[i], ncol = col + delcol[i];
            if (nrow >= 0 && nrow < grid.length && ncol >= 0 && ncol < grid[0].length && !vis[nrow][ncol] && grid[nrow][ncol] == 1) {
                dfs(nrow, ncol, vis, grid, vec, r0, c0);
            }
        }
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
