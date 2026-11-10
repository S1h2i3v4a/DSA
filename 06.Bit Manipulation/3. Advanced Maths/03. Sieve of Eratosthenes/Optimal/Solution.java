/*
QUESTION:-
Given a number N, calculate the prime numbers up to N using Sieve of Eratosthenes.

Example:
Input: N = 10
Output: 2 3 5 7
*/

/*
APPROACH:-
Sieve of Eratosthenes: Create boolean array isPrime initialized to true. For each prime p from 2 to sqrt(N), mark its multiples starting from p*p as false.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static ArrayList<Integer> sieveOfEratosthenes(int N) {
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        if (N >= 1) isPrime[1] = false;

        for (int p = 2; p * p <= N; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= N; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) primes.add(i);
        }
        return primes;
    }
}

// TIME COMPLEXITY = O(N log(log N))
// SPACE COMPLEXITY = O(N)
