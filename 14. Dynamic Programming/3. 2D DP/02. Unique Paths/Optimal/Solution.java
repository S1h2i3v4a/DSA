/*
QUESTION:
Find total number of unique paths from (0,0) to (m-1, n-1) on an m x n grid.

APPROACH:
- Combinatorics:
- Total steps needed = (m - 1) down moves + (n - 1) right moves = (m + n - 2).
- Any choice of (m - 1) down moves uniquely determines a path: C(m + n - 2, m - 1).

TIME COMPLEXITY: O(min(M, N)).
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public int uniquePaths(int m, int n) {
        int N = m + n - 2;
        int r = Math.min(m - 1, n - 1);
        double res = 1;

        for (int i = 1; i <= r; i++) {
            res = res * (N - r + i) / i;
        }

        return (int) Math.round(res);
    }
}
