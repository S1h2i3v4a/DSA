/*
QUESTION:
Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges, check whether it contains any cycle or not.

APPROACH:
- To check for cycles in a directed graph, we can use Depth-First Search (DFS) with backtracking.
- During the DFS, we maintain a visited array to keep track of nodes that have been visited.
- We perform DFS from each unvisited node to explore the graph and check for cycles.
- If we encounter a node that is already visited in the current DFS traversal, it means we have found a cycle, and we return true.
- If we complete the DFS for all nodes without finding any cycle, we return false.

COMPLEXITY ANALYSIS:
- Time Complexity: O(V + E), where V is the number of vertices (nodes) and E is the number of edges in the graph. We visit each node and each edge exactly once during the DFS.
- Space Complexity: O(V), where V is the number of vertices (nodes) in the graph. We use additional space to store the visited status of the nodes.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] vis = new int[V];
        int[] pathVis = new int[V];
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                if (dfs(i, adj, vis, pathVis)) return true;
            }
        }
        return false;
    }
    private boolean dfs(int node, ArrayList<ArrayList<Integer>> adj, int[] vis, int[] pathVis) {
        vis[node] = 1;
        pathVis[node] = 1;
        for (int it : adj.get(node)) {
            if (vis[it] == 0) {
                if (dfs(it, adj, vis, pathVis)) return true;
            } else if (pathVis[it] == 1) return true;
        }
        pathVis[node] = 0;
        return false;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
