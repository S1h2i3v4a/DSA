/**
 * Problem Name: Largest Rectangle in Histogram
 * Platform: LeetCode (84)
 * Difficulty: Hard
 * 
 * Time Complexity: O(N) single pass using Monotonic Increasing Stack.
 * Space Complexity: O(N) stack size.
 * 
 * Approach:
 * Monotonic Increasing Stack.
 * Iterate index `i` from `0` to `N` (with virtual height 0 at index `N`):
 * While `stack.peek()` height > `current height`:
 * - `height = heights[stack.pop()]`
 * - `width = stack.isEmpty() ? i : (i - stack.peek() - 1)`
 * - `maxArea = max(maxArea, height * width)`
 * Push `i` onto stack.
 */

import java.util.ArrayDeque;
import java.util.Deque;

class Largest_Rectangle_In_Histogram {

    public static int largestRectangleArea(int[] heights) {
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
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println("Largest Rectangle Area: " + largestRectangleArea(heights));
    }
}
