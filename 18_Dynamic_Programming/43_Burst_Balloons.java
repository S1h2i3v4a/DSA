/**
 * Problem Name: Burst Balloons
 * Platform: LeetCode (LC 312) / GeeksforGeeks
 * Difficulty: Hard
 *
 * Time Complexity: O(N^3) where N is array length
 * Space Complexity: O(N^2) for DP table
 *
 * Approach:
 *   - If you burst balloon i, you get nums[i - 1] * nums[i] * nums[i + 1] coins.
 *   - Crucial Reversal Insight:
 *     Instead of thinking which balloon to burst FIRST (which dynamically changes neighbors),
 *     think which balloon is burst LAST in the subarray [i, j]!
 *   - If balloon k is the LAST one to burst in [i, j]:
 *     Its neighbors are guaranteed to be nums[i - 1] and nums[j + 1] because all other
 *     balloons in [i, j] are already gone!
 *   - Coins gained:
 *     nums[i - 1] * nums[k] * nums[j + 1] + dp(i, k - 1) + dp(k + 1, j).
 *   - We pad nums with 1 at both ends: [1, ...nums..., 1].
 */

class BurstBalloons {

    public static int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        System.arraycopy(nums, 0, arr, 1, n);

        int[][] dp = new int[n + 2][n + 2];

        for (int i = n; i >= 1; i--) {
            for (int j = i; j <= n; j++) {
                int maxCoins = 0;
                for (int k = i; k <= j; k++) {
                    int coins = arr[i - 1] * arr[k] * arr[j + 1]
                              + dp[i][k - 1]
                              + dp[k + 1][j];
                    maxCoins = Math.max(maxCoins, coins);
                }
                dp[i][j] = maxCoins;
            }
        }

        return dp[1][n];
    }

    public static void main(String[] args) {
        // Test Case 1: nums = [3,1,5,8] -> Expected: 167
        int[] nums1 = {3, 1, 5, 8};
        System.out.println("Test 1: " + maxCoins(nums1)); // 167

        // Test Case 2: nums = [1,5] -> Expected: 10
        int[] nums2 = {1, 5};
        System.out.println("Test 2: " + maxCoins(nums2)); // 10
    }
}
