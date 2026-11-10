/*
QUESTION:-
Given a list arr of N integers, return sums of all subsets in it. Return the sums in increasing order.

Example:
Input: N = 2, arr = [2, 3]
Output: 0 2 3 5
*/

/*
APPROACH:-
Bitmasking: Iterate through 0 to 2^n - 1. For each mask, compute sum of elements having corresponding set bits, add to list, and sort.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        int total = 1 << n;
        for (int i = 0; i < total; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    sum += arr.get(j);
                }
            }
            ans.add(sum);
        }
        Collections.sort(ans);
        return ans;
    }
}

// TIME COMPLEXITY = O(2^N * N + 2^N log(2^N))
// SPACE COMPLEXITY = O(1) auxiliary
