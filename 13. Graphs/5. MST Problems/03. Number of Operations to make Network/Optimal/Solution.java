/*
QUESTION:
There are `n` computers numbered from 0 to `n - 1` and connections between them.
Return the minimum number of times you need to repeatedly take an existing cable between two computers and connect them to another pair
to connect all computers. If impossible, return -1.

APPROACH:
- To connect `n` computers, we need at least `n - 1` cables. If `connections.length < n - 1`, return -1.
- Use Disjoint Set Union (DSU) to find the number of connected components `components`.
- Minimum operations needed = `components - 1`.

TIME COMPLEXITY: O(N + E * alpha(N)).
SPACE COMPLEXITY: O(N) for DSU.
*/

class Solution {
    private static class DisjointSet {
        int[] parent;
        int components;

        DisjointSet(int n) {
            parent = new int[n];
            components = n;
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
                parent[rootU] = rootV;
                components--;
            }
        }
    }

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) return -1;
        DisjointSet ds = new DisjointSet(n);

        for (int[] conn : connections) {
            ds.union(conn[0], conn[1]);
        }
        return ds.components - 1;
    }
}
