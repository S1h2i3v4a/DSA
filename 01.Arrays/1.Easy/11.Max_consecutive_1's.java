/*
QUESTION:-
Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.

Example 1:

Input: nums = [3,0,1]
Output: 2
Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
Example 2:

Input: nums = [0,1]
Output: 2
Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
*/

/*
APPROACH:-
-> Traverse the entire array and within it run a loop while element's are equal to 1 and store the count
-> Update the ans as max(ans,cnt)
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 0;
            }
        }
        return max;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(0)
