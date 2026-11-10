/*
QUESTION:
A directed graph of V vertices is given. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).
Return an array of all safe nodes in sorted order.

APPROACH:
- Reverse all edges of the graph.
- Terminal nodes (out-degree 0 in original) now have in-degree 0 in the reversed graph.
- Run Kahn's algorithm (BFS Topo Sort) on the reversed graph.
- All nodes reachable and processed by Topo Sort are safe!
- Sort safe node indices and return.

TIME COMPLEXITY: O(V + E).
SPACE COMPLEXITY: O(V + E).
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> revAdj = new ArrayList<>();
        for (int i = 0; i < n; i++) revAdj.add(new ArrayList<>());
        int[] inDegree = new int[n];

        for (int i = 0; i < n; i++) {
            for (int neighbor : graph[i]) {
                revAdj.get(neighbor).add(i);
                inDegree[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        List<Integer> safeNodes = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            safeNodes.add(node);

            for (int prev : revAdj.get(node)) {
                inDegree[prev]--;
                if (inDegree[prev] == 0) {
                    queue.offer(prev);
                }
            }
        }

        Collections.sort(safeNodes);
        return safeNodes;
    }
}
