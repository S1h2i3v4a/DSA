/**
 * Problem Name: Binary Subarrays With Sum
 * Platform: LeetCode (930)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) single pass.
 * Space Complexity: O(1) auxiliary space.
 * 
 * Approach:
 * At-Most Sliding Window Strategy.
 * Exact Goal Subarrays Count = `numSubarraysWithSumAtMost(goal) - numSubarraysWithSumAtMost(goal - 1)`.
 * For `numSubarraysWithSumAtMost(K)`:
 * - If K < 0 return 0.
 * - Expand `right`, add `nums[right]` to sum.
 * - While `sum > K`, shrink `left` (`sum -= nums[left++]`).
 * - Add `(right - left + 1)` to count.
 */

class Binary_Subarrays_With_Sum {

    public static int numSubarraysWithSum(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    private static int atMost(int[] nums, int k) {
        if (k < 0) return 0;
        int left = 0;
        int sum = 0;
        int count = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum > k) {
                sum -= nums[left];
                left++;
            }

            count += (right - left + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 0, 1};
        int goal = 2;
        System.out.println("Binary Subarrays with Sum " + goal + ": " + numSubarraysWithSum(nums, goal));
    }
}
