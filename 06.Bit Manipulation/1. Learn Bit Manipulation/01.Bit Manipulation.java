/*QUESTION:-
Given a 32-bit unsigned integer num and an integer i, perform the following operations on the number:
1. Get the ith bit.
2. Set the ith bit.
3. Clear the ith bit.

Note: The bits are indexed from 1 instead of 0 (1-based indexing).

Example:
Input: 70, 3
Output: 1 70 66
Explanation:
- The bit at the 3rd position from the least significant bit (LSB) is 1. (1 0 0 0 1 1 0)
- The value of the given number after setting the 3rd bit is 70.
- The value of the given number after clearing the 3rd bit is 66. (1 0 0 0 0 1 0)

Approach:
1. Subtract 1 from i to adjust the index to 0-based.
2. To get the ith bit, perform a bitwise AND operation between num and (1 << i). If the result is non-zero, the ith bit is set; otherwise, it is cleared.
3. To set the ith bit, perform a bitwise OR operation between num and (1 << i).
4. To clear the ith bit, perform a bitwise AND operation between num and the complement of (1 << i).

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static void bitManipulation(int num, int i) {
        int getBit = (num & (1 << (i - 1))) != 0 ? 1 : 0;
        int setBit = num | (1 << (i - 1));
        int clearBit = num & (~(1 << (i - 1)));
        System.out.println(getBit + " " + setBit + " " + clearBit);
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
