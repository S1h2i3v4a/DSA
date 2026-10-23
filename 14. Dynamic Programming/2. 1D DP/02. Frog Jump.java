/*
QUESTION:-
Geek wants to climb from the 0th stair to the (n-1)th stair. At a time, the Geek can climb either one or two steps.
A height[N] array is also given. Whenever the geek jumps from stair i to stair j, the energy consumed in the jump is abs(height[i] - height[j]), where abs() means the absolute difference.
Return the minimum energy that can be used by the Geek to jump from stair 0 to stair N-1.

Approach:
1. We can use dynamic programming with memoization to find the minimum energy required to jump from the 0th stair to the (n-1)th stair.
2. We define a helper function fmemo(i, h, dp) that calculates the minimum energy required to jump from the 0th stair to the ith stair using memoization.
3. The function checks if the minimum energy for the ith stair is already calculated and stored in the dp array. If yes, it returns the value from dp.
4. Otherwise, it calculates the minimum energy by taking two possible jumps - either from (i-1)th stair or from (i-2)th stair - and stores the result in dp[i].
5. The base case is when i is 0, in which case the function returns 0 as the Geek is already at the 0th stair.
6. In the main function minimumEnergy(height, n), we create a dp array of size n initialized with -1 and call the fmemo function passing n-1 (last stair), the height array, and the dp array as arguments.

Complexity Analysis:
- The time complexity of the fmemo function is O(n) because we are calculating and storing the minimum energy for each stair.
- The space complexity is also O(n) to store the dp array.
*/

import java.io.*;
import java.util.*;

// CODE:-
class Solution {
    public int minimumEnergy(int[] height, int n) {
        if (n <= 1) return 0;
        int prev = 0, prev2 = 0;
        for (int i = 1; i < n; i++) {
            int jumpOne = prev + Math.abs(height[i] - height[i - 1]);
            int jumpTwo = Integer.MAX_VALUE;
            if (i > 1) {
                jumpTwo = prev2 + Math.abs(height[i] - height[i - 2]);
            }
            int cur = Math.min(jumpOne, jumpTwo);
            prev2 = prev;
            prev = cur;
        }
        return prev;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
