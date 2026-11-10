/*
QUESTION:-
Given a 32-bit integer num and an integer i (1-based indexing).
Perform the following three operations:
1. Get the i-th bit of num.
2. Set the i-th bit of num.
3. Clear the i-th bit of num.
Print the results separated by space.

Example:
Input: 70 3
Output: 1 70 66
*/

/*
APPROACH:-
Bitwise Operations (1-based indexing):
1. Get bit: (num & (1 << (i - 1))) != 0 ? 1 : 0
2. Set bit: num | (1 << (i - 1))
3. Clear bit: num & (~(1 << (i - 1)))
*/

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

// TIME COMPLEXITY = O(1)
// SPACE COMPLEXITY = O(1)
