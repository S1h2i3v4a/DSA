/**
 * Problem Name: Boolean Evaluation / Evaluate Boolean Expression to True
 * Platform: GeeksforGeeks / Coding Ninjas
 * Difficulty: Hard
 *
 * Time Complexity: O(N^3) where N is expression length
 * Space Complexity: O(N^2 * 2) for DP table
 *
 * Approach:
 *   - Given a boolean expression with operands 'T' (True), 'F' (False) and operators
 *     '&' (AND), '|' (OR), '^' (XOR).
 *   - Count ways to parenthesize so that the entire expression evaluates to TRUE.
 *   - State: f(i, j, isTrue):
 *     Number of ways to parenthesize sub-expression from i to j to evaluate to isTrue (1 or 0).
 *   - Partition at each operator k (from i + 1 to j - 1 with step 2):
 *     Calculate LT = f(i, k - 1, 1), LF = f(i, k - 1, 0)
 *     Calculate RT = f(k + 1, j, 1), RF = f(k + 1, j, 0)
 *     Depending on operator at k (&, |, ^), calculate resulting ways.
 */

import java.util.Arrays;

class EvaluateBooleanExpressionToTrue {

    private static final int MOD = 1003; // Standard GFG modulo

    public static int countWays(int n, String exp) {
        int[][][] dp = new int[n][n][2];
        for (int[][] mat : dp) {
            for (int[] row : mat) {
                Arrays.fill(row, -1);
            }
        }
        return memoHelper(0, n - 1, 1, exp, dp);
    }

    private static int memoHelper(int i, int j, int isTrue, String exp, int[][][] dp) {
        if (i > j) return 0;
        if (i == j) {
            if (isTrue == 1) return exp.charAt(i) == 'T' ? 1 : 0;
            else return exp.charAt(i) == 'F' ? 1 : 0;
        }

        if (dp[i][j][isTrue] != -1) return dp[i][j][isTrue];

        long ways = 0;

        for (int k = i + 1; k <= j - 1; k += 2) {
            long lT = memoHelper(i, k - 1, 1, exp, dp);
            long lF = memoHelper(i, k - 1, 0, exp, dp);
            long rT = memoHelper(k + 1, j, 1, exp, dp);
            long rF = memoHelper(k + 1, j, 0, exp, dp);

            char op = exp.charAt(k);
            if (op == '&') {
                if (isTrue == 1) ways = (ways + (lT * rT)) % MOD;
                else ways = (ways + (lT * rF) + (lF * rT) + (lF * rF)) % MOD;
            } else if (op == '|') {
                if (isTrue == 1) ways = (ways + (lT * rT) + (lT * rF) + (lF * rT)) % MOD;
                else ways = (ways + (lF * rF)) % MOD;
            } else if (op == '^') {
                if (isTrue == 1) ways = (ways + (lT * rF) + (lF * rT)) % MOD;
                else ways = (ways + (lT * rT) + (lF * rF)) % MOD;
            }
        }

        return dp[i][j][isTrue] = (int) ways;
    }

    public static void main(String[] args) {
        // Test Case 1: exp = "T|T&F^T", n = 7 -> Expected: 4
        String exp1 = "T|T&F^T";
        System.out.println("Test 1: " + countWays(exp1.length(), exp1)); // 4

        // Test Case 2: exp = "T^F|F", n = 5 -> Expected: 2
        String exp2 = "T^F|F";
        System.out.println("Test 2: " + countWays(exp2.length(), exp2)); // 2
    }
}
