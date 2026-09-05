/*
 * Problem Name: Koko Eating Bananas
 * Platform: LeetCode (875) / GeeksforGeeks
 * Difficulty: Medium
 * Time Complexity: O(N * log(max(piles)))
 * Space Complexity: O(1)
 */

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1, high = 0;
        for (int pile : piles) high = Math.max(high, pile);
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            long hours = calculateHours(piles, mid);
            if (hours <= h) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private long calculateHours(int[] piles, int speed) {
        long total = 0;
        for (int pile : piles) {
            total += (pile + speed - 1) / speed;
        }
        return total;
    }
}
