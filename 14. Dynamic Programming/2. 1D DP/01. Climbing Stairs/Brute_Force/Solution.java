/*
QUESTION:
You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

APPROACH:
- Pure recursion: from step n, we can either arrive from n-1 or n-2.
- climbStairs(n) = climbStairs(n-1) + climbStairs(n-2).

TIME COMPLEXITY: O(2^N) exponential branching.
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int climbStairs(int n) {
        if (n <= 2) return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
