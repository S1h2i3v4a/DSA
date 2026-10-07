/**
 * Problem Name: Rotting Oranges
 * Platform: LeetCode (LC 994)
 * Difficulty: Medium
 * 
 * Time Complexity: O(M * N) where M is rows and N is columns
 * Space Complexity: O(M * N) for BFS queue
 * 
 * Approach:
 * Multi-Source BFS:
 * 1. Count fresh oranges and add all initially rotten orange coordinates (r, c) to Queue.
 * 2. If freshCount == 0, return 0 (no fresh oranges to rot).
 * 3. While queue is not empty and freshCount > 0:
 *    - Process current level (current minute).
 *    - For each rotten orange, check 4 neighbors (up, down, left, right).
 *    - If neighbor is fresh (1), mark it rotten (2), decrement freshCount, and offer to queue.
 *    - Increment minutes elapsed.
 * 4. Return freshCount == 0 ? minutes : -1.
 */

import java.util.*;

class Rotting_Oranges {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        if (freshCount == 0) return 0;

        int minutes = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty() && freshCount > 0) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];

                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];

                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2; // Make it rotten
                        freshCount--;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
            minutes++;
        }

        return freshCount == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        Rotting_Oranges solver = new Rotting_Oranges();
        int[][] grid1 = {
            {2, 1, 1},
            {1, 1, 0},
            {0, 1, 1}
        };
        System.out.println("Minutes to rot grid1: " + solver.orangesRotting(grid1)); // Expected: 4

        int[][] grid2 = {
            {2, 1, 1},
            {0, 1, 1},
            {1, 0, 1}
        };
        System.out.println("Minutes to rot grid2: " + solver.orangesRotting(grid2)); // Expected: -1
    }
}
