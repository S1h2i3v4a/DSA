/*
QUESTION:
Given a directed graph, return the Breadth First Traversal of this graph starting from 0th vertex.

APPROACH:
- Use a Queue and a boolean `visited` array.
- Enqueue start vertex 0 and mark visited.
- While queue is not empty:
  - Dequeue node `curr` and add to result.
  - For each unvisited neighbor of `curr`, mark visited and enqueue.

TIME COMPLEXITY: O(V + E).
SPACE COMPLEXITY: O(V) for queue and visited array.
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            bfs.add(curr);

            for (int neighbor : adj.get(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        return bfs;
    }
}
