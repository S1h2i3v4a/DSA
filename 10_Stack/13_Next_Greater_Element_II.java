/**
 * Problem Name: Next Greater Element II (Circular Array)
 * Platform: LeetCode (503)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is array length (2*N virtual iterations).
 * Space Complexity: O(N) monotonic stack & result array.
 * 
 * Approach:
 * Circular Monotonic Stack.
 * Virtual array doubling by iterating `i` from `2*N - 1` down to `0`.
 * Use `i % N` to index into circular array.
 * 1. Pop stack while `stack.peek() <= nums[i % N]`.
 * 2. For indices `i < N`, `result[i] = stack.isEmpty() ? -1 : stack.peek()`.
 * 3. Push `nums[i % N]` onto stack.
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Next_Greater_Element_II {

    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }

            if (i < n) {
                result[i] = stack.isEmpty() ? -1 : stack.peek();
            }

            stack.push(nums[i % n]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
        int[] nge = nextGreaterElements(nums);
        System.out.println("Input Circular Array: " + Arrays.toString(nums));
        System.out.println("Next Greater Elements II: " + Arrays.toString(nge));
    }
}
