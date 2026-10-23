/*
QUESTION:
Given an array of integers nums and a sliding window of size k, we need to find the maximum element in each sliding window as it moves from left to right.

APPROACH:
To solve this problem, we can use a deque (double-ended queue) to store the indices of elements in the current sliding window.
1. We iterate through the first k elements and keep track of the maximum element by maintaining the following property:
   - The deque stores the indices of elements in non-increasing order of their corresponding values in the nums array.
   - If the current element is greater than or equal to the element at the back of the deque, we remove elements from the back until this condition is satisfied.
   - This ensures that the front element of the deque is always the maximum element in the sliding window.
2. After processing the first k elements, we start from the (k+1)th element and continue the following steps:
   - If the index at the front of the deque is less than or equal to i-k, it means the maximum element in the previous sliding window is no longer valid. So, we remove it from the deque.
   - Similar to step 1, we remove elements from the back of the deque until the current element is greater than or equal to the element at the back.
   - Add the index of the current element to the deque.
   - Add the maximum element (front of the deque) to the result for the current sliding window.
3. Repeat step 2 until we process all elements in the nums array.

Example:
Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
