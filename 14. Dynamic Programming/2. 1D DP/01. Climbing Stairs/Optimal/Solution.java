/*
QUESTION:
You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

APPROACH:
- Space-Optimized Tabulation:
- Since only the previous two steps are required to calculate the current step ways,
  maintain two variables `prev2` and `prev`.

TIME COMPLEXITY: O(N) iterative loop.
SPACE COMPLEXITY: O(1) constant extra variables.
*/

class Solution {
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int prev2 = 1, prev = 2;
        for (int i = 3; i <= n; i++) {
            int cur = prev + prev2;
            prev2 = prev;
            prev = cur;
        }
        return prev;
    }
}
