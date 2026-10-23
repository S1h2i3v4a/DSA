/*
QUESTION:
Given two integers dividend and divisor, find the quotient after dividing dividend by divisor without using multiplication, division, and mod operator.

Example:
Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 gives a quotient of 3 and remainder of 1.

APPROACH:
To divide two integers without using multiplication, division, and mod operator, we can use a bitwise shifting approach.
1. Initialize a variable ans as 0 to store the quotient.
2. Check if the dividend and divisor have different signs to determine the sign of the quotient.
3. Take the absolute values of the dividend and divisor to work with positive numbers.
4. Iterate while the absolute value of the dividend is greater than or equal to the absolute value of the divisor:
    a. Initialize a variable q as 1 to represent the current quotient digit.
    b. Iterate while the absolute value of the dividend is greater than the absolute value of the divisor left-shifted by q:
        i. Increment q.
    c. Add (1 << (q - 1)) to the quotient ans.
    d. Subtract the absolute value of the divisor left-shifted by (q - 1) from the absolute value of the dividend.
5. Return the quotient ans, considering the sign based on the step 2.

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
class Divide_Two_Integers {

    public static int divide(int dividend, int divisor) {
        if (dividend == divisor) return 1;
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if (dividend == Integer.MIN_VALUE && divisor == 1) return Integer.MIN_VALUE;

        boolean isNegative = (dividend < 0) ^ (divisor < 0);

        long n = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);

        long quotient = 0;

        while (n >= d) {
            int count = 0;
            while (n >= (d << (count + 1))) {
                count++;
            }
            quotient += 1L << count;
            n -= d << count;
        }

        if (quotient > Integer.MAX_VALUE) {
            return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        return isNegative ? (int) -quotient : (int) quotient;
    }

    public static void main(String[] args) {
        int dividend = 10;
        int divisor = 3;
        System.out.println(dividend + " / " + divisor + " = " + divide(dividend, divisor));

        int dividend2 = 7;
        int divisor2 = -3;
        System.out.println(dividend2 + " / " + divisor2 + " = " + divide(dividend2, divisor2));
    }
}

// TIME COMPLEXITY = O(log n), where n is the absolute value of the dividend
// SPACE COMPLEXITY = O(1)
