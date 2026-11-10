/*
QUESTION:
A robot is at top-left corner (0,0) of an m x n grid. Move to bottom-right corner (m-1, n-1) moving only right or down.
Find total number of unique paths.

APPROACH:
- Pure recursion: paths(r, c) = paths(r-1, c) + paths(r, c-1).
- Base case: at (0, 0), return 1. Out of bounds returns 0.

TIME COMPLEXITY: O(2^(M+N)) exponential recursion tree.
SPACE COMPLEXITY: O(M + N) recursion stack.
*/

class Solution {
    public int uniquePaths(int m, int n) {
        return solve(m - 1, n - 1);
    }

    private int solve(int r, int c) {
        if (r == 0 && c == 0) return 1;
        if (r < 0 || c < 0) return 0;
        return solve(r - 1, c) + solve(r, c - 1);
    }
}
