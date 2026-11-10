/*
QUESTION:
Given `K` sorted arrays of size `K` each, merge them into a single sorted array.

APPROACH:
- Collect all elements from all `K` arrays into an `ArrayList`.
- Sort the entire list using `Collections.sort()`.

TIME COMPLEXITY: O((K^2) log(K^2)) = O(N log N) where N = K^2.
SPACE COMPLEXITY: O(K^2) to hold all merged elements.
*/

import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public static ArrayList<Integer> mergeKArrays(int[][] arr, int K) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                result.add(arr[i][j]);
            }
        }
        Collections.sort(result);
        return result;
    }
}
