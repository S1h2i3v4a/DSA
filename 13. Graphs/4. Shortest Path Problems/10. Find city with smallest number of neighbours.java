/* QUESTION:

There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.

Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold. If there are multiple such cities, return the city with the greatest number.

Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.

Example:

Input: n = 4, edges = [[0, 1, 3], [1, 2, 1], [1, 3, 4], [2, 3, 1]], distanceThreshold = 4
Output: 3
Explanation: The figure above describes the graph.
The neighboring cities at a distanceThreshold = 4 for each city are:
City 0 -> [City 1, City 2]
City 1 -> [City 0, City 2, City 3]
City 2 -> [City 0, City 1, City 3]
City 3 -> [City 1, City 2]
Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.

APPROACH:
- We will use Dijkstra's algorithm to find the shortest distances from each city to all other cities in the graph.
- For each city, we will find the count of cities that are reachable through some path and whose distance is at most distanceThreshold.
- We will keep track of this count for each city in a map.
- Finally, we will find the city with the smallest count, and if there are multiple such cities, we will return the city with the greatest number.

COMPLEXITY ANALYSIS:
- The time complexity of Dijkstra's algorithm is O(V^2), where V is the number of cities.
- Since we perform Dijkstra's algorithm for each city, the overall time complexity is O(V^3).
- The space complexity is O(V) for storing the distances and the count of reachable cities for each city.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.*;

class Find_The_City_With_Smallest_Number_Of_Neighbors {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int INF = 100_000_000;
        int[][] dist = new int[n][n];

        for (int[] row : dist) {
            Arrays.fill(row, INF);
        }
        for (int i = 0; i < n; i++) {
            dist[i][i] = 0;
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            dist[u][v] = w;
            dist[v][u] = w;
        }

        // Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        int minReachable = n;
        int bestCity = -1;

        for (int i = 0; i < n; i++) {
            int reachable = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] <= distanceThreshold) {
                    reachable++;
                }
            }

            // Pick city with smallest reachable count, preferring greatest index on tie
            if (reachable <= minReachable) {
                minReachable = reachable;
                bestCity = i;
            }
        }

        return bestCity;
    }

    public static void main(String[] args) {
        Find_The_City_With_Smallest_Number_Of_Neighbors solver = new Find_The_City_With_Smallest_Number_Of_Neighbors();
        int n = 4;
        int[][] edges = {
            {0, 1, 3},
            {1, 2, 1},
            {1, 3, 4},
            {2, 3, 1}
        };
        int distanceThreshold = 4;

        System.out.println("Best city: " + solver.findTheCity(n, edges, distanceThreshold)); // Expected: 3
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
