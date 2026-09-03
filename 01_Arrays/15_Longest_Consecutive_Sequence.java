/*
 * Problem Name: Longest Consecutive Sequence
 * Platform: LeetCode (128)
 * Difficulty: Medium
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int maxStreak = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int currentNum = num, currentStreak = 1;
                while (set.contains(currentNum + 1)) {
                    currentNum++; currentStreak++;
                }
                maxStreak = Math.max(maxStreak, currentStreak);
            }
        }
        return maxStreak;
    }
}
