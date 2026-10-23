/*
QUESTION:
Given a weighted, undirected, and connected graph of V vertices and an adjacency list 'adj', where 'adj[i]' is a list of lists containing two integers where the first integer of each list j denotes there is an edge between node i and node j, and the second integer corresponds to the weight of that edge. You are also given the source vertex S. You need to find the shortest distance of all vertices from the source vertex S. You have to return a list of integers denoting the shortest distance between each node and the source vertex S.

APPROACH:
- We can use Dijkstra's algorithm to find the shortest distance from the source vertex to all other vertices in a weighted graph.
- The algorithm maintains a priority queue (min-heap) to store the vertices based on their tentative distances from the source vertex. We start with the source vertex and update the distances of its neighbors, pushing them into the priority queue.
- We continue this process until all vertices are visited and their distances are finalized. The priority queue ensures that we always pick the vertex with the minimum tentative distance for processing.
- We initialize the distance array 'dis' with a large value (infinity) for all vertices, except the source vertex whose distance is set to 0.
- We push the source vertex into the priority queue along with its distance, and then perform Dijkstra's algorithm.
- After the algorithm completes, we have the shortest distances of all vertices from the source vertex in the 'dis' array.

COMPLEXITY ANALYSIS:
- Time Complexity: O(E + log(V)), where E is the number of edges and V is the number of vertices in the graph. The time complexity is dominated by the priority queue operations in Dijkstra's algorithm.
- Space Complexity: O(V + E), where V is the number of vertices and E is the number of edges in the graph. We use additional space for the adjacency list, the distance array, and the priority queue.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e9);
        dist[S] = 0;
        pq.add(new int[]{S, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], d = curr[1];
            if (d > dist[u]) continue;
            for (ArrayList<Integer> edge : adj.get(u)) {
                int v = edge.get(0), wt = edge.get(1);
                if (dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }
        return dist;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
