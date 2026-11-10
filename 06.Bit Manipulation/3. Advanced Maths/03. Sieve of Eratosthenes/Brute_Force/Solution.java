/*
QUESTION:-
Given a number N, calculate the prime numbers up to N using Sieve of Eratosthenes.

Example:
Input: N = 10
Output: 2 3 5 7
*/

/*
APPROACH:-
Check Primality Individually: For every number i from 2 to N, check if it is prime by testing divisors up to sqrt(i).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static ArrayList<Integer> sieveOfEratosthenes(int N) {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (isPrime(i)) primes.add(i);
        }
        return primes;
    }

    private static boolean isPrime(int x) {
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}

// TIME COMPLEXITY = O(N * sqrt(N))
// SPACE COMPLEXITY = O(1)
