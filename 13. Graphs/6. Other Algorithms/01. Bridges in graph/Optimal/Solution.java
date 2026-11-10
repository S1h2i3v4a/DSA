/*
QUESTION:
There are `n` servers numbered from 0 to `n - 1` connected by undirected server-to-server connections.
A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
Return all critical connections in the network in any order.

APPROACH:
- Tarjan's Bridge-Finding Algorithm using DFS.
- Maintain `tin[node]` (discovery time) and `low[node]` (lowest reachable discovery time).
- For edge `(node, neighbor)`:
  - If neighbor == parent: continue.
  - If visited: `low[node] = Math.min(low[node], tin[neighbor])`.
  - If unvisited:
    - Recurse on neighbor.
    - `low[node] = Math.min(low[node], low[neighbor])`.
    - If `low[neighbor] > tin[node]`: edge `(node, neighbor)` is a bridge!

TIME COMPLEXITY: O(V + 2E).
SPACE COMPLEXITY: O(V + 2E) for adjacency list and arrays.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private int timer = 1;

    private void dfs(int node, int parent, List<List<Integer>> adj,
                     int[] tin, int[] low, boolean[] visited, List<List<Integer>> bridges) {
        visited[node] = true;
        tin[node] = low[node] = timer++;

        for (int neighbor : adj.get(node)) {
            if (neighbor == parent) continue;

            if (!visited[neighbor]) {
                dfs(neighbor, node, adj, tin, low, visited, bridges);
                low[node] = Math.min(low[node], low[neighbor]);

                if (low[neighbor] > tin[node]) {
                    bridges.add(Arrays.asList(node, neighbor));
                }
            } else {
                low[node] = Math.min(low[node], tin[neighbor]);
            }
        }
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (List<Integer> conn : connections) {
            adj.get(conn.get(0)).add(conn.get(1));
            adj.get(conn.get(1)).add(conn.get(0));
        }

        int[] tin = new int[n];
        int[] low = new int[n];
        boolean[] visited = new boolean[n];
        List<List<Integer>> bridges = new ArrayList<>();

        dfs(0, -1, adj, tin, low, visited, bridges);
        return bridges;
    }
}
