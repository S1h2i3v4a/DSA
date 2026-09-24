/**
 * Problem Name: Sliding Window Maximum
 * Platform: LeetCode (239)
 * Difficulty: Hard
 * 
 * Time Complexity: O(N) single pass.
 * Space Complexity: O(K) for monotonic deque storage.
 * 
 * Approach:
 * Monotonic Decreasing Deque.
 * Maintain index deque storing elements in strictly decreasing order of values.
 * 1. Remove indices out of current window `i - k + 1` from front of deque.
 * 2. Remove indices with values `<= nums[i]` from back of deque.
 * 3. Add current index `i` to back of deque.
 * 4. For `i >= k - 1`, `result[i - k + 1] = nums[deque.peekFirst()]`.
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Sliding_Window_Maximum {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // Remove indices outside the window bounds [i - k + 1, i]
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Maintain monotonic decreasing property in deque
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            // Add front of deque to result once first window is formed
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] maxWindow = maxSlidingWindow(nums, k);
        System.out.println("Sliding Window Maximums: " + Arrays.toString(maxWindow));
    }
}
