/*
QUESTION:
You are given an array of integers `nums`, there is a sliding window of size `k` which is moving from the very left of the array to the very right.
Return the max sliding window.

APPROACH:
- Use a Monotonic Deque storing indices in decreasing order of their corresponding values.
- For each index `i`:
  - Remove indices outside the window `[i - k + 1, i]` from the front (`dq.peekFirst() <= i - k`).
  - Maintain decreasing order by removing indices from the back while `nums[dq.peekLast()] <= nums[i]`.
  - Add index `i` to back.
  - Once `i >= k - 1`, `nums[dq.peekFirst()]` is the maximum for the window.

TIME COMPLEXITY: O(N) each element is added and removed from deque at most once.
SPACE COMPLEXITY: O(K) for monotonic deque storing window indices.
*/

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k == 0) return new int[0];
        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }
            dq.offerLast(i);

            if (i >= k - 1) {
                result[i - k + 1] = nums[dq.peekFirst()];
            }
        }
        return result;
    }
}
