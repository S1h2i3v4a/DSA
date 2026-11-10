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
Binary Search: Search in range [1, x]. Calculate mid = low + (high - low)/2. If mid * mid <= x, ans = mid and low = mid + 1; else high = mid - 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        long low = 1, high = x, ans = 1;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (mid * mid <= x) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (int) ans;
    }
}

// TIME COMPLEXITY = O(log x)
// SPACE COMPLEXITY = O(1)
