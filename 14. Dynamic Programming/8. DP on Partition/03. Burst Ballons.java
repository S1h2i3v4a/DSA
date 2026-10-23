/*
QUESTION:
You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums.
You are asked to burst all the balloons.
If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins.
If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
Return the maximum coins you can collect by bursting the balloons wisely.

Example:
Input: nums = [3, 1, 5, 8]
Output: 167
Explanation:
nums = [3, 1, 5, 8] --> [3, 5, 8] --> [3, 8] --> [8] --> []
coins = 3*1*5 + 3*5*8 + 1*3*8 + 1*8*1 = 167.

APPROACH:
- This problem can be solved using dynamic programming.
- Define a 2D DP array `dp` where dp[i][j] represents the maximum coins collected by bursting balloons between indices i and j.
- Iterate over the stick lengths from length 2 to the length of nums - 1.
- For each subarray length, iterate through all possible starting indices (i).
- Calculate the maximum coins collected by bursting balloons in the subarray between i and j, and update the dp array.
- Finally, return dp[1][n - 2], which represents the maximum coins collected by bursting all the balloons.

COMPLEXITY ANALYSIS:
- The time complexity of this approach is O(n^3), where n is the length of the nums array.
- The space complexity is O(n^2) due to the dp array.
- Overall, the algorithm runs in O(n^3) time and O(n^2) space.

CODE:
*/

import java.io.*;
import java.util.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
