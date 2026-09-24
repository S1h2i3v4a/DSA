/**
 * Problem Name: Maximal Rectangle
 * Platform: LeetCode (85)
 * Difficulty: Hard
 * 
 * Time Complexity: O(R * C) where R is row count and C is column count.
 * Space Complexity: O(C) for height histogram array.
 * 
 * Approach:
 * Row-by-Row Histogram Reduction.
 * Maintain a 1D `heights` array of size `C`.
 * Iterate through each row in matrix:
 * - For each column `c`: if `matrix[r][c] == '1'`, `heights[c] += 1`, else `heights[c] = 0`.
 * - Pass `heights` to `largestRectangleArea(heights)` to get max area for current row.
 * Track max area across all rows.
 */

import java.util.ArrayDeque;
import java.util.Deque;

class Maximal_Rectangle {

    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == '1') {
                    heights[c]++;
                } else {
                    heights[c] = 0;
                }
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    private static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && heights[stack.peek()] >= currentHeight) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        char[][] matrix = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        System.out.println("Maximal Rectangle Area: " + maximalRectangle(matrix));
    }
}
