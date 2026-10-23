/* QUESTION:

Given a weighted, directed and connected graph of V vertices and E edges, Find the shortest distance of all the vertex's from the source vertex S.
Note: If the Graph contains a negative cycle then return an array consisting of only -1.

Example:

Input: E = [[0,1,5],[1,0,3],[1,2,-1],[2,0,1]], S = 2
Output: 1 6 0
Explanation:
For nodes 2 to 0, we can follow the path - 2 -> 0. This has a distance of 1.
For nodes 2 to 1, we can follow the path - 2 -> 0 -> 1, which has a distance of 1 + 5 = 6.

APPROACH:
- We can use Bellman-Ford algorithm to find the shortest distance of all the nodes from the given source vertex S.
- The Bellman-Ford algorithm can handle negative edge weights and identify negative cycles.
- We will initialize an array dis to store the shortest distance of each node from the source vertex. Initialize all distances to a very large value except for the source vertex, which will have a distance of 0.
- Run a loop for V-1 times (V is the number of vertices), and in each iteration, relax all the edges to minimize the distance.
- If there is a negative cycle, the distance will keep decreasing in the Vth iteration as well. In this case, we will return an array containing only -1.
- Otherwise, we return the dis array with the shortest distances of all nodes from the source vertex.

COMPLEXITY ANALYSIS:
- The time complexity of the Bellman-Ford algorithm is O(V*E), where V is the number of vertices and E is the number of edges.
- The space complexity is O(V) to store the distance values.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.*;

class Bellman_Ford_Algorithm {
    public int[] bellmanFord(int V, int[][] edges, int src) {
        int INF = 100_000_000;
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        // Relax all edges V - 1 times
        for (int i = 0; i < V - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if (dist[u] != INF && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // V-th iteration: Check for negative weight cycle
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if (dist[u] != INF && dist[u] + w < dist[v]) {
                return new int[]{-1}; // Negative cycle detected
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        Bellman_Ford_Algorithm solver = new Bellman_Ford_Algorithm();
        int V = 5;
        int[][] edges = {
            {0, 1, -1},
            {0, 2, 4},
            {1, 2, 3},
            {1, 3, 2},
            {1, 4, 2},
            {3, 2, 5},
            {3, 1, 1},
            {4, 3, -3}
        };
        int src = 0;

        int[] dist = solver.bellmanFord(V, edges, src);
        System.out.println("Distances from source 0: " + Arrays.toString(dist));
        // Expected: [0, -1, 2, -2, 1]
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
