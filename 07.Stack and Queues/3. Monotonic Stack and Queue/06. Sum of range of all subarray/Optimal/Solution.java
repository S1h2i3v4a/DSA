/*
QUESTION:
You are given an integer array `nums`. The range of a subarray is the difference between the largest and smallest element in the subarray.
Return the sum of all subarray ranges of `nums`.

APPROACH:
- Sum of ranges = `Sum of subarray maximums - Sum of subarray minimums`.
- Both `sumSubarrayMax` and `sumSubarrayMin` can be computed in O(N) using monotonic stacks.
- For min: compute previous smaller and next smaller or equal spans.
- For max: compute previous greater and next greater or equal spans.

TIME COMPLEXITY: O(N) with monotonic stack passes.
SPACE COMPLEXITY: O(N) for stacks and span arrays.
*/

import java.util.Stack;

class Solution {
    private static long sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) stack.pop();
            left[i] = stack.isEmpty() ? (i + 1) : (i - stack.peek());
            stack.push(i);
        }

        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) stack.pop();
            right[i] = stack.isEmpty() ? (n - i) : (stack.peek() - i);
            stack.push(i);
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (long) left[i] * right[i] * arr[i];
        }
        return sum;
    }

    private static long sumSubarrayMaxs(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) stack.pop();
            left[i] = stack.isEmpty() ? (i + 1) : (i - stack.peek());
            stack.push(i);
        }

        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) stack.pop();
            right[i] = stack.isEmpty() ? (n - i) : (stack.peek() - i);
            stack.push(i);
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (long) left[i] * right[i] * arr[i];
        }
        return sum;
    }

    public static long subArrayRanges(int[] nums) {
        return sumSubarrayMaxs(nums) - sumSubarrayMins(nums);
    }
}
