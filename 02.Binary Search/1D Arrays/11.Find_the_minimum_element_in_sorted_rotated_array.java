/*
QUESTION:
Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

APPROACH:
We can use the binary search approach to find the minimum element.
1. Initialize low = 0 and high = n-1, where n is the size of the array.
2. While low < high, calculate mid = low + (high - low) / 2.
3. If nums[mid] > nums[high], it means the minimum element is on the right side of mid, so update low = mid+1.
4. Otherwise, the minimum element is on the left side of mid or mid itself, so update high = mid.
5. After the loop ends, low will be pointing to the minimum element index.
6. Return nums[low] as the result.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1, ans = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[low] <= nums[high]) {
                ans = Math.min(ans, nums[low]);
                break;
            }
            if (nums[low] <= nums[mid]) {
                ans = Math.min(ans, nums[low]);
                low = mid + 1;
            } else {
                ans = Math.min(ans, nums[mid]);
                high = mid - 1;
            }
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(log n)
// SPACE COMPLEXITY = O(1)
