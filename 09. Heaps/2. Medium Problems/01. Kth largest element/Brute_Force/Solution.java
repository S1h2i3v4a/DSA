/*
QUESTION:
Given an integer array `nums` and an integer `k`, return the `k`th largest element in the array.

APPROACH:
- Sort the array in ascending order.
- The `k`th largest element is located at index `nums.length - k`.

TIME COMPLEXITY: O(N log N) for sorting.
SPACE COMPLEXITY: O(1) if sorting in-place.
*/

import java.util.Arrays;

class Solution {
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
