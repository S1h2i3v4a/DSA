/*
QUESTION:
Given a rod of length n inches and an array price[], determine maximum value obtainable by cutting up the rod and selling pieces.

APPROACH:
- Pure recursion: cut length `len` from 1 to n, gain price[len - 1] + solve(n - len).

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int cutRod(int[] price, int n) {
        if (n <= 0) return 0;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxVal = Math.max(maxVal, price[i] + cutRod(price, n - i - 1));
        }
        return maxVal;
    }
}
