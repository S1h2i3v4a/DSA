/*
QUESTION:
The problem is to find the shortest distances between every pair of vertices in a given edge-weighted directed graph.
The graph is represented as an adjacency matrix of size n*n. Matrix[i][j] denotes the weight of the edge from i to j. If there is no edge, matrix[i][j] = -1.
Update the matrix in-place.

APPROACH:
- Replace -1 with a large value (except diagonal 0).
- Run 3 nested loops: for each intermediate vertex `k` from 0 to `n - 1`:
  - `matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j])`.
- Restore unreachable entries to -1.

TIME COMPLEXITY: O(V^3).
SPACE COMPLEXITY: O(1) in-place matrix updates.
*/

class Solution {
    public void shortestDistance(int[][] matrix) {
        int n = matrix.length;
        int INF = 100000000;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == -1) matrix[i][j] = INF;
                if (i == j) matrix[i][j] = 0;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] >= INF) matrix[i][j] = -1;
            }
        }
    }
}
