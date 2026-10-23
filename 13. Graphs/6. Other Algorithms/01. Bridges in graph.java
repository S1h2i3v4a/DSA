/*
QUESTION:-
There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.
A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
Return all critical connections in the network in any order.

Approach:
1. We can use Tarjan's algorithm to find the critical connections in the network.
2. Tarjan's algorithm is used to find bridges in an undirected graph, which are exactly the critical connections.
3. We perform a depth-first search (DFS) on the graph and keep track of the timestamp (time) when each node is visited.
4. We also maintain two arrays, tin and low, to store the timestamp of each node and the lowest timestamp reachable from the node using a back edge or a cross edge, respectively.
5. During the DFS, if we encounter an edge (u, v) such that low[v] > tin[u], it means the edge (u, v) is a critical connection (bridge).
6. We add all such critical connections to the result.

Complexity Analysis:
- Let n be the number of servers and m be the number of connections in the network.
- The time complexity of this approach is O(n + m) since we perform a single DFS on the graph.
- The space complexity is O(n + m) to store the graph adjacency list and O(n) for the auxiliary arrays.
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
