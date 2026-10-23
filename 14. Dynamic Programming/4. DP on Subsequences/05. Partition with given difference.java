/*
QUESTION:
Given an array arr, partition it into two subsets(possibly empty) 
such that their union is the original array. Let the sum of the 
elements of these two subsets be S1 and S2. 

Given a difference d, count the number of partitions in which S1 is 
greater than or equal to S2 and the difference S1 and S2 is equal to d. 
Since the answer may be large, return it modulo 10^9 + 7.

Example 1:
Input:
n = 4, d = 3
arr[] = {5, 2, 6, 4}
Output: 1
Explanation:
There is only one possible partition of this array. 
Partition: {6, 4}, {5, 2}. The subset difference between subset sums is: (6 + 4) - (5 + 2) = 3.

APPROACH:
- We'll use a similar approach as used in the subset sum count problem.
- Initialize dp[i][j] as the number of subsets with sum 'j' using the first 'i' elements.
- Base cases:
  - dp[0][0] = 1 since an empty subset has a sum of 0.
  - dp[i][0] = 1 since there's always an empty subset.
- For each element 'arr[i]' and each possible sum 'j' from 1 to 'sum':
  - dp[i][j] = dp[i-1][j] (not taking the current element)
  - if 'arr[i]' is less than or equal to 'j', then dp[i][j] += dp[i-1][j-arr[i]] (taking the current element)
- Initialize 'ans' as 0.
- For each possible subset sum 'i':
  - Check if the difference between (i - (sum - i)) is equal to 'd'.
  - If it is, add the result of fmemo(n, i, arr, dp) to 'ans'.
- Return 'ans' % mod which represents the answer.

COMPLEXITY ANALYSIS:
- The time complexity of the solution is O(n * sum), where 'n' is the number of elements and 'sum' is the required sum.
- The space complexity is O(n * sum) for the dp array.

CODE:
*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
class CountPartitionsWithGivenDifference {

    private static final int MOD = 1000000007;

    public static int countPartitions(int n, int d, int[] arr) {
        int totalSum = 0;
        for (int x : arr) totalSum += x;

        // Validation checks
        if (totalSum - d < 0 || (totalSum + d) % 2 != 0) {
            return 0;
        }

        int target = (totalSum + d) / 2;
        return countSubsetsWithSumK(arr, target);
    }

    // Space-Optimized DP helper for counting subsets with sum K
    private static int countSubsetsWithSumK(int[] arr, int target) {
        int[] dp = new int[target + 1];

        if (arr[0] == 0) {
            dp[0] = 2; // Include or exclude 0
        } else {
            dp[0] = 1;
            if (arr[0] <= target) {
                dp[arr[0]] = 1;
            }
        }

        for (int i = 1; i < arr.length; i++) {
            int[] next = new int[target + 1];
            for (int t = 0; t <= target; t++) {
                int notPick = dp[t];
                int pick = 0;
                if (arr[i] <= t) {
                    pick = dp[t - arr[i]];
                }
                next[t] = (notPick + pick) % MOD;
            }
            dp = next;
        }

        return dp[target];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // n = 4, d = 3, arr = [5, 2, 6, 4]
        // totalSum = 17, target = (17 + 3) / 2 = 10.
        // Partitions: {6, 4} and {5, 2} -> difference = 10 - 7 = 3. Count = 1
        int[] arr1 = {5, 2, 6, 4};
        int d1 = 3;
        System.out.println("Test 1 (arr=[5,2,6,4], d=3): " + countPartitions(arr1.length, d1, arr1)); // 1

        // Test Case 2:
        // n = 5, d = 1, arr = [1, 1, 1, 1, 1]
        // totalSum = 5, target = (5 + 1) / 2 = 3.
        // Number of subsets of size 3 from 5 ones: C(5, 3) = 10
        int[] arr2 = {1, 1, 1, 1, 1};
        int d2 = 1;
        System.out.println("Test 2 (arr=[1,1,1,1,1], d=1): " + countPartitions(arr2.length, d2, arr2)); // 10
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
