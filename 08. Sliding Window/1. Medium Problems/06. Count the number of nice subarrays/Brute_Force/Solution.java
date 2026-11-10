/*
QUESTION:
Given an array of integers `nums` and an integer `k`. A continuous subarray is called nice if there are `k` odd numbers on it.
Return the number of nice sub-arrays.

APPROACH:
- Inspect all subarrays `nums[i...j]` using nested loops.
- Track count of odd numbers.
- If odd count == k, increment result.

TIME COMPLEXITY: O(N^2) checking all subarrays.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int oddCount = 0;
            for (int j = i; j < n; j++) {
                if (nums[j] % 2 != 0) oddCount++;
                if (oddCount == k) ans++;
                else if (oddCount > k) break;
            }
        }
        return ans;
    }
}
