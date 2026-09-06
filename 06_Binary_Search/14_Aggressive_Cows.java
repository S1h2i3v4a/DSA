/*
 * Problem Name: Aggressive Cows
 * Platform: GeeksforGeeks / SPOJ
 * Difficulty: Medium
 * Time Complexity: O(N log N + N * log(max_dist))
 * Space Complexity: O(1)
 */

import java.util.Arrays;

class Solution {
    public int aggressiveCows(int[] stalls, int k) {
        Arrays.sort(stalls);
        int low = 1, high = stalls[stalls.length - 1] - stalls[0];
        int ans = 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canPlace(stalls, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean canPlace(int[] stalls, int k, int dist) {
        int countCows = 1, last = stalls[0];
        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - last >= dist) {
                countCows++;
                last = stalls[i];
            }
            if (countCows >= k) return true;
        }
        return false;
    }
}
