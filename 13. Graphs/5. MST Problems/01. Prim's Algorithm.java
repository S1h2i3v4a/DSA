/* QUESTION:

Given a weighted, undirected and connected graph of V vertices and E edges, the task is to find the sum of weights of the edges of the Minimum Spanning Tree.

Example:

Input: 
3 3
0 1 5
1 2 3
0 2 1

Output:
4
Explanation:

The Spanning Tree resulting in a weight
of 4 is shown above.

APPROACH:
- We will use Prim's algorithm to find the Minimum Spanning Tree (MST) of the graph.
- The idea is to start from any vertex (let's say vertex 0) and add it to the MST.
- Then, we will add the edges connected to vertex 0 to a priority queue (min-heap), where the edges are sorted based on their weights in ascending order.
- We will keep adding the edges with the minimum weight to the MST and add their connected vertices to the priority queue if they are not already in the MST.
- We will repeat this process until all vertices are added to the MST.

COMPLEXITY ANALYSIS:
- The time complexity of Prim's algorithm is O(V^2) using an adjacency matrix representation, where V is the number of vertices.
- If an adjacency list representation is used, the time complexity reduces to O(E + V log V), where E is the number of edges and log V is the time complexity of priority queue operations.
- The space complexity is O(V) for the priority queue and other data structures.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static int spanningTree(int V, int E, int edges[][]) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(new int[]{e[1], e[2]});
            adj.get(e[1]).add(new int[]{e[0], e[2]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        boolean[] vis = new boolean[V];
        pq.add(new int[]{0, 0});
        int sum = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int wt = curr[0], u = curr[1];
            if (vis[u]) continue;
            vis[u] = true;
            sum += wt;
            for (int[] it : adj.get(u)) {
                int v = it[0], edW = it[1];
                if (!vis[v]) pq.add(new int[]{edW, v});
            }
        }
        return sum;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
