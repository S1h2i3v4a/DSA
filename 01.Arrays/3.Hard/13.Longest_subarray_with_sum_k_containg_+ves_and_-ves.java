/*Question:
Given an array containing N integers and an integer K, find the length of the longest subarray with the sum of the elements equal to K.

Example:
Input:
A[] = {10, 5, 2, 7, 1, 9}
K = 15
Output:
4
Explanation:
The sub-array is {5, 2, 7, 1}.

Approach:
To solve this problem, we can use a prefix sum approach along with a hashmap to keep track of the prefix sums encountered so far. We iterate through the array and maintain a prefix sum variable. At each index, we check if the prefix sum equals K, in which case we update the maximum length of the subarray found so far. Additionally, we check if the current prefix sum minus K exists in the hashmap. If it does, it means there is a subarray between the previous occurrence of the prefix sum minus K and the current index that sums up to K. We update the maximum length accordingly. We store the prefix sums and their corresponding indices in the hashmap.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static int lenOfLongSubarr(int A[], int N, int K) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxLen = 0, sum = 0;
        for (int i = 0; i < N; i++) {
            sum += A[i];
            if (sum == K) maxLen = i + 1;
            if (!map.containsKey(sum)) map.put(sum, i);
            if (map.containsKey(sum - K)) {
                maxLen = Math.max(maxLen, i - map.get(sum - K));
            }
        }
        return maxLen;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
