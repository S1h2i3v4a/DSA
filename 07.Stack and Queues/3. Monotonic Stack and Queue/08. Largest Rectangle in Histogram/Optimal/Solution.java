/*
QUESTION:
Given an array of integers `heights`, return the area of the largest rectangle in the histogram.

APPROACH:
- Single-pass monotonic stack storing indices of increasing heights.
- When `heights[i]` is smaller than the height of the stack top, pop the element.
- The popped bar's height is `heights[popped]`.
- Its right boundary is `i` (current element).
- Its left boundary is the new stack top (or -1 if stack is empty).
- Width is `i - stack.peek() - 1`.
- Compute area and update maximum.

TIME COMPLEXITY: O(N) single pass; each index pushed and popped at most once.
SPACE COMPLEXITY: O(N) for monotonic stack.
*/

import java.util.Stack;

class Solution {
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && heights[stack.peek()] >= currentHeight) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
