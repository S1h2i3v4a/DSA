/*
QUESTION:
Given an array of integers `arr`, replace each element with its rank.
The rank represents how large the element is. The rank has the following rules:
- Rank is an integer starting from 1.
- The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
- Rank should be as small as possible.

APPROACH:
- For each element `arr[i]`, find how many distinct elements in the array are strictly smaller than `arr[i]`.
- Rank is `1 + count_of_distinct_smaller_elements`.

TIME COMPLEXITY: O(N^2) comparing each element with unique elements of array.
SPACE COMPLEXITY: O(N) for set of distinct values.
*/

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Set<Integer> unique = new HashSet<>();
        for (int x : arr) unique.add(x);

        for (int i = 0; i < n; i++) {
            int smallerCount = 0;
            for (int val : unique) {
                if (val < arr[i]) smallerCount++;
            }
            result[i] = smallerCount + 1;
        }
        return result;
    }
}
