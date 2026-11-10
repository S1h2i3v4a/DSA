/*
QUESTION:
Given an array of integers `arr`, find the sum of `min(b)`, where `b` ranges over every (contiguous) subarray of `arr`.
Return the answer modulo 10^9 + 7.

APPROACH:
- Each element `arr[i]` acts as the minimum for subarrays spanning from its Previous Less Element (strictly less) to Next Less or Equal Element.
- Let `left[i]` = number of elements to the left where `arr[k] > arr[i]`.
- Let `right[i]` = number of elements to the right where `arr[k] >= arr[i]`.
- Contribution of `arr[i]` = `(left[i] * right[i] * arr[i]) % MOD`.
- Compute `left` and `right` spans using monotonic stacks in O(N).

TIME COMPLEXITY: O(N) using monotonic stacks.
SPACE COMPLEXITY: O(N) for stack and auxiliary span arrays.
*/

import java.util.Stack;

class Solution {
    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        long MOD = 1000000007L;
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Previous Smaller Element (strictly less)
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? (i + 1) : (i - stack.peek());
            stack.push(i);
        }

        stack.clear();

        // Next Smaller Element (less or equal to avoid double counting)
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? (n - i) : (stack.peek() - i);
            stack.push(i);
        }

        long total = 0;
        for (int i = 0; i < n; i++) {
            long count = ((long) left[i] * right[i]) % MOD;
            total = (total + count * arr[i]) % MOD;
        }
        return (int) total;
    }
}
