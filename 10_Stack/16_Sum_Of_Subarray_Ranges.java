/**
 * Problem Name: Sum of Subarray Ranges
 * Platform: LeetCode (2104)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) using Monotonic Stacks for Max and Min contribution.
 * Space Complexity: O(N) auxiliary space.
 * 
 * Approach:
 * Range = Maximum - Minimum across all subarrays.
 * Total Sum = (Sum of Subarray Maximums) - (Sum of Subarray Minimums).
 * Both components are evaluated in O(N) using Monotonic Stacks (PLE/NSE & PGE/NGE).
 */

import java.util.ArrayDeque;
import java.util.Deque;

class Sum_Of_Subarray_Ranges {

    public static long subArrayRanges(int[] nums) {
        return sumSubarrayMaxs(nums) - sumSubarrayMins(nums);
    }

    private static long sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
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
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (long) left[i] * right[i] * arr[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println("Sum of Subarray Ranges: " + subArrayRanges(nums));
    }
}
