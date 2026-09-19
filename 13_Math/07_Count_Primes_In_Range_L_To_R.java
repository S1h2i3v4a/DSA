/**
 * Problem Name: Count Primes in Range L to R / Sieve of Eratosthenes
 * Platform: LeetCode (204) / GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(R log(log R)) for precomputing Sieve + O(1) query complexity.
 * Space Complexity: O(R) for prime array and prefix sum array.
 * 
 * Approach:
 * Sieve of Eratosthenes with Prefix Sums.
 * 1. Mark non-primes using Sieve of Eratosthenes up to R.
 * 2. Precompute prefix sum `prefixPrimes[i]` = total primes from 1 to i.
 * 3. Range query [L, R] answer = `prefixPrimes[R] - prefixPrimes[L - 1]`.
 */

import java.util.Arrays;

class Count_Primes_In_Range_L_To_R {

    public static int countPrimesInRange(int L, int R) {
        if (R < 2 || L > R) return 0;

        boolean[] isPrime = new boolean[R + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int p = 2; p * p <= R; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= R; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        int[] prefixPrimes = new int[R + 1];
        for (int i = 1; i <= R; i++) {
            prefixPrimes[i] = prefixPrimes[i - 1] + (isPrime[i] ? 1 : 0);
        }

        return prefixPrimes[R] - (L > 0 ? prefixPrimes[L - 1] : 0);
    }

    public static void main(String[] args) {
        int L = 10, R = 30;
        System.out.println("Number of prime numbers in range [" + L + ", " + R + "]: " + countPrimesInRange(L, R));
    }
}
