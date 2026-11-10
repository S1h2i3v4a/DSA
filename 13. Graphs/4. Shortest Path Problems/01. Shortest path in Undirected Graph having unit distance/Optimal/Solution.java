/*
QUESTION:
You are given an Undirected Graph having unit weight, find the shortest path from src to all the vertices and if it is unreachable to reach any vertex, then return -1 for that vertex.

APPROACH:
- In unweighted graph (unit weight), BFS guarantees shortest path!
- Initialize `dist` array with infinity, `dist[src] = 0`.
- BFS queue: when relaxing an edge `dist[neighbor] = dist[curr] + 1`, enqueue neighbor.
- Replace unreachable distances with -1.

TIME COMPLEXITY: O(V + 2E).
SPACE COMPLEXITY: O(V + 2E) for adjacency list and queue.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int[] shortestPath(int[][] edges, int n, int m, int src) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();

        dist[src] = 0;
        queue.offer(src);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : adj.get(node)) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[node] + 1;
                    queue.offer(neighbor);
                }
            }
        }
        return dist;
    }
}
