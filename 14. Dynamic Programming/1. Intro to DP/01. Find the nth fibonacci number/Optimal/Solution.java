/*
QUESTION:
Find the nth fibonacci number.

APPROACH:
- Space-Optimized Bottom-Up Dynamic Programming.
- Observe that computing F(i) only requires the previous two values: F(i-1) and F(i-2).
- Maintain two variables `prev1` and `prev2` and update them iteratively.

TIME COMPLEXITY: O(N) single linear scan.
SPACE COMPLEXITY: O(1) constant extra space.
*/

class Solution {
    private static final int MOD = 1000000007;

    public int nthFibonacci(int n) {
        if (n <= 1) return n;
        int prev2 = 0;
        int prev1 = 1;

        for (int i = 2; i <= n; i++) {
            int curr = (prev1 + prev2) % MOD;
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
