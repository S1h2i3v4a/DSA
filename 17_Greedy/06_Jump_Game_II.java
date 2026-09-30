/**
 * Problem Name: Jump Game II
 * Platform: LeetCode (LC 45)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) single pass through the array
 * Space Complexity: O(1) constant auxiliary space
 * 
 * Approach:
 * Greedy / BFS Level-by-Level Window Approach:
 * Maintain current window end (currEnd) and the farthest index reachable (farthest).
 * Iterate through the array (excluding the last element as we stop when currEnd reaches/passes end):
 * - Update farthest = Math.max(farthest, i + nums[i]).
 * - When i reaches currEnd, increment jump count (jumps++) and set currEnd = farthest.
 */

class Jump_Game_II {
    public int jump(int[] nums) {
        int jumps = 0;
        int currEnd = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == currEnd) {
                jumps++;
                currEnd = farthest;
            }
        }

        return jumps;
    }

    public static void main(String[] args) {
        Jump_Game_II solver = new Jump_Game_II();
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Min jumps for [2, 3, 1, 1, 4]: " + solver.jump(nums1)); // Expected: 2

        int[] nums2 = {2, 3, 0, 1, 4};
        System.out.println("Min jumps for [2, 3, 0, 1, 4]: " + solver.jump(nums2)); // Expected: 2
    }
}
