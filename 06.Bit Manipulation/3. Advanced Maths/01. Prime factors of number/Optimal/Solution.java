/*
QUESTION:-
Given a number N. Find its unique prime factors in increasing order.

Example:
Input: N = 100
Output: 2 5
*/

/*
APPROACH:-
Trial division up to sqrt(N): Divide out all factors of 2. Then check odd numbers i from 3 up to sqrt(N). If i divides N, add i and divide N completely by i. Finally, if N > 1, N itself is prime.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static List<Integer> primeFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
                while (n % i == 0) {
                    n /= i;
                }
            }
        }
        if (n > 1) {
            factors.add(n);
        }
        return factors;
    }
}

// TIME COMPLEXITY = O(sqrt(N))
// SPACE COMPLEXITY = O(1)
