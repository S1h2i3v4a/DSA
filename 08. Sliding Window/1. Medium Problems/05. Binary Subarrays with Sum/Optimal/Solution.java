/*
QUESTION:
Given a binary array `nums` and an integer `goal`, return the number of non-empty subarrays with sum `goal` in O(1) space.

APPROACH:
- Number of subarrays with sum exactly `goal` = `atMost(nums, goal) - atMost(nums, goal - 1)`.
- `atMost(nums, k)`: sliding window finding number of subarrays with sum <= k.
  - Expand `right`, add `nums[right]` to sum.
  - While `sum > k`, subtract `nums[left++]`.
  - Add `right - left + 1` to total.

TIME COMPLEXITY: O(N) two sliding window passes.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    private static int atMost(int[] nums, int goal) {
        if (goal < 0) return 0;
        int left = 0, sum = 0, count = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum > goal) {
                sum -= nums[left++];
            }
            count += (right - left + 1);
        }
        return count;
    }

    public static int numSubarraysWithSum(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }
}
