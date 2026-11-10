/*
QUESTION:
Given a connected undirected graph, return a list containing the Depth First Traversal of this graph starting from 0th vertex.

APPROACH:
- Use recursive DFS with a boolean `visited` array.
- Mark current node visited, add to result.
- For each unvisited neighbor, recursively call DFS.

TIME COMPLEXITY: O(V + 2E).
SPACE COMPLEXITY: O(V) recursion stack and visited array.
*/

import java.util.ArrayList;

class Solution {
    private void dfs(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> result) {
        visited[node] = true;
        result.add(node);

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, adj, result);
            }
        }
    }

    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[V];
        dfs(0, visited, adj, result);
        return result;
    }
}
