/*
QUESTION:-
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

APPROACH:
- We can use a Breadth-First Search (BFS) traversal to find the distance of the nearest 0 for each cell.
- First, we initialize the distance matrix with -1 for all cells.
- Then, we iterate through the matrix and find all cells with the value 0. For each 0 cell found, we add it to the queue and set its distance to 0 in the distance matrix.
- Next, we perform a BFS starting from the cells with 0. During the BFS, we update the distance of each cell from the nearest 0 cell and continue the BFS until all cells are visited.
- Finally, we return the distance matrix.

COMPLEXITY ANALYSIS:
- Time Complexity: O(m * n), where m is the number of rows and n is the number of columns in the matrix. In the worst case, we may need to visit all the cells of the matrix.
- Space Complexity: O(m * n), where m is the number of rows and n is the number of columns in the matrix. We use additional space for the distance matrix and the queue during BFS.
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.*;

class Zero_One_Matrix {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[][] dist = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    dist[i][j] = 0;
                    queue.offer(new int[]{i, j});
                } else {
                    dist[i][j] = -1; // Unvisited marker
                }
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

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        Zero_One_Matrix solver = new Zero_One_Matrix();
        int[][] mat = {
            {0, 0, 0},
            {0, 1, 0},
            {1, 1, 1}
        };

        int[][] result = solver.updateMatrix(mat);
        System.out.println("Nearest 0 distances:");
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        // Expected:
        // 0 0 0
        // 0 1 0
        // 1 2 1
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
