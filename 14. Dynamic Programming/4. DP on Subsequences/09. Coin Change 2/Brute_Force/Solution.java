/*
QUESTION:
Given an integer array coins and integer amount, return number of combinations that make up that amount.

APPROACH:
- Pure recursion: unbounded choice at index i (take or skip).
- solve(i, amount) = solve(i - 1, amount) + solve(i, amount - coins[i]).

TIME COMPLEXITY: O(2^Amount) exponential.
SPACE COMPLEXITY: O(Amount) recursion stack.
*/

class Solution {
    public int change(int amount, int[] coins) {
        return solve(coins.length - 1, amount, coins);
    }

    private int solve(int i, int amount, int[] coins) {
        if (amount == 0) return 1;
        if (i < 0) return 0;
        int notTake = solve(i - 1, amount, coins);
        int take = 0;
        if (coins[i] <= amount) take = solve(i, amount - coins[i], coins);
        return notTake + take;
    }
}
