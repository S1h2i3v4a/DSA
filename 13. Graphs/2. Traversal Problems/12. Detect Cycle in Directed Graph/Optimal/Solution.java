/*
QUESTION:
Detect cycle in directed graph using Kahn's Algorithm (BFS).

APPROACH:
- Calculate in-degree of all vertices.
- Enqueue all vertices with `inDegree == 0`.
- Maintain a counter `count = 0`.
- Dequeue node `curr`, increment `count`.
- For each neighbor of `curr`, decrement in-degree. If in-degree reaches 0, enqueue.
- If `count == V`, topological ordering is possible (DAG). If `count < V`, cycle exists!

TIME COMPLEXITY: O(V + E).
SPACE COMPLEXITY: O(V) for in-degree array and queue.
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] inDegree = new int[V];
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                inDegree[v]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            count++;

            for (int neighbor : adj.get(curr)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return count != V;
    }
}
