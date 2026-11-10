/*
QUESTION:
Given an array `arr` of size `N`, find the next greater element for each element of the array in order of their appearance.
If no greater element exists, output -1.

APPROACH:
- For each element at index `i`, iterate from `i + 1` to `N - 1`.
- Find the first element strictly greater than `arr[i]`.
- If none found, assign -1.

TIME COMPLEXITY: O(N^2) due to nested loops.
SPACE COMPLEXITY: O(1) auxiliary space (excluding result array).
*/

class Solution {
    public static int[] nextGreaterElement(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = -1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    result[i] = arr[j];
                    break;
                }
            }
        }
        return result;
    }
}
