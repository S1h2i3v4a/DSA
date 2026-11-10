/*
QUESTION:
Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

APPROACH:
- Test every possible square submatrix with top-left (i, j) and size k:
  Check if all elements in k x k submatrix are 1.

TIME COMPLEXITY: O(M * N * min(M, N)^2).
SPACE COMPLEXITY: O(1).
*/

class Solution {
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int totalSquares = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int maxK = Math.min(m - i, n - j);
                for (int k = 1; k <= maxK; k++) {
                    if (isAllOnes(i, j, k, matrix)) {
                        totalSquares++;
                    } else {
                        break;
                    }
                }
            }
        }

        return totalSquares;
    }

    private boolean isAllOnes(int r, int c, int k, int[][] matrix) {
        for (int i = r; i < r + k; i++) {
            for (int j = c; j < c + k; j++) {
                if (matrix[i][j] == 0) return false;
            }
        }
        return true;
    }
}
