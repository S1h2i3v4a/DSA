/*
QUESTION:
Given an m x n binary matrix filled with 0s and 1s, find largest square containing only 1s and return its area.

APPROACH:
- For each cell (r, c) with '1', expand square size k = 1, 2, ... as long as all cells in square are '1'.
- Return max(size * size).

TIME COMPLEXITY: O((M * N)^2).
SPACE COMPLEXITY: O(1).
*/

class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxSide = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    int side = 1;
                    boolean flag = true;
                    while (i + side < m && j + side < n && flag) {
                        for (int k = j; k <= j + side; k++) {
                            if (matrix[i + side][k] == '0') { flag = false; break; }
                        }
                        for (int k = i; k <= i + side; k++) {
                            if (matrix[k][j + side] == '0') { flag = false; break; }
                        }
                        if (flag) side++;
                    }
                    maxSide = Math.max(maxSide, side);
                }
            }
        }

        return maxSide * maxSide;
    }
}
