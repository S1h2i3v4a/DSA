/*
QUESTION:
Given an array `arr` of size `N`, find the next greater element for each element of the array in order of their appearance.
If no greater element exists, output -1.

APPROACH:
- Use a monotonic decreasing stack traversing from right to left (`N - 1` down to `0`).
- While `stack` is not empty and `stack.peek() <= arr[i]`, pop from stack.
- If stack is empty, next greater is -1; otherwise, `stack.peek()`.
- Push `arr[i]` onto stack.

TIME COMPLEXITY: O(N) since each element is pushed and popped at most once.
SPACE COMPLEXITY: O(N) for the stack.
*/

import java.util.Stack;

class Solution {
    public static int[] nextGreaterElement(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return result;
    }
}
