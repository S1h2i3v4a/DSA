/*
QUESTION:
Find the nth fibonacci number.

APPROACH:
- Pure recursion: F(n) = F(n-1) + F(n-2) with base cases F(0) = 0 and F(1) = 1.
- Each call branches into two recursive calls forming a binary tree of calls.

TIME COMPLEXITY: O(2^N) due to exponential recursive branching.
SPACE COMPLEXITY: O(N) auxiliary recursion stack space.
*/

class Solution {
    private static final int MOD = 1000000007;

    public int nthFibonacci(int n) {
        if (n <= 1) return n;
        return (nthFibonacci(n - 1) + nthFibonacci(n - 2)) % MOD;
    }
}
