/*
QUESTION:
Given an array `A` of integers of length `n`, find the nearest smaller number for every element such that the smaller element is on the left side.
If no smaller element is present on the left, assign -1.

APPROACH:
- For each element `A.get(i)`, scan indices `j = i - 1` down to `0`.
- Find the first element strictly smaller than `A.get(i)`.
- If none exists, assign -1.

TIME COMPLEXITY: O(N^2) due to nested loops.
SPACE COMPLEXITY: O(1) auxiliary space.
*/

import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> prevSmaller(ArrayList<Integer> A) {
        int n = A.size();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int smaller = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (A.get(j) < A.get(i)) {
                    smaller = A.get(j);
                    break;
                }
            }
            result.add(smaller);
        }
        return result;
    }
}
