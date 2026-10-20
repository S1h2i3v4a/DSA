/**
 * Problem Name: Partition Array Into Two Arrays to Minimize Sum Difference (Minimum Sum Partition)
 * Platform: LeetCode (LC 2035) / GeeksforGeeks
 * Difficulty: Hard
 *
 * Time Complexity:
 *   - Part A (GFG DP for Non-Negative Integers): O(N * totalSum)
 *   - Part B (LeetCode 2035 Meet-in-the-Middle for 2N elements & Negatives): O(2^N * N) where N = len / 2 <= 15
 * Space Complexity:
 *   - Part A: O(totalSum) 1D boolean array
 *   - Part B: O(2^N) for storing partitioned subset sums
 *
 * Approach:
 *   - Part A (Classic DP - GFG Minimum Sum Partition):
 *     Compute Subset Sum DP table for all achievable sums from 0 to totalSum / 2.
 *     If sum S1 is achievable, the other subset will sum to totalSum - S1.
 *     Difference = (totalSum - S1) - S1 = totalSum - 2 * S1.
 *     Iterate S1 from totalSum / 2 down to 0, finding the first achievable sum to minimize difference.
 *
 *   - Part B (LeetCode 2035 - Exact N elements per partition with negative values):
 *     Length is 2 * N (where N <= 15). DP on sums is infeasible due to negative values and scale.
 *     Use Meet-in-the-Middle:
 *       1. Divide array into two halves of size N each: left and right.
 *       2. Generate all 2^N subset sums for both halves, grouped by the count of elements k (0 <= k <= N).
 *       3. Sort the right half sums for each count k.
 *       4. For each subset sum in left with k elements, we need (N - k) elements from right.
 *          Binary search in right[N - k] for the value closest to (totalSum / 2 - leftSum).
 *       5. Track the minimum absolute difference: |totalSum - 2 * (leftSum + rightSum)|.
 */

import java.util.*;

class PartitionArrayIntoTwoArraysToMinimizeSumDifference {

    // ==============================================================
    // Part A: Classic DP Approach (GeeksforGeeks - Non-Negative Array)
    // ==============================================================
    public static int minDifferenceDP(int[] arr, int n) {
        int totalSum = 0;
        for (int x : arr) totalSum += x;

        int target = totalSum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        if (arr[0] <= target) {
            dp[arr[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            boolean[] next = new boolean[target + 1];
            next[0] = true;
            for (int t = 1; t <= target; t++) {
                boolean notPick = dp[t];
                boolean pick = false;
                if (arr[i] <= t) {
                    pick = dp[t - arr[i]];
                }
                next[t] = notPick || pick;
            }
            dp = next;
        }

        // Find largest s1 <= totalSum / 2 that is achievable
        int minDiff = Integer.MAX_VALUE;
        for (int s1 = 0; s1 <= target; s1++) {
            if (dp[s1]) {
                int s2 = totalSum - s1;
                minDiff = Math.min(minDiff, Math.abs(s2 - s1));
            }
        }

        return minDiff;
    }

    // ==============================================================
    // Part B: Meet-in-the-Middle (LeetCode 2035 - Exact N elements)
    // ==============================================================
    public static int minimumDifferenceMeetInMiddle(int[] nums) {
        int totalLen = nums.length;
        int n = totalLen / 2;

        int totalSum = 0;
        for (int num : nums) totalSum += num;

        // Group subset sums by count of elements k: Map<Integer, List<Integer>>
        List<List<Integer>> leftSums = new ArrayList<>();
        List<List<Integer>> rightSums = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            leftSums.add(new ArrayList<>());
            rightSums.add(new ArrayList<>());
        }

        // Generate subset sums for left half
        generateSums(0, 0, 0, n, nums, leftSums);
        // Generate subset sums for right half
        generateSums(n, 0, 0, totalLen, nums, rightSums);

        // Sort each bucket in rightSums for binary search
        for (int k = 0; k <= n; k++) {
            Collections.sort(rightSums.get(k));
        }

        int minDiff = Integer.MAX_VALUE;

        // For each count k in left, pick (n - k) from right
        for (int k = 0; k <= n; k++) {
            List<Integer> leftList = leftSums.get(k);
            List<Integer> rightList = rightSums.get(n - k);

            for (int s1 : leftList) {
                // We want: 2 * (s1 + s2) closest to totalSum => s2 closest to (totalSum / 2.0 - s1)
                double ideal = ((double) totalSum / 2.0) - s1;
                int idx = Collections.binarySearch(rightList, (int) Math.round(ideal));

                if (idx >= 0) {
                    // Exact match found
                    int s2 = rightList.get(idx);
                    minDiff = Math.min(minDiff, Math.abs(totalSum - 2 * (s1 + s2)));
                } else {
                    int insertionPoint = -idx - 1;
                    // Check element at insertionPoint
                    if (insertionPoint < rightList.size()) {
                        int s2 = rightList.get(insertionPoint);
                        minDiff = Math.min(minDiff, Math.abs(totalSum - 2 * (s1 + s2)));
                    }
                    // Check element immediately preceding insertionPoint
                    if (insertionPoint - 1 >= 0) {
                        int s2 = rightList.get(insertionPoint - 1);
                        minDiff = Math.min(minDiff, Math.abs(totalSum - 2 * (s1 + s2)));
                    }
                }
            }
        }

        return minDiff;
    }

    private static void generateSums(int idx, int currentSum, int count, int endIdx,
                                     int[] nums, List<List<Integer>> sumGroups) {
        if (idx == endIdx) {
            sumGroups.get(count).add(currentSum);
            return;
        }

        // Choice 1: Exclude nums[idx]
        generateSums(idx + 1, currentSum, count, endIdx, nums, sumGroups);

        // Choice 2: Include nums[idx]
        generateSums(idx + 1, currentSum + nums[idx], count + 1, endIdx, nums, sumGroups);
    }

    public static void main(String[] args) {
        // Test Case 1 (GFG Non-negative array):
        // arr = [1, 6, 11, 5] -> S1 = [1, 5, 5? no: 1, 11 = 12, 6 + 5 = 11], Diff = 12 - 11 = 1
        int[] arr1 = {1, 6, 11, 5};
        System.out.println("Test 1 (GFG DP): " + minDifferenceDP(arr1, arr1.length)); // 1
        System.out.println("Test 1 (Meet-in-Middle): " + minimumDifferenceMeetInMiddle(arr1)); // 1

        // Test Case 2 (LC 2035 with negative values):
        // nums = [3, 9, 7, 3] -> Partition into size 2 each: [3, 7] sum 10, [3, 9] sum 12 -> Diff = 2
        int[] arr2 = {3, 9, 7, 3};
        System.out.println("\nTest 2 (LC 2035 [3, 9, 7, 3]): " + minimumDifferenceMeetInMiddle(arr2)); // 2

        // Test Case 3:
        // nums = [-36, 36] -> Partition size 1 each: [-36] and [36] -> Diff = |(-36) - 36| = 72
        int[] arr3 = {-36, 36};
        System.out.println("Test 3 (LC 2035 [-36, 36]): " + minimumDifferenceMeetInMiddle(arr3)); // 72
    }
}
