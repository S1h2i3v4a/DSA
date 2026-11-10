/*
QUESTION:
Count provinces in O(N^2 * alpha(N)) using Disjoint Set Union (DSU).

APPROACH:
- Initialize DSU with `n` individual components.
- Iterate through matrix: if `isConnected[i][j] == 1` and `i != j`, union components of `i` and `j`.
- Total provinces = number of unique roots.

TIME COMPLEXITY: O(N^2 * alpha(N)).
SPACE COMPLEXITY: O(N) for DSU parent and rank arrays.
*/

class Solution {
    private static class DisjointSet {
        int[] parent, rank;
        int count;

        DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            count = n;
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }

        void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU != rootV) {
                if (rank[rootU] < rank[rootV]) {
                    parent[rootU] = rootV;
                } else if (rank[rootU] > rank[rootV]) {
                    parent[rootV] = rootU;
                } else {
                    parent[rootV] = rootU;
                    rank[rootU]++;
                }
                count--;
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        DisjointSet ds = new DisjointSet(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    ds.union(i, j);
                }
            }
        }
        return ds.count;
    }
}
