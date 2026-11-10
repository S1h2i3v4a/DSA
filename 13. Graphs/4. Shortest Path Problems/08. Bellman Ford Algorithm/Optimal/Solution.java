/*
QUESTION:
Given a weighted, directed and connected graph of V vertices and list of edges,
find the shortest distance of all vertices from the source vertex S.
If a negative weight cycle is detected, return {-1}.

APPROACH:
- Relax all edges `V - 1` times:
  - If `dist[u] != 1e8` and `dist[u] + wt < dist[v]`, update `dist[v] = dist[u] + wt`.
- 1 more relaxation check: if any distance reduces on `V`th relaxation, a negative cycle exists!

TIME COMPLEXITY: O(V * E).
SPACE COMPLEXITY: O(V) for distance array.
*/

import java.util.Arrays;

class Solution {
    public int[] bellmanFord(int V, int[][] edges, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, 100000000);
        dist[src] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];
                if (dist[u] != 100000000 && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }

        // Negative cycle check
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            if (dist[u] != 100000000 && dist[u] + wt < dist[v]) {
                return new int[]{-1};
            }
        }
        return dist;
    }
}
