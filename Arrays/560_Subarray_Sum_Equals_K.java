/*
 * Problem Name: Subarray Sum Equals K
 * Platform: LeetCode (560) / GeeksforGeeks
 * Difficulty: Medium
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */

import java.util.HashMap;

class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, count = 0;
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
