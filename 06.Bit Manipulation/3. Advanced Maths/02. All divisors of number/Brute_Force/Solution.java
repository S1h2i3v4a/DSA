/*
QUESTION:-
Given an integer N, print all the divisors of N in increasing order.

Example:
Input: N = 20
Output: 1 2 4 5 10 20
*/

/*
APPROACH:-
Linear Scan 1 to N: Loop i from 1 to N. If N % i == 0, print i.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static void print_divisors(int n) {
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                System.out.print(i + " ");
            }
        }
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
