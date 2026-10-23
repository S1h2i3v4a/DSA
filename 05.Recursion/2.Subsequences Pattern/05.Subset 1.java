/*QUESTION:
Given a list arr of N integers, print the sums of all subsets in it.

Example:
Input:
N = 2
arr[] = {2, 3}
Output:
0 2 3 5
Explanation:
When no element is taken, the sum is 0.
When only 2 is taken, the sum is 2.
When only 3 is taken, the sum is 3.
When elements 2 and 3 are taken, the sum is 2 + 3 = 5.

Approach:
To print the sums of all subsets, we can use a recursive approach with backtracking.
1. Create a helper function called 'solve' that takes the current index, the current sum, the array, and a vector to store the sums as parameters.
2. In the 'solve' function:
   - If the current index is equal to the size of the array, add the current sum to the vector of sums and return.
   - Otherwise, do the following:
     - Include the element at the current index in the sum by recursively calling the 'solve' function with the updated index and the current sum plus the element value.
     - Exclude the element at the current index from the sum by recursively calling the 'solve' function with the updated index and the current sum.
3. Create the main function called 'subsetSums' that calculates and returns the vector of subset sums.
   - Initialize an empty vector to store the subset sums.
   - Call the 'solve' function with the initial index as 0, the initial sum as 0, the array, and the vector of subset sums.
   - Sort the vector of subset sums in ascending order.
   - Return the vector of subset sums.

Time Complexity: O(2^N), where N is the size of the array.
Space Complexity: O(N), where N is the size of the array (for recursion stack and storing subset sums).

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int n) {
        ArrayList<Integer> sumList = new ArrayList<>();
        calculateSubsetSums(arr, 0, 0, sumList);
        Collections.sort(sumList);
        return sumList;
    }

    private void calculateSubsetSums(ArrayList<Integer> arr, int index, int currentSum, ArrayList<Integer> sumList) {
        if (index == arr.size()) {
            sumList.add(currentSum);
            return;
        }

        calculateSubsetSums(arr, index + 1, currentSum + arr.get(index), sumList);
        calculateSubsetSums(arr, index + 1, currentSum, sumList);
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
