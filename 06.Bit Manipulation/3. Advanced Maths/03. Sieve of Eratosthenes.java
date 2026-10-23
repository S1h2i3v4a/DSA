/*
QUESTION:
Given an integer n, return the number of prime numbers that are strictly less than n.

Example:
Input: n = 10
Output: 4
Explanation: There are 4 prime numbers less than 10, which are 2, 3, 5, and 7.

APPROACH:
To count the number of prime numbers less than a given number n, we can use the Sieve of Eratosthenes algorithm.
1. Create a boolean vector `primes` of size n+1 and initialize all elements to true. This vector will be used to mark numbers as prime or not.
2. Initialize a variable `cnt` to count the number of primes.
3. Iterate from 2 to n-1:
   - If primes[i] is true (i.e., i is a prime number), increment `cnt`.
   - Mark all multiples of i as false in the `primes` vector, as they are not prime.
4. Return `cnt`, which will be the count of prime numbers less than n.

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static ArrayList<Integer> sieveOfEratosthenes(int N) {
        boolean[] prime = new boolean[N + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= N; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= N; i += p) prime[i] = false;
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (prime[i]) ans.add(i);
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(n log log n)
// SPACE COMPLEXITY = O(n)
