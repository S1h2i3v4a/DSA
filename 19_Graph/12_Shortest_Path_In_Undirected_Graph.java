/**
 * Problem Name: Shortest Path in Undirected Graph with Unit Weights
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(V + 2E) where V is vertices and E is edges
 * Space Complexity: O(V + 2E) for adjacency list, distance array, and BFS queue
 * 
 * Approach:
 * BFS Shortest Path for Unit Weights:
 * In a graph where every edge has equal weight (1), standard BFS guarantees finding the
 * shortest distance to every vertex in minimum number of edge traversals:
 * 1. Build adjacency list representation from given edge list.
 * 2. Initialize distance array dist[] with -1 (or infinity), and set dist[src] = 0.
 * 3. Enqueue src into a Queue.
 * 4. While Queue is not empty:
 *    - Poll vertex u.
 *    - For each neighbor v: if dist[u] + 1 < dist[v] (or dist[v] == -1), set dist[v] = dist[u] + 1
 *      and offer v to Queue.
 * 5. Return dist array.
 */

import java.util.*;

class Shortest_Path_In_Undirected_Graph {
    public int[] shortestPath(int[][] edges, int n, int m, int src) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[src] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : adj.get(u)) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    queue.offer(v);
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        Shortest_Path_In_Undirected_Graph solver = new Shortest_Path_In_Undirected_Graph();
        int n = 9, m = 10;
        int[][] edges = {
            {0, 1}, {0, 3}, {3, 4}, {4, 5}, {5, 6},
            {1, 2}, {2, 6}, {6, 7}, {7, 8}, {6, 8}
        };
        int src = 0;

        int[] dist = solver.shortestPath(edges, n, m, src);
        System.out.println("Shortest distances from source " + src + ": " + Arrays.toString(dist));
        // Expected: [0, 1, 2, 1, 2, 3, 3, 4, 4]
    }
}
