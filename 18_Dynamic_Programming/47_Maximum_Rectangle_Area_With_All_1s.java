/**
 * Problem Name: Maximal Rectangle / Maximum Rectangle Area with All 1's
 * Platform: LeetCode (LC 85) / GeeksforGeeks
 * Difficulty: Hard
 *
 * Time Complexity: O(R * C) where R is rows and C is columns
 * Space Complexity: O(C) for height histogram array and monotonic stack
 *
 * Approach:
 *   - Find the largest rectangle containing only 1's in a binary matrix.
 *   - Reduction to Largest Rectangle in Histogram (Monotonic Stack DP):
 *     1. Maintain an array `heights` of size C.
 *     2. For each row:
 *        - Update height of column c:
 *          if matrix[r][c] == '1': heights[c]++
 *          else: heights[c] = 0 (reset consecutive vertical height)
 *        - Compute the maximum rectangle in this histogram row using a monotonic stack.
 *     3. Track the global maximum area over all rows.
 */

import java.util.Stack;

class MaximumRectangleAreaWithAll1s {

    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int r = matrix.length;
        int c = matrix[0].length;

        int[] heights = new int[c];
        int maxArea = 0;

        for (int i = 0; i < r; i++) {
            // Update histogram heights
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == '1') heights[j]++;
                else heights[j] = 0;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    // Monotonic stack implementation for largest rectangle in histogram
    private static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        // Test Case 1:
        // matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
        // Expected: 6
        char[][] matrix1 = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        System.out.println("Test 1: " + maximalRectangle(matrix1)); // 6

        // Test Case 2:
        // matrix = [["0"]] -> Expected: 0
        char[][] matrix2 = {{'0'}};
        System.out.println("Test 2: " + maximalRectangle(matrix2)); // 0
    }
}
