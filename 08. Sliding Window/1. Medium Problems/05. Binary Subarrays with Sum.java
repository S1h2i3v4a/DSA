/*QUESTION:
Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum equal to the goal.

Example 1:
Input: nums = [1,0,1,0,1], goal = 2
Output: 4
Explanation: The 4 subarrays are bolded and underlined below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]

APPROACH:
1. Create an unordered_map mp to store the prefix sum and its frequency.
2. Initialize a variable prefSum to keep track of the prefix sum.
3. Initialize a variable cnt to keep track of the count of subarrays with a sum equal to the goal.
4. Iterate through each element nums[i] in the array:
   - Update prefSum by adding nums[i].
   - Check if prefSum is equal to the goal, if so, increment cnt.
   - Check if the difference between prefSum and goal (prefSum - goal) exists in the map.
     - If it exists, increment cnt by the frequency of (prefSum - goal) in the map, as this would mean we have found subarrays with a sum equal to the goal.
   - Increment the frequency of prefSum in the map.
5. Return cnt, which represents the number of non-empty subarrays with a sum equal to the goal.

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
class Binary_Subarrays_With_Sum {

    public static int numSubarraysWithSum(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    private static int atMost(int[] nums, int k) {
        if (k < 0) return 0;
        int left = 0;
        int sum = 0;
        int count = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum > k) {
                sum -= nums[left];
                left++;
            }

            count += (right - left + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 0, 1};
        int goal = 2;
        System.out.println("Binary Subarrays with Sum " + goal + ": " + numSubarraysWithSum(nums, goal));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
