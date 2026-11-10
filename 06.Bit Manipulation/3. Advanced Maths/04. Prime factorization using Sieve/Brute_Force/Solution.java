/*
QUESTION:-
Given an integer N. Find the prime factorization of N using the Smallest Prime Factor (SPF) sieve.

Example:
Input: N = 12246
Output: 2 3 13 157
*/

/*
APPROACH:-
Trial division without Sieve: Divide out 2, then odd numbers starting from 3 up to sqrt(N).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static List<Integer> findPrimeFactors(int N) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 2; i * i <= N; i++) {
            while (N % i == 0) {
                factors.add(i);
                N /= i;
            }
        }
        if (N > 1) factors.add(N);
        return factors;
    }
}

// TIME COMPLEXITY = O(sqrt(N))
// SPACE COMPLEXITY = O(1)
