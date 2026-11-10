/*
QUESTION:-
Given an integer array of size n, find all elements that appear more than floor(n/3) times.

Example:
Input: nums = [3,2,3]
Output: [3]
*/

/*
APPROACH:-
Iterate through each distinct element, count its occurrences across array with an inner loop. Add to list if frequency > n / 3.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (ans.contains(nums[i])) continue;
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (nums[i] == nums[j]) count++;
            }
            if (count > n / 3) ans.add(nums[i]);
            if (ans.size() == 2) break;
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1) auxiliary
