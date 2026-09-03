/*
 * Problem Name: Majority Element
 * Platform: LeetCode (169) / GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */

class Solution {
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
