/*
Question:
You are given an array of integers nums and an integer threshold. We need to find the smallest divisor
such that the result of dividing each element of the array by the divisor and summing up the results
is less than or equal to the threshold.

Example:
Input: nums = [1,2,5,9], threshold = 6
Output: 5
Explanation: We can get a sum of 17 (1+2+5+9) if the divisor is 1.
If the divisor is 4, we can get a sum of 7 (1+1+2+3).
If the divisor is 5, the sum will be 5 (1+1+1+2).
The smallest divisor that gives a sum less than or equal to the threshold is 5.

Approach:
- Start with a range of possible divisors from 1 to the maximum value in the array.
- Use binary search to find the smallest divisor that satisfies the given condition.
- Check the midpoint of the range and calculate the sum of divisions using the current divisor.
- If the sum is less than or equal to the threshold, update the answer and continue searching in the lower half of the range.
- If the sum is greater than the threshold, search in the upper half of the range.
- Repeat this process until the range is narrowed down to a single value, which will be the smallest divisor.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1, high = 0;
        for (int num : nums) high = Math.max(high, num);
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (computeSum(nums, mid) <= threshold) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private int computeSum(int[] nums, int divisor) {
        int sum = 0;
        for (int num : nums) {
            sum += (num + divisor - 1) / divisor;
        }
        return sum;
    }
}

// TIME COMPLEXITY = O(n log(max(nums)))
// SPACE COMPLEXITY = O(1)
