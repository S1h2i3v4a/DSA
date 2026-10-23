/*QUESTION:
Given a circular integer array `nums` (i.e., the next element of `nums[nums.length - 1]` is `nums[0]`), return the next greater number for every element in `nums`.

The next greater number of a number `x` is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.

Example:

Input: `nums = [1,2,1]`
Output: `[2,-1,2]`
Explanation: The first 1's next greater number is 2. The number 2 doesn't have a next greater number. The second 1's next greater number needs to be searched circularly, which is also 2.

APPROACH:
To find the next greater number for each element in a circular array, we can utilize a stack. 
We iterate through the array in reverse order to handle the circular nature of the array. 
For each element, we compare it with the elements in the stack. 
If an element in the stack is smaller than or equal to the current element, we pop it from the stack since it cannot be the next greater number.
The top of the stack at each iteration will hold the next greater element for the corresponding element in the array.

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Next_Greater_Element_II {

    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }

            if (i < n) {
                result[i] = stack.isEmpty() ? -1 : stack.peek();
            }

            stack.push(nums[i % n]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
        int[] nge = nextGreaterElements(nums);
        System.out.println("Input Circular Array: " + Arrays.toString(nums));
        System.out.println("Next Greater Elements II: " + Arrays.toString(nge));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
