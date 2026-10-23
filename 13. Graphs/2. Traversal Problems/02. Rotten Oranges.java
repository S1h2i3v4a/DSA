/*
Question: 

You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.

Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

Example:

Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Approach:

1. Use Breadth-First Search (BFS) to rot the oranges.
2. Initialize a queue to store the rotten oranges.
3. Iterate through the grid to find the rotten oranges and count the number of fresh oranges.
4. Perform BFS starting from each rotten orange and keep track of the time required to rot all the fresh oranges.
5. For each level in BFS (each minute), check adjacent cells to a rotten orange and mark them as rotten if they contain a fresh orange. Decrease the fresh count and add the newly rotten oranges to the queue.
6. Repeat this process until all fresh oranges are rotten or the queue is empty.
7. Return the time required (minutes) if all fresh oranges are rotten, otherwise, return -1.

Time Complexity: O(m * n) - where 'm' is the number of rows and 'n' is the number of columns in the grid.
Space Complexity: O(m * n) - due to the queue and the grid.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
