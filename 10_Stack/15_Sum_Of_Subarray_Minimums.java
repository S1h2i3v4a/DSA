/**
 * Problem Name: Sum of Subarray Minimums
 * Platform: LeetCode (907)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) single pass with monotonic stack.
 * Space Complexity: O(N) for stack and distance arrays.
 * 
 * Approach:
 * Previous Smaller Element (PLE) & Next Smaller or Equal Element (NSE).
 * For each element `arr[i]`:
 * - `left[i]`: count of continuous subarrays ending at `i` where `arr[i]` is strictly minimum.
 * - `right[i]`: count of continuous subarrays starting at `i` where `arr[i]` is minimum (or equal).
 * Total contribution = `(arr[i] * left[i] * right[i]) % MOD`.
 */

import java.util.ArrayDeque;
import java.util.Deque;

class Sum_Of_Subarray_Minimums {

    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        long MOD = 1_000_000_007;

        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        // Calculate PLE (Previous Less Element)
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();

        // Calculate NSE (Next Less or Equal Element)
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            long count = ((long) left[i] * right[i]) % MOD;
            totalSum = (totalSum + (count * arr[i]) % MOD) % MOD;
        }

        return (int) totalSum;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4};
        System.out.println("Sum of Subarray Minimums: " + sumSubarrayMins(arr));
    }
}
