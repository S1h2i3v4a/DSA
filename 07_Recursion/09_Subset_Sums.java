/*
 * Problem Name: Subset Sums
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * Time Complexity: O(2^N)
 * Space Complexity: O(2^N)
 */

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
