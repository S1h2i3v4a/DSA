/*
QUESTION:
Given an integer array 'nums' and an integer 'k', find the number of good subarrays of 'nums'.

A good array is an array where the number of different integers in that array is exactly 'k'.

For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.

EXAMPLE:
Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]

APPROACH:
We can solve this problem using a sliding window approach.

1. Create a function, kDistinctIntegers, that takes 'k' and the input vector 's' as parameters.
   - Initialize an unordered_map, mp, to store the frequency of elements.
   - Initialize 'start' to 0 and 'ans' to 0.
   - Iterate over the vector from left to right:
     - Increment the frequency of the current element in the map.
     - If the number of distinct elements in the map exceeds 'k', move the 'start' pointer to the right and remove elements from the map until the number of distinct elements becomes equal to 'k'.
     - Update 'ans' by adding the number of subarrays that can be formed with exactly 'k' distinct integers, which is equal to (i - start + 1).
   - Return 'ans', which represents the total number of good subarrays.

2. Create a function, subarraysWithKDistinct, that takes 'nums' and 'k' as parameters.
   - Return the difference between the number of good subarrays with 'k' distinct integers and the number of good subarrays with 'k-1' distinct integers, which can be calculated using the kDistinctIntegers function.

COMPLEXITY ANALYSIS:
- Time complexity: O(n), where n is the length of the input vector 'nums'. We iterate over the vector once using the sliding window approach.
- Space complexity: O(k), as the space used by the unordered_map is proportional to the number of distinct elements, which can be at most 'k'.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    long substrCount(String s, int k) {
        return atMostK(s, k) - atMostK(s, k - 1);
    }
    private long atMostK(String s, int k) {
        if (k < 0) return 0;
        int[] freq = new int[26];
        int left = 0, distinct = 0;
        long count = 0;
        for (int right = 0; right < s.length(); right++) {
            if (freq[s.charAt(right) - 'a']++ == 0) distinct++;
            while (distinct > k) {
                if (--freq[s.charAt(left) - 'a'] == 0) distinct--;
                left++;
            }
            count += (right - left + 1);
        }
        return count;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
