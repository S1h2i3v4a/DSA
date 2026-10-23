/*QUESTION:
Given an integer array nums that may contain duplicates, return all possible subsets (the power set). The solution set must not contain duplicate subsets. Return the solution in any order.

Example:
Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
Example:
Input: nums = [0]
Output: [[],[0]]

Approach:
To find all possible subsets without duplicates, we can use a recursive backtracking approach.
1. Sort the input array nums in non-decreasing order to handle duplicates.
2. Create a helper function called 'solve' that takes the current index, the array nums, a temporary vector to store the current subset, and a vector of vector to store all subsets as parameters.
3. In the 'solve' function:
   - Add the current subset to the vector of subsets.
   - Iterate from the current index to the end of the array:
     - If the current index is greater than the starting index and the current element is the same as the previous element, skip the iteration to avoid duplicates.
     - Add the current element to the current subset.
     - Recursively call the 'solve' function with the updated index, the array nums, the current subset, and the vector of subsets.
     - Remove the last added element from the current subset (backtracking).
4. Create the main function called 'subsetsWithDup' that calculates and returns the vector of subsets without duplicates.
   - Sort the input array nums.
   - Initialize an empty vector to store the subsets.
   - Call the 'solve' function with the initial index as 0, the array nums, an empty temporary vector, and the vector of subsets.
   - Return the vector of subsets.

Time Complexity: O(2^N), where N is the size of the input array nums. This is because there are 2^N possible subsets.
Space Complexity: O(N), where N is the size of the input array nums. This is the space required to store the subsets.

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) continue;
            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
