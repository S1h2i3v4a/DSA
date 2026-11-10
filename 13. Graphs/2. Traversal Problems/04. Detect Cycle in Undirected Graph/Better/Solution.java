/*
QUESTION:
Given an undirected graph with `V` vertices and `E` edges, detect if it contains a cycle using BFS.

APPROACH:
- Use BFS storing pairs `(node, parent)`.
- If an adjacent node is visited and is NOT the parent of the current node, a cycle is detected.

TIME COMPLEXITY: O(V + 2E).
SPACE COMPLEXITY: O(V) for queue and visited array.
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private boolean checkCycleBFS(int start, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, -1});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int parent = curr[1];

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(new int[]{neighbor, node});
                } else if (neighbor != parent) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (checkCycleBFS(i, visited, adj)) return true;
            }
        }
        return false;
    }
}
