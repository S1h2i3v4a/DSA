/**
 * Problem Name: Critical Connections in a Network (Bridges in Graph)
 * Platform: LeetCode (LC 1192) / GeeksforGeeks
 * Difficulty: Hard
 *
 * Time Complexity: O(V + E) where V is the number of servers and E is connections.size()
 * Space Complexity: O(V + E) for adjacency list, discovery/low-time arrays, and recursion stack
 *
 * Approach:
 *   - A critical connection (or bridge) is an edge whose removal increases the number
 *     of connected components in the graph.
 *   - We employ Tarjan's Bridge-Finding Algorithm based on Depth-First Search (DFS):
 *     1. Maintain `tin[u]`: Time of discovery / insertion of vertex u during DFS.
 *     2. Maintain `low[u]`: Lowest discovery time reachable from u via its subtree
 *        and at most one back-edge.
 *     3. During DFS from u with parent p:
 *        - For each neighbor v of u:
 *          - If v == p: continue (ignore direct edge to parent).
 *          - If visited[v] == true:
 *              Edge (u, v) is a back-edge; update low[u] = min(low[u], tin[v]).
 *          - If visited[v] == false:
 *              Forward tree-edge; recurse dfs(v, u).
 *              Upon return, update low[u] = min(low[u], low[v]).
 *              Bridge Condition:
 *                If low[v] > tin[u], then edge (u, v) is a critical bridge!
 *                (There is no alternative path from v's subtree to u or u's ancestors).
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CriticalConnectionsInANetwork {

    private static int timer;

    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] tin = new int[n];
        int[] low = new int[n];
        boolean[] visited = new boolean[n];
        List<List<Integer>> bridges = new ArrayList<>();
        timer = 0;

        // The problem states the network is connected, but looping over all nodes handles disconnected cases
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, -1, adj, visited, tin, low, bridges);
            }
        }

        return bridges;
    }

    private static void dfs(int u, int p, List<List<Integer>> adj, boolean[] visited,
                            int[] tin, int[] low, List<List<Integer>> bridges) {
        visited[u] = true;
        tin[u] = low[u] = ++timer;

        for (int v : adj.get(u)) {
            if (v == p) {
                continue; // Skip the direct parent edge
            }

            if (visited[v]) {
                // Back-edge to an ancestor
                low[u] = Math.min(low[u], tin[v]);
            } else {
                // Forward tree-edge
                dfs(v, u, adj, visited, tin, low, bridges);
                low[u] = Math.min(low[u], low[v]);

                // If the lowest reachable vertex from v has discovery time strictly greater
                // than u's discovery time, then edge (u, v) is a bridge.
                if (low[v] > tin[u]) {
                    bridges.add(Arrays.asList(u, v));
                }
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1:
        // n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
        // Expected: [[1, 3]]
        int n1 = 4;
        List<List<Integer>> connections1 = new ArrayList<>();
        connections1.add(Arrays.asList(0, 1));
        connections1.add(Arrays.asList(1, 2));
        connections1.add(Arrays.asList(2, 0));
        connections1.add(Arrays.asList(1, 3));

        System.out.println("Test 1 Critical Connections: " + criticalConnections(n1, connections1));

        // Test Case 2:
        // n = 2, connections = [[0,1]]
        // Expected: [[0, 1]]
        int n2 = 2;
        List<List<Integer>> connections2 = new ArrayList<>();
        connections2.add(Arrays.asList(0, 1));

        System.out.println("Test 2 Critical Connections: " + criticalConnections(n2, connections2));
    }
}
