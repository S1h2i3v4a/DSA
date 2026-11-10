/*
QUESTION:-
Given an integer N, print all the divisors of N in increasing order.

Example:
Input: N = 20
Output: 1 2 4 5 10 20
*/

/*
APPROACH:-
Iterate up to sqrt(N): Divisors occur in pairs (i, n / i). Iterate up to sqrt(N), collect both divisors, sort and print.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static void print_divisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (n / i != i) {
                    divisors.add(n / i);
                }
            }
        }
        Collections.sort(divisors);
        for (int d : divisors) {
            System.out.print(d + " ");
        }
    }
}

// TIME COMPLEXITY = O(sqrt(N) log(divisors))
// SPACE COMPLEXITY = O(sqrt(N))
