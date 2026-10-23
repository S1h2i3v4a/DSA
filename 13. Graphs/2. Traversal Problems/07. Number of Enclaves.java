/*
QUESTION:-
You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

APPROACH:
- We can use Depth-First Search (DFS) to mark all land cells connected to the boundary of the grid as uncountable (i.e., cells that we can walk off the boundary).
- First, we initialize a copy of the grid called 'vis' to store the visited status of each cell.
- Then, we perform DFS from all land cells located at the boundary of the grid. During the DFS, we mark all connected land cells as visited by changing their value to -1 in the 'vis' matrix.
- After performing DFS from boundary cells, all remaining land cells in the 'vis' matrix represent cells that we cannot walk off the boundary of the grid.
- Finally, we count the number of land cells in the 'vis' matrix and return the count as the result.

COMPLEXITY ANALYSIS:
- Time Complexity: O(m * n), where m is the number of rows and n is the number of columns in the matrix. In the worst case, we may need to visit all the cells of the matrix during DFS.
- Space Complexity: O(m * n), where m is the number of rows and n is the number of columns in the matrix. We use additional space for the 'vis' matrix.
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.*;

class Number_Of_Enclaves {
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();

        // Add boundary land cells to queue and mark visited (0)
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) {
                grid[i][0] = 0;
                queue.offer(new int[]{i, 0});
            }
            if (grid[i][n - 1] == 1) {
                grid[i][n - 1] = 0;
                queue.offer(new int[]{i, n - 1});
            }
        }

        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1) {
                grid[0][j] = 0;
                queue.offer(new int[]{0, j});
            }
            if (grid[m - 1][j] == 1) {
                grid[m - 1][j] = 0;
                queue.offer(new int[]{m - 1, j});
            }
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                    grid[nr][nc] = 0; // Mark visited
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        // Count remaining land cells (enclaves)
        int enclaves = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    enclaves++;
                }
            }
        }

        return enclaves;
    }

    public static void main(String[] args) {
        Number_Of_Enclaves solver = new Number_Of_Enclaves();
        int[][] grid1 = {
            {0, 0, 0, 0},
            {1, 0, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}
        };
        System.out.println("Enclaves count in grid1: " + solver.numEnclaves(grid1)); // Expected: 3

        int[][] grid2 = {
            {0, 1, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0}
        };
        System.out.println("Enclaves count in grid2: " + solver.numEnclaves(grid2)); // Expected: 0
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
