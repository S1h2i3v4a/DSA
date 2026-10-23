/*QUESTION:
You are given an integer array nums. The range of a subarray of nums is the difference between the largest and smallest element in the subarray. Return the sum of all subarray ranges of nums.

APPROACH:
To find the sum of all subarray ranges, we can use the concept of previous smaller, next smaller, previous greater, and next greater elements for each element in the array.
1. Define four helper functions:
   - `prevSmaller(arr)`: Returns an array that contains the index of the previous smaller element for each element in `arr`. If no smaller element exists, the index is set to -1.
   - `nextSmaller(arr)`: Returns an array that contains the index of the next smaller element for each element in `arr`. If no smaller element exists, the index is set to the length of the array.
   - `prevGreater(arr)`: Returns an array that contains the index of the previous greater element for each element in `arr`. If no greater element exists, the index is set to -1.
   - `nextGreater(arr)`: Returns an array that contains the index of the next greater element for each element in `arr`. If no greater element exists, the index is set to the length of the array.
2. Initialize a variable `ans` to 0 to store the final sum.
3. Iterate over each element `arr[i]` in the array:
   a. Calculate the number of subarrays where `arr[i]` is the minimum element:
      - The number of subarrays with `arr[i]` as the minimum element on the left side is `i - prevS[i]`.
      - The number of subarrays with `arr[i]` as the minimum element on the right side is `nextS[i] - i`.
   b. Calculate the number of subarrays where `arr[i]` is the maximum element:
      - The number of subarrays with `arr[i]` as the maximum element on the left side is `i - prevG[i]`.
      - The number of subarrays with `arr[i]` as the maximum element on the right side is `nextG[i] - i`.
   c. Update `ans` by adding `(maxleftElements * maxrightElements * arr[i]) - (minleftElements * minrightElements * arr[i])`.
4. Return the final value of `ans` as the sum of all subarray ranges.

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.ArrayDeque;
import java.util.Deque;

class Sum_Of_Subarray_Ranges {

    public static long subArrayRanges(int[] nums) {
        return sumSubarrayMaxs(nums) - sumSubarrayMins(nums);
    }

    private static long sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (long) left[i] * right[i] * arr[i];
        }
        return sum;
    }

    private static long sumSubarrayMaxs(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (long) left[i] * right[i] * arr[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println("Sum of Subarray Ranges: " + subArrayRanges(nums));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
