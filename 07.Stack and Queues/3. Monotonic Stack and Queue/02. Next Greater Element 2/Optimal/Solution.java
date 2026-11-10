/*
QUESTION:
Given a circular integer array `nums`, return the next greater number for every element in `nums`.
Search circularly to find its next greater number. If it doesn't exist, return -1.

APPROACH:
- Simulate circular traversal by iterating from `2 * N - 1` down to `0`.
- Maintain a monotonic decreasing stack of values.
- Pop all elements `<= nums[i % n]`.
- For `i < n`, assign `result[i] = stack.isEmpty() ? -1 : stack.peek()`.
- Push `nums[i % n]` onto stack.

TIME COMPLEXITY: O(N) as each element is pushed/popped at most twice across 2N iterations.
SPACE COMPLEXITY: O(N) for monotonic stack.
*/

import java.util.Stack;

class Solution {
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 2 * n - 1; i >= 0; i--) {
            int current = nums[i % n];
            while (!stack.isEmpty() && stack.peek() <= current) {
                stack.pop();
            }
            if (i < n) {
                result[i] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(current);
        }
        return result;
    }
}
