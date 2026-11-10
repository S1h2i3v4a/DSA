/*
QUESTION:-
Given a list arr of N integers, return sums of all subsets in it. Return the sums in increasing order.

Example:
Input: N = 2, arr = [2, 3]
Output: 0 2 3 5
*/

/*
APPROACH:-
Recursion (Pick and Don't Pick): At each index, recurse by adding arr.get(idx) to running sum, and recurse without adding it. Sort final result list.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int n) {
        ArrayList<Integer> sumList = new ArrayList<>();
        findSums(0, 0, arr, n, sumList);
        Collections.sort(sumList);
        return sumList;
    }

    private void findSums(int ind, int sum, ArrayList<Integer> arr, int n, ArrayList<Integer> sumList) {
        if (ind == n) {
            sumList.add(sum);
            return;
        }
        findSums(ind + 1, sum + arr.get(ind), arr, n, sumList);
        findSums(ind + 1, sum, arr, n, sumList);
    }
}

// TIME COMPLEXITY = O(2^N + 2^N log(2^N))
// SPACE COMPLEXITY = O(N)
