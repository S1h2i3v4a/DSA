/*
QUESTION:
You are given an undirected graph with unit weight. Find the shortest path from the source vertex to all other vertices, and if it is unreachable to reach any vertex, then return -1 for that vertex.

APPROACH:
- To find the shortest path from the source vertex to all other vertices, we can use a BFS traversal of the graph.
- We create an adjacency list to represent the undirected graph.
- We use a queue to perform the BFS traversal, starting from the source vertex.
- During the BFS traversal, we keep track of the level (or distance) of each vertex from the source vertex.
- We maintain a boolean array to keep track of visited vertices so that we don't visit the same vertex again.
- If a vertex is reachable from the source vertex, its distance will be equal to the number of edges in the shortest path from the source vertex.
- After the BFS traversal is complete, we will have the distance of each vertex from the source vertex.

COMPLEXITY ANALYSIS:
- Time Complexity: O(N+M), where N is the number of vertices and M is the number of edges in the graph. We perform a BFS traversal, visiting each vertex and edge once.
- Space Complexity: O(N), where N is the number of vertices. We use additional space to store the adjacency list, visited array, and the distance array.
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
