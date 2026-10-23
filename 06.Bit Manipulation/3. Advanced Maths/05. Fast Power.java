/*
QUESTION:
Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).

Example:
Input: x = 2.00000, n = 10
Output: 1024.00000

APPROACH:
To calculate x raised to the power n, we can use the concept of binary exponentiation.
1. If n is 0, return 1, as any number raised to the power 0 is 1.
2. Initialize a variable res to 1 to store the result.
3. If n is negative, set isNegative flag to true and make n positive.
4. Iterate until n becomes 0:
   - If the least significant bit of n is 1 (i.e., n is odd), multiply res by x.
   - Update x to x^2 by multiplying it with itself.
   - Right-shift n by 1 to remove the least significant bit.
5. If isNegative is true, return 1/res; otherwise, return res.

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(log(n))
// SPACE COMPLEXITY = O(1)
