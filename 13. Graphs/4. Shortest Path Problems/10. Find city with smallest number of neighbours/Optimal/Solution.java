/*
QUESTION:
There are `n` cities numbered from 0 to `n - 1`. Given the array `edges` where `edges[i] = [fromi, toi, weighti]`
and an integer `distanceThreshold`, return the city with the smallest number of cities that are reachable through some path
whose distance is at most `distanceThreshold`. If multiple such cities exist, return the city with the greatest number.

APPROACH:
- Run Floyd-Warshall to find all-pairs shortest paths `dist[n][n]`.
- For each city `i`, count cities `j` with `dist[i][j] <= distanceThreshold`.
- Find city with minimum reachable count (breaking ties by picking the largest index).

TIME COMPLEXITY: O(N^3) Floyd-Warshall.
SPACE COMPLEXITY: O(N^2) for distance matrix.
*/

import java.util.Arrays;

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, 100000000);
        for (int i = 0; i < n; i++) dist[i][i] = 0;

        for (int[] e : edges) {
            dist[e[0]][e[1]] = e[2];
            dist[e[1]][e[0]] = e[2];
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int bestCity = -1;
        int minReachable = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int reachable = 0;
            for (int j = 0; j < n; j++) {
                if (dist[i][j] <= distanceThreshold) {
                    reachable++;
                }
            }
            if (reachable <= minReachable) {
                minReachable = reachable;
                bestCity = i;
            }
        }
        return bestCity;
    }
}
