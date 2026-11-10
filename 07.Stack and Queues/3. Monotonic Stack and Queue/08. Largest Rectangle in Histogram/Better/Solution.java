/*
QUESTION:
Given an array of integers `heights`, return the area of the largest rectangle in the histogram.

APPROACH:
- Precalculate two arrays using monotonic stacks:
  - `pse[i]`: index of Previous Smaller Element.
  - `nse[i]`: index of Next Smaller Element.
- Width for each bar `i` is `nse[i] - pse[i] - 1`.
- Max area is `max(heights[i] * width)`.

TIME COMPLEXITY: O(N) using two linear passes with stack.
SPACE COMPLEXITY: O(N) for `pse`, `nse`, and stack.
*/

import java.util.Stack;

class Solution {
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] pse = new int[n];
        int[] nse = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Previous Smaller Element indices
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            pse[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();

        // Next Smaller Element indices
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            nse[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = nse[i] - pse[i] - 1;
            maxArea = Math.max(maxArea, heights[i] * width);
        }
        return maxArea;
    }
}
