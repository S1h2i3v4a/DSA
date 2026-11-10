/*
QUESTION:
Given a Directed Graph with V vertices and adj list, check whether it contains any cycle using DFS.

APPROACH:
- Maintain two boolean arrays: `visited` and `pathVisited`.
- When entering a node, set `visited[node] = true` and `pathVisited[node] = true`.
- If an adjacent node is unvisited, recurse.
- If an adjacent node is currently in `pathVisited`, a back-edge (cycle) exists!
- When backtracking, unmark `pathVisited[node] = false`.

TIME COMPLEXITY: O(V + E).
SPACE COMPLEXITY: O(V) for arrays and recursion stack.
*/

import java.util.ArrayList;

class Solution {
    private boolean dfsCheck(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] pathVisited) {
        visited[node] = true;
        pathVisited[node] = true;

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                if (dfsCheck(neighbor, adj, visited, pathVisited)) return true;
            } else if (pathVisited[neighbor]) {
                return true;
            }
        }

        pathVisited[node] = false;
        return false;
    }

    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        boolean[] pathVisited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfsCheck(i, adj, visited, pathVisited)) return true;
            }
        }
        return false;
    }
}
