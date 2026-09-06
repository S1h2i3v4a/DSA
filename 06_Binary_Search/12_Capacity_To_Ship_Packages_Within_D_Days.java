/*
 * Problem Name: Capacity To Ship Packages Within D Days
 * Platform: LeetCode (1011) / GeeksforGeeks
 * Difficulty: Medium
 * Time Complexity: O(N * log(sum - max))
 * Space Complexity: O(1)
 */

class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int low = 0, high = 0;
        for (int w : weights) {
            low = Math.max(low, w);
            high += w;
        }
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (countDays(weights, mid) <= days) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private int countDays(int[] weights, int cap) {
        int days = 1, currentLoad = 0;
        for (int w : weights) {
            if (currentLoad + w > cap) {
                days++;
                currentLoad = w;
            } else {
                currentLoad += w;
            }
        }
        return days;
    }
}
