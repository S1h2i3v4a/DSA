/*
QUESTION:
Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
- 0 <= a, b, c, d < n
- a, b, c, and d are distinct.
- nums[a] + nums[b] + nums[c] + nums[d] == target

Example:
Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

APPROACH:
To find the unique quadruplets that sum up to the target, we can use a similar approach as the threeSum problem. We will fix two elements (nums[a] and nums[b]) and use two pointers to find the remaining two elements (nums[c] and nums[d]) that sum up to the target.

1. Sort the input array nums in ascending order.
2. Iterate through the array with two pointers: a and b.
3. For each pair of elements nums[a] and nums[b], use two pointers c and d to find the remaining two elements that sum up to the target.
   - Initialize c as b + 1 and d as the last index of the array.
   - Calculate the target sum as trgt = target - (nums[a] + nums[b]).
   - While c < d, compare the sum of nums[c] and nums[d] with the target sum.
     - If the sum is equal to the target sum, we found a quadruplet. Add it to the answer and move the pointers c and d.
       - Important: Skip any duplicate elements while moving c and d.
     - If the sum is greater than the target sum, decrement d.
     - If the sum is less than the target sum, increment c.
4. Skip any duplicate elements for pointers a and b to avoid duplicate quadruplets.
5. Return the answer array containing unique quadruplets.


CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int k = j + 1, l = n - 1;
                while (k < l) {
                    long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];
                    if (sum < target) k++;
                    else if (sum > target) l--;
                    else {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        k++;
                        l--;
                        while (k < l && nums[k] == nums[k - 1]) k++;
                        while (k < l && nums[l] == nums[l + 1]) l--;
                    }
                }
            }
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
