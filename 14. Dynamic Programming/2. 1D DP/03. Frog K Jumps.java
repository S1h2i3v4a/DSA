/*
QUESTION:-
Geek wants to climb from the 0th stair to the (n-1)th stair. At a time, the Geek can climb k steps.
A height[N] array is also given. Whenever the geek jumps from stair i to stair j, the energy consumed in the jump is abs(height[i] - height[j]), where abs() means the absolute difference.
Return the minimum energy that can be used by the Geek to jump from stair 0 to stair N-1.

Example:
Input:
4 2
10 40 30 10
Output:
40

Explanation:
For 'n' = 4, 'k' = 2, height = {10, 40, 30, 10}

Initially, we are present at stone 1 having height 10. We can reach stone 3 as 'k' is 2. So, the cost incurred is |10 - 30| = 20.

Now, we are present at stone 3, we can reach stone 4 as 'k' is 2. So, the cost incurred is |30 - 10| = 20. So, the total cost is 40. We can show any other path will lead to greater cost.

Approach:
1. We can use dynamic programming with memoization to find the minimum energy required to jump from the 0th stair to the (n-1)th stair.
2. We define a helper function fmemo(i, k, h, dp) that calculates the minimum energy required to jump from the 0th stair to the ith stair using memoization.
3. The function checks if the minimum energy for the ith stair is already calculated and stored in the dp array. If yes, it returns the value from dp.
4. Otherwise, it calculates the minimum energy by considering all possible jumps from i-j (where j varies from 1 to k) and stores the result in dp[i].
5. The base case is when i is 0, in which case the function returns 0 as the Geek is already at the 0th stair.
6. In the main function minimizeCost(n, k, height), we create a dp array of size n initialized with -1 and call the fmemo function passing n-1 (last stair), k, the height array, and the dp array as arguments.

Complexity Analysis:
- The time complexity of the fmemo function is O(n*k) because we are calculating and storing the minimum energy for each stair and considering k possible jumps at each stair.
- The space complexity is also O(n) to store the dp array.
*/

import java.io.*;
import java.util.*;

// CODE:-
class Solution {
    public int minimizeCost(int[] height, int n, int k) {
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            int minSteps = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int jump = dp[i - j] + Math.abs(height[i] - height[i - j]);
                    minSteps = Math.min(minSteps, jump);
                }
            }
            dp[i] = minSteps;
        }
        return dp[n - 1];
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
