/*
 * Problem Name: Longest Subarray with Sum K
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */

import java.util.HashMap;

class Solution {
    public int lenOfLongestSubarr(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxLen = 0, sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum == k) maxLen = i + 1;
            if (map.containsKey(sum - k)) maxLen = Math.max(maxLen, i - map.get(sum - k));
            if (!map.containsKey(sum)) map.put(sum, i);
        }
        return maxLen;
    }
}
