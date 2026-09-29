/**
 * Problem Name: Jump Game
 * Platform: LeetCode (LC 55)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) single pass through the array
 * Space Complexity: O(1) constant extra space
 * 
 * Approach:
 * Maintain the maximum reachable index maxReach initialized to 0.
 * Iterate through the array:
 * - If current index i > maxReach, return false (index is unreachable).
 * - Update maxReach = Math.max(maxReach, i + nums[i]).
 * - If maxReach >= nums.length - 1, return true.
 */

class Jump_Game {
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i > maxReach) {
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= n - 1) {
                return true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Jump_Game solver = new Jump_Game();
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Can jump [2, 3, 1, 1, 4]: " + solver.canJump(nums1)); // true

        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("Can jump [3, 2, 1, 0, 4]: " + solver.canJump(nums2)); // false
    }
}
