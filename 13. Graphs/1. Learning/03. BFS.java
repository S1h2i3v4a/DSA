/*
Question:
Given a directed graph, perform Breadth First Traversal (BFS) of the graph starting from vertex 0 and visit all the nodes directly or indirectly connected to Node 0.

Explanation:
- We initialize an empty vector 'ans' to store the BFS traversal.
- We also initialize a vector 'vis' to keep track of visited nodes, initialized with 'false' for all nodes.
- We use a queue 'q' to perform BFS. We start by pushing vertex 0 into the queue and mark it as visited.
- While the queue is not empty, we pop the front element and add it to the 'ans' vector.
- For each adjacent vertex of the current node, if it has not been visited, we push it into the queue and mark it as visited.
- We continue this process until the queue becomes empty and all connected nodes are visited.
- Finally, we return the 'ans' vector containing the BFS traversal.

Time Complexity:
- The time complexity is O(V + E), where V is the number of vertices and E is the number of edges in the graph. In the worst case, we visit all the vertices and edges.

Space Complexity:
- The space complexity is O(V), where V is the number of vertices, as we use extra space for the 'vis' vector and the 'q' queue.

*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean[] vis = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        vis[0] = true;
        while (!q.isEmpty()) {
            int node = q.poll();
            bfs.add(node);
            for (int it : adj.get(node)) {
                if (!vis[it]) {
                    vis[it] = true;
                    q.add(it);
                }
            }
        }
        return bfs;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
