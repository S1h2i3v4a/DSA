/**
 * Problem Name: Pow(x, n)
 * Platform: LeetCode (50)
 * Difficulty: Medium
 * 
 * Time Complexity: O(log N)
 * Space Complexity: O(1)
 * 
 * Approach:
 * Binary Exponentiation (Fast Power).
 * 1. Convert `n` to long `N` to prevent Integer.MIN_VALUE overflow (-2147483648 -> +2147483648).
 * 2. If N < 0, handle negative power by taking absolute N and setting flag.
 * 3. While N > 0:
 *    - If N % 2 == 1, multiply `ans *= x` and subtract 1 from N.
 *    - Square `x = x * x` and halve `N = N / 2`.
 * 4. Return `1.0 / ans` if original n was negative.
 */

class Pow_X_N {

    public static double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            N = -N;
        }

        double ans = 1.0;
        while (N > 0) {
            if (N % 2 == 1) {
                ans = ans * x;
                N = N - 1;
            } else {
                x = x * x;
                N = N / 2;
            }
        }

        if (n < 0) {
            ans = 1.0 / ans;
        }

        return ans;
    }

    public static void main(String[] args) {
        double x = 2.00000;
        int n = 10;
        System.out.println(x + "^" + n + " = " + myPow(x, n));

        double x2 = 2.10000;
        int n2 = 3;
        System.out.println(x2 + "^" + n2 + " = " + myPow(x2, n2));

        double x3 = 2.00000;
        int n3 = -2;
        System.out.println(x3 + "^" + n3 + " = " + myPow(x3, n3));
    }
}
