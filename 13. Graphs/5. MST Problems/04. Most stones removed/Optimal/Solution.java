/*
QUESTION:
On a 2D plane, we place `n` stones with some integer coordinate points.
A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
Return the largest possible number of stones that can be removed.

APPROACH:
- Connect every stone's row `r` to its column `c + 10001` using DSU.
- Each connected component of stones can be reduced to 1 stone.
- Stones that can be removed = `totalStones - numberOfComponents`.

TIME COMPLEXITY: O(N * alpha(N)).
SPACE COMPLEXITY: O(maxCoordinate).
*/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    private static class DisjointSet {
        Map<Integer, Integer> parent = new HashMap<>();

        int find(int i) {
            if (!parent.containsKey(i)) parent.put(i, i);
            if (parent.get(i) == i) return i;
            int root = find(parent.get(i));
            parent.put(i, root);
            return root;
        }

        void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU != rootV) {
                parent.put(rootU, rootV);
            }
        }
    }

    public int removeStones(int[][] stones) {
        DisjointSet ds = new DisjointSet();

        for (int[] stone : stones) {
            int row = stone[0];
            int col = stone[1] + 10001;
            ds.union(row, col);
        }

        Set<Integer> uniqueRoots = new HashSet<>();
        for (int[] stone : stones) {
            uniqueRoots.add(ds.find(stone[0]));
        }

        return stones.length - uniqueRoots.size();
    }
}
