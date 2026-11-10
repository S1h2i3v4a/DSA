/*
QUESTION:
Given a Directed Graph with V vertices and adj list, find the number of Strongly Connected Components (SCCs) in the graph using Kosaraju's Algorithm.

APPROACH:
- Step 1: DFS traversal to sort nodes by finishing times, pushing to Stack.
- Step 2: Reverse all edges of the graph to get `revAdj`.
- Step 3: Pop nodes from Stack. If unvisited in reversed graph, run DFS and increment SCC count.

TIME COMPLEXITY: O(V + E) three linear passes.
SPACE COMPLEXITY: O(V + E) for reversed graph and stack.
*/

import java.util.ArrayList;
import java.util.Stack;

class Solution {
    private static void dfs1(int node, boolean[] visited, Stack<Integer> stack, ArrayList<ArrayList<Integer>> adj) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) dfs1(neighbor, visited, stack, adj);
        }
        stack.push(node);
    }

    private static void dfs2(int node, boolean[] visited, ArrayList<ArrayList<Integer>> revAdj) {
        visited[node] = true;
        for (int neighbor : revAdj.get(node)) {
            if (!visited[neighbor]) dfs2(neighbor, visited, revAdj);
        }
    }

    public static int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        // Step 1: Push nodes ordered by finish time
        for (int i = 0; i < V; i++) {
            if (!visited[i]) dfs1(i, visited, stack, adj);
        }

        // Step 2: Reverse graph
        ArrayList<ArrayList<Integer>> revAdj = new ArrayList<>();
        for (int i = 0; i < V; i++) revAdj.add(new ArrayList<>());
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                revAdj.get(v).add(u);
            }
        }

        // Step 3: DFS on reversed graph
        for (int i = 0; i < V; i++) visited[i] = false;
        int sccCount = 0;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                sccCount++;
                dfs2(node, visited, revAdj);
            }
        }
        return sccCount;
    }
}
