/**
 * Problem Name: Shortest Path in Binary Matrix
 * Platform: LeetCode (LC 1091)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N^2) where N is the grid dimension
 * Space Complexity: O(N^2) for BFS queue
 * 
 * Approach:
 * 8-Directional BFS:
 * In a grid with unit cost per step and 8 possible movement directions, BFS guarantees
 * the shortest path to destination.
 * 1. Base check: If grid[0][0] != 0 or grid[n-1][n-1] != 0, return -1.
 * 2. Enqueue (0, 0, length = 1) and mark grid[0][0] = 1 (visited).
 * 3. While queue is not empty:
 *    - Poll (r, c, len). If (r, c) == (n-1, n-1), return len.
 *    - Explore all 8 adjacent directions.
 *    - If neighbor is within bounds and has value 0:
 *      Mark visited by setting grid[nr][nc] = 1, and offer (nr, nc, len + 1) to queue.
 * 4. Return -1 if bottom-right corner cannot be reached.
 */

import java.util.*;

class Shortest_Path_In_Binary_Matrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1}); // {row, col, length}
        grid[0][0] = 1; // Mark visited

        int[][] dirs = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int len = curr[2];

            if (r == n - 1 && c == n - 1) {
                return len;
            }

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 0) {
                    grid[nr][nc] = 1; // Mark visited
                    queue.offer(new int[]{nr, nc, len + 1});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Shortest_Path_In_Binary_Matrix solver = new Shortest_Path_In_Binary_Matrix();
        int[][] grid1 = {
            {0, 0, 0},
            {1, 1, 0},
            {1, 1, 0}
        };
        System.out.println("Shortest path in grid1: " + solver.shortestPathBinaryMatrix(grid1)); // Expected: 4

        int[][] grid2 = {
            {1, 0, 0},
            {1, 1, 0},
            {1, 1, 0}
        };
        System.out.println("Shortest path in grid2: " + solver.shortestPathBinaryMatrix(grid2)); // Expected: -1
    }
}
