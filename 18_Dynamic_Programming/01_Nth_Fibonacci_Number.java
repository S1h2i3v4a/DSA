/**
 * Problem Name: Nth Fibonacci Number (Base of Dynamic Programming)
 * Platform: LeetCode (LC 509) / GeeksforGeeks
 * Difficulty: Easy
 *
 * Time Complexity:
 *   - Approach 1 (Recursive): O(2^N)
 *   - Approach 2 (Memoization - Top Down): O(N)
 *   - Approach 3 (Tabulation - Bottom Up): O(N)
 *   - Approach 4 (Space-Optimized DP): O(N)
 *   - Approach 5 (Matrix Exponentiation): O(log N)
 * Space Complexity:
 *   - Approach 1: O(N) recursion stack
 *   - Approach 2: O(N) memo table + recursion stack
 *   - Approach 3: O(N) dp array
 *   - Approach 4: O(1) auxiliary variables
 *   - Approach 5: O(1)
 *
 * Approach:
 *   - The Fibonacci sequence is defined as:
 *       F(0) = 0, F(1) = 1
 *       F(n) = F(n - 1) + F(n - 2) for n >= 2
 *   - This is the canonical foundational problem of Dynamic Programming, demonstrating:
 *     1. Overlapping Subproblems: F(n-1) and F(n-2) both repeatedly compute F(n-3), etc.
 *     2. Optimal Substructure: Solution to F(n) depends directly on optimal sub-solutions F(n-1) and F(n-2).
 *   - We implement all major DP paradigms:
 *     1. Top-Down DP (Memoization)
 *     2. Bottom-Up DP (Tabulation)
 *     3. Space-Optimized DP (Rolling variables)
 *     4. Matrix Exponentiation for O(log N) calculation modulo 10^9 + 7.
 */

import java.util.Arrays;

class NthFibonacciNumber {

    private static final int MOD = 1000000007;

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int fibMemo(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return memoHelper(n, dp);
    }

    private static int memoHelper(int n, int[] dp) {
        if (n <= 1) return n;
        if (dp[n] != -1) return dp[n];
        return dp[n] = (memoHelper(n - 1, dp) + memoHelper(n - 2, dp)) % MOD;
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int fibTabulation(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }

        return dp[n];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(1) Space
    // ==========================================
    public static int fibSpaceOptimized(int n) {
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

    // ==========================================
    // Approach 4: Matrix Exponentiation - O(log N)
    // ==========================================
    // [[F(n+1), F(n)], [F(n), F(n-1)]] = [[1, 1], [1, 0]]^n
    public static int fibMatrixExponentiation(int n) {
        if (n <= 1) return n;
        long[][] base = {{1, 1}, {1, 0}};
        long[][] result = matrixPower(base, n - 1);
        return (int) result[0][0];
    }

    private static long[][] matrixPower(long[][] mat, int p) {
        long[][] res = {{1, 0}, {0, 1}}; // Identity matrix
        long[][] cur = mat;

        while (p > 0) {
            if ((p & 1) == 1) {
                res = multiplyMatrix(res, cur);
            }
            cur = multiplyMatrix(cur, cur);
            p >>= 1;
        }

        return res;
    }

    private static long[][] multiplyMatrix(long[][] a, long[][] b) {
        long[][] c = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return c;
    }

    public static void main(String[] args) {
        int[] testCases = {0, 1, 2, 5, 10, 30};

        System.out.println("=== Testing Nth Fibonacci Implementations ===");
        for (int n : testCases) {
            int memo = fibMemo(n);
            int tab = fibTabulation(n);
            int opt = fibSpaceOptimized(n);
            int mat = fibMatrixExponentiation(n);

            System.out.printf("n = %2d -> Memo: %d | Tab: %d | Opt: %d | MatrixExp: %d%n",
                    n, memo, tab, opt, mat);
        }
    }
}
