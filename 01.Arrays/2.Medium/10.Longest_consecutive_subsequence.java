/*
QUESTION:-

Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

Example 1:
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Example 2:
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int maxStreak = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int currentNum = num, currentStreak = 1;
                while (set.contains(currentNum + 1)) {
                    currentNum++; currentStreak++;
                }
                maxStreak = Math.max(maxStreak, currentStreak);
            }
        }
        return maxStreak;
    }
}

// TIME COMPLEXITY = O(n), where n is the size of the input array.
// SPACE COMPLEXITY = O(n), as we are using a set to store the elements of the array.
