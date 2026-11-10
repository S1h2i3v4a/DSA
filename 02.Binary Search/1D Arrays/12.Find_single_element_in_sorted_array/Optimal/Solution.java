/*
QUESTION:-
You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once. Return the single element that appears only once.

Example:
Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2
*/

/*
APPROACH:-
Binary Search: Before the single element, pairs start at even index and end at odd index (even, odd). After the single element, pairs start at odd index and end at even index (odd, even). We can use this index property to binary search in O(log N).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (mid % 2 == 1) mid--;
            if (nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }
}

// TIME COMPLEXITY = O(log N)
// SPACE COMPLEXITY = O(1)
