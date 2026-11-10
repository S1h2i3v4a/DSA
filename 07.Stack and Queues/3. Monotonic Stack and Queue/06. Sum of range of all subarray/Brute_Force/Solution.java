/*
QUESTION:
You are given an integer array `nums`. The range of a subarray is the difference between the largest and smallest element in the subarray.
Return the sum of all subarray ranges of `nums`.

APPROACH:
- Use two nested loops to generate all subarrays `nums[i...j]`.
- Track running minimum and maximum.
- Add `(max - min)` to the total answer.

TIME COMPLEXITY: O(N^2) inspecting all subarrays.
SPACE COMPLEXITY: O(1) constant space.
*/

class Solution {
    public static long subArrayRanges(int[] nums) {
        int n = nums.length;
        long totalRange = 0;

        for (int i = 0; i < n; i++) {
            int minVal = nums[i];
            int maxVal = nums[i];
            for (int j = i; j < n; j++) {
                minVal = Math.min(minVal, nums[j]);
                maxVal = Math.max(maxVal, nums[j]);
                totalRange += (maxVal - minVal);
            }
        }
        return totalRange;
    }
}
