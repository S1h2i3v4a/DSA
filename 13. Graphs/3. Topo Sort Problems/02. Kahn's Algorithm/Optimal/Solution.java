/*
QUESTION:
Perform Topological Sort on a DAG using Kahn's Algorithm (BFS).

APPROACH:
- Compute in-degrees of all vertices.
- Enqueue all vertices with in-degree 0.
- While queue is not empty:
  - Dequeue node `curr`, add to topo order.
  - Decrement in-degree of all neighbors of `curr`. If in-degree reaches 0, enqueue.

TIME COMPLEXITY: O(V + E).
SPACE COMPLEXITY: O(V) for in-degree array and queue.
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] inDegree = new int[V];
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) inDegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int[] topo = new int[V];
        int idx = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            topo[idx++] = node;

            for (int neighbor : adj.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return topo;
    }
}
