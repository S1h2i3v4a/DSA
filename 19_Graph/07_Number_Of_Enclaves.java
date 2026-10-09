/**
 * Problem Name: Number of Enclaves
 * Platform: LeetCode (LC 1020)
 * Difficulty: Medium
 * 
 * Time Complexity: O(M * N) where M is rows and N is columns
 * Space Complexity: O(M * N) for BFS queue / DFS recursion stack
 * 
 * Approach:
 * Boundary Flood Fill:
 * Any land cell (1) connected to the grid boundary can be walked off the grid.
 * 1. Start BFS/DFS from all land cells on the 4 boundaries (first/last row, first/last col).
 * 2. Mark all reachable land cells as visited (or convert to 0).
 * 3. Traverse the grid and count remaining 1s in the interior (enclaves).
 */

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
