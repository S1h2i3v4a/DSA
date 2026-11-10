/*
QUESTION:-
Given an integer N. Find the prime factorization of N using the Smallest Prime Factor (SPF) sieve.

Example:
Input: N = 12246
Output: 2 3 13 157
*/

/*
APPROACH:-
Smallest Prime Factor (SPF) Sieve: Precompute spf[i] = smallest prime factor of i using sieve. Factorize N in O(log N) steps by repeatedly dividing N by spf[N].
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static List<Integer> findPrimeFactors(int N) {
        int[] spf = new int[N + 1];
        for (int i = 1; i <= N; i++) spf[i] = i;

        for (int i = 2; i * i <= N; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= N; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }

        List<Integer> factors = new ArrayList<>();
        while (N != 1) {
            factors.add(spf[N]);
            N /= spf[N];
        }
        return factors;
    }
}

// TIME COMPLEXITY = O(N log(log N)) preprocessing, O(log N) factorization
// SPACE COMPLEXITY = O(N)
