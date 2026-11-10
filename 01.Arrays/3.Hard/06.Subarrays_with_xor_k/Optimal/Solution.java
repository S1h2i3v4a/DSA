/*
QUESTION:-
Given an array of integers A and an integer B. Find the total number of subarrays having bitwise XOR of all elements equals to B.

Example:
Input: A = [4, 2, 2, 6, 4], B = 6
Output: 4
*/

/*
APPROACH:-
Prefix XOR with HashMap: Maintain prefix XOR. At each element, if (prefixXor ^ B) exists in map, add its frequency to count. Update frequency of prefixXor in map.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int solve(int[] A, int B) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int xor = 0, count = 0;
        for (int x : A) {
            xor ^= x;
            int target = xor ^ B;
            count += map.getOrDefault(target, 0);
            map.put(xor, map.getOrDefault(xor, 0) + 1);
        }
        return count;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
