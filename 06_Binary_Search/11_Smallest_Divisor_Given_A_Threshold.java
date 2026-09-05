/*
 * Problem Name: Find the Smallest Divisor Given a Threshold
 * Platform: LeetCode (1283) / GeeksforGeeks
 * Difficulty: Medium
 * Time Complexity: O(N * log(max(nums)))
 * Space Complexity: O(1)
 */

class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1, high = 0;
        for (int num : nums) high = Math.max(high, num);
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (computeSum(nums, mid) <= threshold) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private int computeSum(int[] nums, int divisor) {
        int sum = 0;
        for (int num : nums) {
            sum += (num + divisor - 1) / divisor;
        }
        return sum;
    }
}
