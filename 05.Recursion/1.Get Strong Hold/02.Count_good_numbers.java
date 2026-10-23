/*QUESTION:
A digit string is good if the digits (0-indexed) at even indices are even and the digits at odd indices are prime (2, 3, 5, or 7).
Given an integer n, return the total number of good digit strings of length n. Since the answer may be large, return it modulo 109 + 7.
A digit string is a string consisting of digits 0 through 9 that may contain leading zeros.

Example 1:
Input: n = 1
Output: 5
Explanation: The good numbers of length 1 are "0", "2", "4", "6", "8".

Example 2:
Input: n = 4
Output: 400

Example 3:
Input: n = 50
Output: 564908303

Approach:
- We can observe that for a good digit string of length n, each digit can be either even or a prime number (2, 3, 5, or 7).
- For odd indices, there are 4 choices (2, 3, 5, or 7) since they must be prime.
- For even indices, there are 5 choices (0, 2, 4, 6, or 8) since they must be even.
- We can use a recursive approach to count the number of good digit strings.
- The base case is when n = 1, in which case there are 5 possible digit strings (0, 2, 4, 6, or 8).
- For even lengths (n % 2 == 0), we multiply the count of good digit strings of length n-1 by 4 (since there are 4 choices for odd indices).
- For odd lengths (n % 2 != 0), we multiply the count of good digit strings of length n-1 by 5 (since there are 5 choices for even indices).
- We return the result modulo 10^9 + 7 to handle large outputs.

Time Complexity: O(n) (due to the recursive calls)
Space Complexity: O(n) (due to the recursive calls)

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    private long MOD = 1_000_000_007L;
    public int countGoodNumbers(long n) {
        long even = (n + 1) / 2;
        long odd = n / 2;
        long first = power(5, even);
        long second = power(4, odd);
        return (int) ((first * second) % MOD);
    }
    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
