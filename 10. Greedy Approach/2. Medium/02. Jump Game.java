/*
Question:
You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
Return true if you can reach the last index, or false otherwise.

Example:
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Approach:
- We maintain a variable 'farthest' to keep track of the farthest position we can reach.
- We iterate over the array from left to right.
- At each position, we check if the current position is reachable from the previous farthest position.
- If the current position is beyond the farthest position, it means we cannot reach the end, so we return false.
- Otherwise, we update the farthest position by taking the maximum of the current farthest and the current position plus the maximum jump length at that position.
- Finally, if we reach the end of the array or beyond it, we return true.

Complexity Analysis:
- The time complexity of this approach is O(N), where N is the length of the array nums.
- We iterate over the array once to update the farthest position.
- The space complexity is O(1) as we use only a constant amount of extra space.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Jump_Game {
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i > maxReach) {
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= n - 1) {
                return true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Jump_Game solver = new Jump_Game();
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Can jump [2, 3, 1, 1, 4]: " + solver.canJump(nums1)); // true

        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("Can jump [3, 2, 1, 0, 4]: " + solver.canJump(nums2)); // false
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
