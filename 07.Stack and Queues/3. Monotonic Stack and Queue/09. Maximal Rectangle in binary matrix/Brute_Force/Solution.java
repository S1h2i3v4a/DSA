/*
QUESTION:
Given a rows x cols binary matrix filled with 0's and 1's,
find the largest rectangle containing only 1's and return its area.

APPROACH:
- Inspect every possible rectangle defined by top-left `(r1, c1)` and bottom-right `(r2, c2)`.
- Check if all cells inside this submatrix contain '1'.
- If so, update `maxArea = max(maxArea, (r2 - r1 + 1) * (c2 - c1 + 1))`.

TIME COMPLEXITY: O((R * C)^3) checking all submatrices.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    private static boolean isAllOnes(char[][] matrix, int r1, int c1, int r2, int c2) {
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                if (matrix[i][j] != '1') return false;
            }
        }
        return true;
    }

    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxArea = 0;

        for (int r1 = 0; r1 < rows; r1++) {
            for (int c1 = 0; c1 < cols; c1++) {
                for (int r2 = r1; r2 < rows; r2++) {
                    for (int c2 = c1; c2 < cols; c2++) {
                        if (isAllOnes(matrix, r1, c1, r2, c2)) {
                            int area = (r2 - r1 + 1) * (c2 - c1 + 1);
                            maxArea = Math.max(maxArea, area);
                        }
                    }
                }
            }
        }
        return maxArea;
    }
}
