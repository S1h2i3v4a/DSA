/*
QUESTION:-
You are given two numbers a and b. Your task is to swap the given two numbers without using a third/temporary variable.

Example:
Input: a = 13, b = 9
Output: 9 13
*/

/*
APPROACH:-
Bitwise XOR: XOR preserves information without overflow: a = a ^ b, b = a ^ b, a = a ^ b.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static List<Integer> get(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        return Arrays.asList(a, b);
    }
}

// TIME COMPLEXITY = O(1)
// SPACE COMPLEXITY = O(1)
