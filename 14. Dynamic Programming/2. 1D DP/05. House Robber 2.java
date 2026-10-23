/*
QUESTION:-
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.

Approach:
1. Since the houses are arranged in a circle, the robber cannot rob the first and last house together as they are adjacent.
2. To solve this problem, we can divide it into two subproblems: one where the robber robs from the first house to the second-last house (excluding the last house), and another where the robber robs from the second house to the last house (excluding the first house).
3. We can use dynamic programming with memoization to calculate the maximum amount of money that can be robbed in each subproblem.
4. We define a helper function fmemo(e, i, nums, dp) that calculates the maximum amount of money that can be robbed from the e-th to the i-th house using memoization.
5. The function checks if the maximum amount for the i-th house is already calculated and stored in the dp array. If yes, it returns the value from dp.
6. Otherwise, it calculates the maximum amount by considering two options:
   a) Rob the current house and add the money with the maximum amount from the (i-2)th house (since adjacent houses cannot be robbed).
   b) Skip the current house and take the maximum amount from the (i-1)th house.
7. The base case is when i is less than e, in which case the function returns 0 as there are no houses to rob.
8. In the main function rob(nums), we first check if there is only one house. If yes, the robber can only rob that house, so we return the amount in that case.
9. Otherwise, we create a dp array of size n initialized with -1 and call the fmemo function twice: one for robbing from the first to the second-last house and the other for robbing from the second to the last house.
10. Finally, we return the maximum amount from the two subproblems, which gives the maximum amount the robber can rob without alerting the police.

Complexity Analysis:
- The time complexity of the fmemo function is O(n) because we are calculating the maximum amount for each house only once and storing it in the dp array.
- The space complexity is also O(n) to store the dp array.
*/

import java.io.*;
import java.util.*;

// CODE:-
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(robLinear(nums, 0, n - 2), robLinear(nums, 1, n - 1));
    }
    private int robLinear(int[] nums, int start, int end) {
        int prev = 0, prev2 = 0;
        for (int i = start; i <= end; i++) {
            int cur = Math.max(prev, prev2 + nums[i]);
            prev2 = prev;
            prev = cur;
        }
        return prev;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
