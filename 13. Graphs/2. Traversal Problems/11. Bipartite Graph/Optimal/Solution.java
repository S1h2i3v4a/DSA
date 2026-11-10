/*
QUESTION:
There is an undirected graph with `n` nodes. Return true if and only if it is bipartite.
A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in A and a node in B.

APPROACH:
- 2-coloring problem using BFS or DFS.
- Initialize `color` array with -1.
- For each uncolored component, assign color 0.
- For every adjacent node:
  - If uncolored: assign opposite color `1 - color[curr]`.
  - If already colored with same color: graph is not bipartite, return false.

TIME COMPLEXITY: O(V + 2E).
SPACE COMPLEXITY: O(V) for color array and queue.
*/

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private boolean checkBipartiteBFS(int start, int[][] graph, int[] color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        color[start] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbor : graph[curr]) {
                if (color[neighbor] == -1) {
                    color[neighbor] = 1 - color[curr];
                    queue.offer(neighbor);
                } else if (color[neighbor] == color[curr]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        for (int i = 0; i < n; i++) color[i] = -1;

        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                if (!checkBipartiteBFS(i, graph, color)) return false;
            }
        }
        return true;
    }
}
