/*
QUESTION:
Given integer array coins representing different denominations and integer amount, return fewest number of coins needed.

APPROACH:
- Pure recursion: at index i, either skip or take coin (unlimited supply so index stays i).
- Return min coins.

TIME COMPLEXITY: O(2^Amount) exponential.
SPACE COMPLEXITY: O(Amount) recursion stack.
*/

class Solution {
    public int coinChange(int[] coins, int amount) {
        int ans = solve(coins.length - 1, amount, coins);
        return ans >= (int) 1e9 ? -1 : ans;
    }

    private int solve(int i, int target, int[] coins) {
        if (target == 0) return 0;
        if (i < 0) return (int) 1e9;
        int notTake = solve(i - 1, target, coins);
        int take = (int) 1e9;
        if (coins[i] <= target) {
            take = 1 + solve(i, target - coins[i], coins);
        }
        return Math.min(notTake, take);
    }
}
