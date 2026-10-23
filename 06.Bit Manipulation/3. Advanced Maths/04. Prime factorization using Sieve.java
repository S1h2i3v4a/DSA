/*
QUESTION:
Given a positive number N, compute its prime factorization using the concept of Sieve.

Example:
Input: N = 12246
Output: 2 3 13 157
Explanation: The prime factorization of 12246 is 2 * 3 * 13 * 157.

APPROACH:
To compute the prime factorization of a number N, we can use the concept of Sieve.
1. Create a boolean vector `prime` of size N+1 and initialize all elements to true. This vector will be used to mark numbers as prime or not.
2. Create an empty vector `ans` to store the prime factors of N.
3. Iterate from 2 to sqrt(N):
   - If prime[i] is true (i.e., i is a prime number):
     - While N is divisible by i, add i to the `ans` vector and divide N by i.
     - Mark all multiples of i as false in the `prime` vector, as they are not prime.
4. If N is greater than 1, it means N is a prime number itself, so add N to the `ans` vector.
5. Return the `ans` vector, which will contain the prime factors of N.

CODE:*/

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
                    if (spf[j] == j) spf[j] = i;
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (N != 1) {
            ans.add(spf[N]);
            N /= spf[N];
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(N log(log N))
// SPACE COMPLEXITY = O(N)
