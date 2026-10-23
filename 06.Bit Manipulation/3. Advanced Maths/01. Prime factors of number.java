/*
QUESTION:
Given a number N, find its unique prime factors in increasing order.

Example:
Input: N = 100
Output: 2 5
Explanation: 2 and 5 are the unique prime factors of 100.

APPROACH:
To find the unique prime factors of a number N, we can iterate from 2 to sqrt(N) and check if each number divides N.
1. Initialize an empty vector `ans` to store the prime factors.
2. Iterate from 2 to sqrt(N):
   - If `i` divides `N` (i.e., N % i == 0):
     - Add `i` to `ans`.
     - Divide `N` by `i` until it is no longer divisible by `i`.
3. If `N` is still greater than 1, it means that `N` itself is a prime factor. Add it to `ans`.
4. Return `ans`.

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.ArrayList;
import java.util.List;

class Print_Prime_Factors_Of_A_Number {

    public static List<Integer> primeFactors(int n) {
        List<Integer> factors = new ArrayList<>();

        if (n % 2 == 0) {
            factors.add(2);
            while (n % 2 == 0) {
                n /= 2;
            }
        }

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                factors.add(i);
                while (n % i == 0) {
                    n /= i;
                }
            }
        }

        if (n > 2) {
            factors.add(n);
        }

        return factors;
    }

    public static void main(String[] args) {
        int n = 60;
        System.out.println("Prime factors of " + n + ": " + primeFactors(n));
    }
}

// TIME COMPLEXITY = O(sqrt(N))
// SPACE COMPLEXITY = O(1) (excluding the space required for the output vector)
