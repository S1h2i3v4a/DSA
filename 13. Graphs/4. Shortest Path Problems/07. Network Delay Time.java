/* QUESTION:

You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.

Example:

Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2

APPROACH:
- We can use Dijkstra's algorithm to find the minimum time it takes for all the nodes to receive the signal.
- We will create an adjacency list to represent the graph, where each node will have a list of neighboring nodes with their corresponding signal travel time.
- We will use a priority queue to perform Dijkstra's algorithm starting from the given node k.
- During Dijkstra's algorithm, we will keep track of the minimum time to reach each node from the given node k.
- Continue the algorithm until we have explored all nodes in the graph.
- Return the maximum time among all the nodes' signal travel times, as this represents the minimum time it takes for all the nodes to receive the signal.

COMPLEXITY ANALYSIS:
- The time complexity of Dijkstra's algorithm is O(E*log(V)), where E is the number of edges and V is the number of vertices.
- The space complexity is O(V) to store the distance values.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int[] t : times) adj.get(t[0]).add(new int[]{t[1], t[2]});
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{k, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], d = curr[1];
            if (d > dist[u]) continue;
            for (int[] edge : adj.get(u)) {
                int v = edge[0], wt = edge[1];
                if (dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            max = Math.max(max, dist[i]);
        }
        return max;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
