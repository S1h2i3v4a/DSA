/*
QUESTION:
Given an array 'nums' and an integer 'target', the task is to count 
the number of ways to make the sum of elements of one subset equal 
to 'target'.

Example:
Input: nums = [1, 1, 1, 1, 1], target = 3
Output: 5
Explanation: There are 5 ways to achieve the target sum: 
{1, 1, 1}, {1, 2}, {2, 1}, {1, 2}, {3}

APPROACH:
- The problem can be reduced to finding the count of subsets with a 
  given sum.
- Initialize dp[i][j] as the number of subsets with sum 'j' using 
  the first 'i' elements.
- Base cases:
  - dp[0][0] = 1 since an empty subset has a sum of 0.
  - dp[i][0] = 1 since there's always an empty subset.
- For each element 'nums[i]' and each possible sum 'j' from 1 to 'target':
  - dp[i][j] = dp[i-1][j] (not taking the current element)
  - if 'nums[i]' is less than or equal to 'j', then dp[i][j] += dp[i-1][j-nums[i]] (taking the current element)
- The required answer will be dp[n][target] where 'n' is the number 
  of elements in the array.

COMPLEXITY ANALYSIS:
- The time complexity of the solution is O(n * target), where 'n' 
  is the number of elements in the array.
- The space complexity is O(n * target) for the dp array.

CODE:
*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
class TargetSum {

    public static int findTargetSumWays(int[] nums, int target) {
        int totalSum = 0;
        for (int x : nums) totalSum += x;

        // Validation checks
        if (totalSum < Math.abs(target) || (totalSum + target) % 2 != 0) {
            return 0;
        }

        int s1 = (totalSum + target) / 2;
        return countSubsetsWithSumK(nums, s1);
    }

    private static int countSubsetsWithSumK(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[target + 1];

        // Base case at index 0
        if (nums[0] == 0) {
            dp[0] = 2; // +0 or -0
        } else {
            dp[0] = 1;
            if (nums[0] <= target) {
                dp[nums[0]] = 1;
            }
        }

        for (int i = 1; i < n; i++) {
            int[] next = new int[target + 1];
            for (int t = 0; t <= target; t++) {
                int notPick = dp[t];
                int pick = 0;
                if (nums[i] <= t) {
                    pick = dp[t - nums[i]];
                }
                next[t] = notPick + pick;
            }
            dp = next;
        }

        return dp[target];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // nums = [1, 1, 1, 1, 1], target = 3 -> Expected: 5
        // totalSum = 5, s1 = (5 + 3) / 2 = 4. Subsets of size 4 ones: C(5, 4) = 5
        int[] nums1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        System.out.println("Test 1 (nums=[1,1,1,1,1], target=3): " + findTargetSumWays(nums1, target1)); // 5

        // Test Case 2:
        // nums = [1], target = 1 -> Expected: 1 (+1)
        int[] nums2 = {1};
        int target2 = 1;
        System.out.println("Test 2 (nums=[1], target=1): " + findTargetSumWays(nums2, target2)); // 1

        // Test Case 3:
        // nums = [1], target = 2 -> Expected: 0
        int[] nums3 = {1};
        int target3 = 2;
        System.out.println("Test 3 (nums=[1], target=2): " + findTargetSumWays(nums3, target3)); // 0
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
