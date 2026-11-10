/*
QUESTION:
Detect cycle in undirected graph using DFS.

APPROACH:
- Recursive DFS tracking `(node, parent)`.
- Mark node visited.
- For each neighbor:
  - If unvisited: recursively visit. If recursive call detects cycle, return true.
  - Else if `neighbor != parent`: cycle detected, return true.

TIME COMPLEXITY: O(V + 2E).
SPACE COMPLEXITY: O(V) recursion stack.
*/

import java.util.ArrayList;

class Solution {
    private boolean checkCycleDFS(int node, int parent, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
        visited[node] = true;

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                if (checkCycleDFS(neighbor, node, visited, adj)) return true;
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }

    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (checkCycleDFS(i, -1, visited, adj)) return true;
            }
        }
        return false;
    }
}
