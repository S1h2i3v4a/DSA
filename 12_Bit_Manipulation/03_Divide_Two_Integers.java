/**
 * Problem Name: Divide Two Integers
 * Platform: LeetCode (29)
 * Difficulty: Medium
 * 
 * Time Complexity: O(log^2 N) where N is dividend magnitude.
 * Space Complexity: O(1)
 * 
 * Approach:
 * Bitwise Exponential Subtraction without multiplication, division, or mod operator.
 * Shift divisor left (`divisor << count`) until it exceeds dividend, then subtract (`(divisor << count)`)
 * and add `(1L << count)` to quotient. Repeat until dividend < divisor.
 * Handle overflow cases (e.g. Integer.MIN_VALUE / -1 = Integer.MAX_VALUE).
 */

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
