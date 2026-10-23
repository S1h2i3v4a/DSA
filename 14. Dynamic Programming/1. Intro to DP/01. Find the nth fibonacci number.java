/*
QUESTION:-
Find the nth fibonacci number.

Approach:
1. We can use dynamic programming to calculate the nth Fibonacci number.
2. We define a helper function fibo(n, dp) that calculates the nth Fibonacci number using memoization.
3. The function checks if the nth Fibonacci number is already calculated and stored in the dp array. If yes, it returns the value from dp.
4. Otherwise, it calculates the nth Fibonacci number by recursively calling fibo(n-1, dp) and fibo(n-2, dp) and stores the result in dp.
5. The base cases are when n is 0 or 1, in which case the function returns n.
6. In the main function, we take the input for n and create a dp array of size n+1 initialized with -1.
7. We then call the fibo function passing n and the dp array as arguments.
8. Finally, we print the value of dp[n], which represents the nth Fibonacci number.

Complexity Analysis:
- The time complexity of the fibo function is O(n) because we are calculating and storing the Fibonacci numbers from 0 to n.
- The space complexity is also O(n) to store the dp array.
*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
