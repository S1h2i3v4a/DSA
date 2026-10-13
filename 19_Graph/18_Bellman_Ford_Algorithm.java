/**
 * Problem Name: Bellman Ford Algorithm (Single Source Shortest Path with Negative Edges)
 * Platform: GeeksforGeeks
 * Difficulty: Medium / Hard
 * 
 * Time Complexity: O(V * E) where V is vertices and E is edges
 * Space Complexity: O(V) for distance array
 * 
 * Approach:
 * Dynamic Edge Relaxation:
 * Bellman-Ford can handle graphs with negative edge weights and detect negative weight cycles.
 * 1. Initialize dist[V] with 10^8 (infinity), and set dist[S] = 0.
 * 2. Relax all edges (V - 1) times:
 *    For each edge (u -> v with weight w):
 *      If dist[u] != 1e8 && dist[u] + w < dist[v]:
 *        dist[v] = dist[u] + w;
 * 3. Run a V-th relaxation check:
 *    If any edge can still be relaxed:
 *      A negative weight cycle is reachable from source!
 *      Return new int[]{-1}.
 * 4. Return dist array.
 */

import java.util.*;

class Bellman_Ford_Algorithm {
    public int[] bellmanFord(int V, int[][] edges, int src) {
        int INF = 100_000_000;
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        // Relax all edges V - 1 times
        for (int i = 0; i < V - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if (dist[u] != INF && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // V-th iteration: Check for negative weight cycle
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if (dist[u] != INF && dist[u] + w < dist[v]) {
                return new int[]{-1}; // Negative cycle detected
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        Bellman_Ford_Algorithm solver = new Bellman_Ford_Algorithm();
        int V = 5;
        int[][] edges = {
            {0, 1, -1},
            {0, 2, 4},
            {1, 2, 3},
            {1, 3, 2},
            {1, 4, 2},
            {3, 2, 5},
            {3, 1, 1},
            {4, 3, -3}
        };
        int src = 0;

        int[] dist = solver.bellmanFord(V, edges, src);
        System.out.println("Distances from source 0: " + Arrays.toString(dist));
        // Expected: [0, -1, 2, -2, 1]
    }
}
