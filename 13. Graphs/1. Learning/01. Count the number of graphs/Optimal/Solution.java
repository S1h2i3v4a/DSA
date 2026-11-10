/*
QUESTION:
Given an integer `n` representing the number of vertices, find the maximum number of undirected graphs that can be formed using these vertices.

APPROACH:
- In an undirected graph with `n` vertices, the maximum number of edges is `n * (n - 1) / 2`.
- Each possible edge can either be present or absent (2 choices).
- Total number of graphs = `2^(n * (n - 1) / 2)`.

TIME COMPLEXITY: O(1) using bit shift or power.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    static long count(int n) {
        long edges = (long) n * (n - 1) / 2;
        return 1L << edges;
    }
}
