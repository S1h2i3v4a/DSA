/*
QUESTION:-
Given an integer x, find the square root of x. If x is not a perfect square, then return floor(sqrt(x)).

Example 1:
Input: x = 5
Output: 2
Explanation: Since 5 is not a perfect square, floor(sqrt(5)) is 2.

Example 2:
Input: x = 4
Output: 2
Explanation: Since 4 is a perfect square, sqrt(4) is 2.
*/

/*
APPROACH:-
Linear Search: Iterate from 1 upwards. Find the largest integer i such that i * i <= x.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        long ans = 1;
        for (long i = 1; i * i <= x; i++) {
            ans = i;
        }
        return (int) ans;
    }
}

// TIME COMPLEXITY = O(sqrt(x))
// SPACE COMPLEXITY = O(1)
