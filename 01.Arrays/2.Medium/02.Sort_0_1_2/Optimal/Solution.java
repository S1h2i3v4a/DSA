/*
QUESTION:-
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order 0, 1, and 2.

Example:
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
*/

/*
APPROACH:-
Dutch National Flag Algorithm: Maintain three pointers: low, mid, and high. If nums[mid] == 0: swap(low, mid), low++, mid++. If nums[mid] == 1: mid++. If nums[mid] == 2: swap(mid, high), high--.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 0) {
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
