/**
 * Problem Name: Max Consecutive Ones III
 * Platform: LeetCode (1004)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) single pass.
 * Space Complexity: O(1) auxiliary space.
 * 
 * Approach:
 * Expanding/Shrinking Sliding Window.
 * Track count of zeros `zeroCount` in window `[left, right]`.
 * Whenever `zeroCount > k`, shrink window from the left by incrementing `left` pointer and adjusting `zeroCount`.
 * Maximum consecutive ones is `max(maxLength, right - left + 1)`.
 */

class Max_Consecutive_Ones_III {

    public static int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeroCount = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;
        System.out.println("Max Consecutive Ones III: " + longestOnes(nums, k));
    }
}
