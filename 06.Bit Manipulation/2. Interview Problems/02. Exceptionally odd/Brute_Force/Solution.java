/*
QUESTION:-
Given an array of N positive integers where all numbers occur an even number of times except one number which occurs an odd number of times. Find that number.

Example:
Input: N = 7, Arr[] = {1, 2, 3, 2, 3, 1, 3}
Output: 3
*/

/*
APPROACH:-
Frequency Map: Count occurrences of each number in a HashMap. Return the number with odd frequency.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : map.keySet()) {
            if (map.get(num) % 2 != 0) return num;
        }
        return -1;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
