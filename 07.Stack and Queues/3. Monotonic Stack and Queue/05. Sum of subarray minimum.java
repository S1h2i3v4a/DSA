/*QUESTION:
Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.

APPROACH:
To find the sum of the minimums of all subarrays, we can use the concept of previous smaller and next smaller elements for each element in the array.
1. Define two helper functions:
   - `prevSmaller(arr)`: Returns an array that contains the index of the previous smaller element for each element in `arr`. If no smaller element exists, the index is set to -1.
   - `nextSmaller(arr)`: Returns an array that contains the index of the next smaller element for each element in `arr`. If no smaller element exists, the index is set to the length of the array.
2. Initialize a variable `ans` to 0 to store the final sum.
3. Iterate over each element `arr[i]` in the array:
   a. Calculate the number of subarrays in which `arr[i]` is the minimum element.
      - The number of subarrays with `arr[i]` as the minimum element on the left side is `i - prevS[i]`.
      - The number of subarrays with `arr[i]` as the minimum element on the right side is `nextS[i] - i`.
   b. Add `(leftElements * rightElements * arr[i]) % mod` to `ans`, where `mod` is `1e9 + 7`.
4. Return the final value of `ans` as the sum of minimums of all subarrays modulo `1e9 + 7`.

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.ArrayDeque;
import java.util.Deque;

class Sum_Of_Subarray_Minimums {

    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        long MOD = 1_000_000_007;

        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        // Calculate PLE (Previous Less Element)
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();

        // Calculate NSE (Next Less or Equal Element)
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            long count = ((long) left[i] * right[i]) % MOD;
            totalSum = (totalSum + (count * arr[i]) % MOD) % MOD;
        }

        return (int) totalSum;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4};
        System.out.println("Sum of Subarray Minimums: " + sumSubarrayMins(arr));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
