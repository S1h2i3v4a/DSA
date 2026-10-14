/**
 * Problem Name: Disjoint Set (Union by Rank and Path Compression & Union by Size)
 * Platform: GeeksforGeeks / Standard Data Structure
 * Difficulty: Medium
 *
 * Time Complexity:
 *   - Find with Path Compression: O(alpha(V)) amortized ~ O(1)
 *   - Union by Rank / Size: O(alpha(V)) amortized ~ O(1)
 * Space Complexity: O(V) for parent, rank, and size arrays
 *
 * Approach:
 *   - Disjoint Set Union (DSU) maintains a collection of disjoint dynamic sets.
 *   - Two primary optimizations make operations nearly constant time:
 *     1. Path Compression: Flattens tree structure during find operations,
 *        pointing all nodes on the path directly to the representative root.
 *     2. Union by Rank / Union by Size: Attaches the shallower / smaller tree
 *        under the root of the deeper / larger tree to minimize tree height.
 */

import java.util.Arrays;

class DisjointSet {

    static class DSU {
        private final int[] parent;
        private final int[] rank;
        private final int[] size;

        // Constructor initializing n elements (0 to n-1 or 1 to n)
        public DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            size = new int[n + 1];

            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                rank[i] = 0;
                size[i] = 1;
            }
        }

        // Find operation with Path Compression
        public int findUPar(int node) {
            if (node == parent[node]) {
                return node;
            }
            return parent[node] = findUPar(parent[node]); // Path compression
        }

        // Union by Rank
        public boolean unionByRank(int u, int v) {
            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);

            if (ulp_u == ulp_v) {
                return false; // Already in the same component
            }

            if (rank[ulp_u] < rank[ulp_v]) {
                parent[ulp_u] = ulp_v;
            } else if (rank[ulp_v] < rank[ulp_u]) {
                parent[ulp_v] = ulp_u;
            } else {
                parent[ulp_v] = ulp_u;
                rank[ulp_u]++;
            }
            return true;
        }

        // Union by Size
        public boolean unionBySize(int u, int v) {
            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);

            if (ulp_u == ulp_v) {
                return false; // Already in the same component
            }

            if (size[ulp_u] < size[ulp_v]) {
                parent[ulp_u] = ulp_v;
                size[ulp_v] += size[ulp_u];
            } else {
                parent[ulp_v] = ulp_u;
                size[ulp_u] += size[ulp_v];
            }
            return true;
        }

        // Check if two elements belong to the same connected component
        public boolean isConnected(int u, int v) {
            return findUPar(u) == findUPar(v);
        }

        // Get size of component containing node
        public int getComponentSize(int node) {
            return size[findUPar(node)];
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Disjoint Set Union by Rank ===");
        DSU dsRank = new DSU(7);
        dsRank.unionByRank(1, 2);
        dsRank.unionByRank(2, 3);
        dsRank.unionByRank(4, 5);
        dsRank.unionByRank(6, 7);
        dsRank.unionByRank(5, 6);

        // Check connectivity
        System.out.println("Are 3 and 7 connected? " + dsRank.isConnected(3, 7)); // Expected: false
        dsRank.unionByRank(3, 7);
        System.out.println("After union(3, 7), are 3 and 7 connected? " + dsRank.isConnected(3, 7)); // Expected: true

        System.out.println("\n=== Testing Disjoint Set Union by Size ===");
        DSU dsSize = new DSU(7);
        dsSize.unionBySize(1, 2);
        dsSize.unionBySize(2, 3);
        dsSize.unionBySize(4, 5);
        dsSize.unionBySize(6, 7);
        dsSize.unionBySize(5, 6);

        System.out.println("Component size of 1: " + dsSize.getComponentSize(1)); // Expected: 3 (1, 2, 3)
        System.out.println("Component size of 4: " + dsSize.getComponentSize(4)); // Expected: 4 (4, 5, 6, 7)
        dsSize.unionBySize(3, 7);
        System.out.println("After union(3, 7), component size of 1: " + dsSize.getComponentSize(1)); // Expected: 7
    }
}
