/*
QUESTION:
Given a binary array `nums` and an integer `goal`, return the number of non-empty subarrays with a sum `goal`.

APPROACH:
- Iterate through all subarrays `nums[i...j]` using nested loops.
- Calculate running sum.
- If `sum == goal`, increment count. If `sum > goal`, break (since array contains non-negative elements 0 and 1).

TIME COMPLEXITY: O(N^2) inspecting all subarrays.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public static int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == goal) count++;
                else if (sum > goal) break;
            }
        }
        return count;
    }
}
