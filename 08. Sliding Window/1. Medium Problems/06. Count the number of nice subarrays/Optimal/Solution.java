/*
QUESTION:
Return the number of nice sub-arrays with `k` odd numbers in O(1) auxiliary space.

APPROACH:
- Number of subarrays with exactly `k` odd numbers = `atMost(nums, k) - atMost(nums, k - 1)`.
- `atMost(nums, k)` calculates number of subarrays with at most `k` odd numbers using sliding window:
  - Expand `right`: if `nums[right] % 2 != 0`, increment `odds`.
  - While `odds > k`, if `nums[left] % 2 != 0`, decrement `odds`, `left++`.
  - Count += `right - left + 1`.

TIME COMPLEXITY: O(N) two linear passes.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    private int atMost(int[] nums, int k) {
        if (k < 0) return 0;
        int left = 0, odds = 0, count = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] % 2 != 0) odds++;
            while (odds > k) {
                if (nums[left] % 2 != 0) odds--;
                left++;
            }
            count += (right - left + 1);
        }
        return count;
    }

    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }
}
