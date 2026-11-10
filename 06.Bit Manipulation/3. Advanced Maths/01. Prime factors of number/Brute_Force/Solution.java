/*
QUESTION:-
Given a number N. Find its unique prime factors in increasing order.

Example:
Input: N = 100
Output: 2 5
*/

/*
APPROACH:-
Check all numbers up to N: For each i from 2 to N, check if i divides N and is prime.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static List<Integer> primeFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (n % i == 0 && isPrime(i)) {
                factors.add(i);
            }
        }
        return factors;
    }

    private static boolean isPrime(int x) {
        if (x <= 1) return false;
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}

// TIME COMPLEXITY = O(N * sqrt(N))
// SPACE COMPLEXITY = O(1)
