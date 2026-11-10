/*
QUESTION:-
Given a positive integer N, determine whether it is odd or even.

Example:
Input: N = 15
Output: odd
*/

/*
APPROACH:-
Bitwise AND: The least significant bit (LSB) of any odd number is 1 and of any even number is 0. Check (N & 1) == 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static String oddEven(int N) {
        return (N & 1) != 0 ? "odd" : "even";
    }
}

// TIME COMPLEXITY = O(1)
// SPACE COMPLEXITY = O(1)
