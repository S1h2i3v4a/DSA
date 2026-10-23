/* QUESTION:

Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
1. All the visited cells of the path are 0.
2. All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
3. The length of a clear path is the number of visited cells of this path.

Example:
Input: grid = [[0,1],[1,0]]
Output: 2

APPROACH:
- We can use Breadth-First Search (BFS) to find the shortest path.
- Start BFS from the top-left cell (0, 0) and explore its neighboring cells in 8 directions.
- Mark the visited cells and continue BFS until reaching the bottom-right cell (n - 1, n - 1).
- The number of levels traversed in BFS will represent the length of the shortest clear path.
- If the bottom-right cell cannot be reached or there is no clear path, return -1.

COMPLEXITY ANALYSIS:
- The time complexity of BFS is O(n^2) as it explores all cells in the n x n matrix.
- The space complexity is also O(n^2) due to the usage of the queue and the visited matrix.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
