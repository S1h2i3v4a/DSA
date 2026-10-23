/*
QUESTION:
Given a binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

APPROACH:
To solve this problem, we can use a variation of the Largest Rectangle in Histogram problem.
1. First, we will calculate the heights of the histogram for each row in the matrix.
   - If the current element is '1', we increment the height of the histogram at that column by 1.
   - If the current element is '0', we reset the height of the histogram at that column to 0.
2. For each row, we calculate the largest rectangle area using the heights calculated in step 1.
   - We use the largestRectangleArea function, which calculates the largest rectangle area in a histogram.
   - The histogram heights are the heights of the current row up to that column.
3. Finally, we return the maximum area obtained from step 2.

Example:
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
