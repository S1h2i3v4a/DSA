/**
 * Problem Name: Floyd Warshall Algorithm (All-Pairs Shortest Path)
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(V^3) where V is the number of vertices
 * Space Complexity: O(1) auxiliary space (modifies input adjacency matrix in-place)
 * 
 * Approach:
 * Dynamic Programming via Intermediate Nodes:
 * Computes shortest distances between all pairs of vertices.
 * 1. Preprocess: Replace -1 (indicating no direct edge) with a sufficiently large value (e.g. 10^8).
 *    Set matrix[i][i] = 0.
 * 2. Three nested loops:
 *    Let k be the intermediate vertex (0 to n - 1):
 *      For each source i:
 *        For each destination j:
 *          matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]).
 * 3. Postprocess: Replace unreachable values (10^8) back with -1.
 * 4. (Negative cycle detection: if any matrix[i][i] < 0, a negative cycle exists).
 */

class Floyd_Warshall_Algorithm {
    public void shortestDistance(int[][] matrix) {
        int n = matrix.length;
        int INF = 100_000_000;

        // Step 1: Preprocess -1 to INF
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = INF;
                }
                if (i == j) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Step 2: Floyd-Warshall DP
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][k] != INF && matrix[k][j] != INF) {
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                    }
                }
            }
        }

        // Step 3: Postprocess INF back to -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == INF) {
                    matrix[i][j] = -1;
                }
            }
        }
    }

    public static void main(String[] args) {
        Floyd_Warshall_Algorithm solver = new Floyd_Warshall_Algorithm();
        int[][] matrix = {
            {0, 2, -1, -1},
            {1, 0, 3, -1},
            {-1, -1, 0, -1},
            {3, 5, 4, 0}
        };

        solver.shortestDistance(matrix);
        System.out.println("All-Pairs Shortest Distances:");
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%3d ", val);
            }
            System.out.println();
        }
        // Expected:
        //   0   2   5  -1 
        //   1   0   3  -1 
        //  -1  -1   0  -1 
        //   3   5   4   0 
    }
}
