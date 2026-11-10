/*
QUESTION:
Given the number of vertices `V` and a 2D array `edges` representing edges of an undirected graph,
construct and return the adjacency list representation of the graph.

APPROACH:
- Create an adjacency list of size `V`.
- For each edge `[u, v]`, add `v` to `adj.get(u)` and `u` to `adj.get(v)`.

TIME COMPLEXITY: O(V + E) where V is vertices and E is edges.
SPACE COMPLEXITY: O(V + 2E) for adjacency list.
*/

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> printGraph(int V, int edges[][]) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        return adj;
    }
}
