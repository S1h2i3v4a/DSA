/*QUESTION:
Given a number N and a bit number K, check if the Kth index bit of N is set or not. A bit is called set if it is 1. The position of the set bit '1' should be indexed starting with 0 from the least significant bit (LSB) side in the binary representation of the number.

Example:
Input: N = 4, K = 0
Output: No
Explanation: The binary representation of 4 is 100, in which the 0th index bit from the LSB is not set. So, return false.

Approach:
1. Perform a bitwise AND operation between N and (1 << K).
2. If the result is non-zero, it means the Kth index bit is set (1); otherwise, it is not set (0).

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
class Check_If_Ith_Bit_Is_Set {

    public static boolean checkKthBitLeftShift(int n, int k) {
        return (n & (1 << k)) != 0;
    }

    public static boolean checkKthBitRightShift(int n, int k) {
        return ((n >> k) & 1) == 1;
    }

    public static void main(String[] args) {
        int n = 5; // Binary: 101
        int k = 2; // 0-indexed: bit 2 is '1'
        System.out.println("Is bit " + k + " set in " + n + " (Left Shift)? " + checkKthBitLeftShift(n, k));
        System.out.println("Is bit " + k + " set in " + n + " (Right Shift)? " + checkKthBitRightShift(n, k));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
