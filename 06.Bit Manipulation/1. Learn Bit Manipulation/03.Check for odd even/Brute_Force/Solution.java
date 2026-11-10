/*
QUESTION:-
Given a positive integer N, determine whether it is odd or even.

Example:
Input: N = 15
Output: odd
*/

/*
APPROACH:-
Modulo Operator: Use arithmetic modulo operator (N % 2 != 0).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static String oddEven(int N) {
        return (N % 2 != 0) ? "odd" : "even";
    }
}

// TIME COMPLEXITY = O(1)
// SPACE COMPLEXITY = O(1)
