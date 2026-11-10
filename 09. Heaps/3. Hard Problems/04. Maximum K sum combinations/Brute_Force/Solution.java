/*
QUESTION:
Given two integer arrays `A` and `B` of size `N` each. A sum combination is made by adding one element from array A and another element from array B.
Return the maximum `K` valid sum combinations sorted in non-increasing order.

APPROACH:
- Compute all `N * N` possible sum combinations `A[i] + B[j]`.
- Sort all combinations in descending order.
- Return the first `K` elements.

TIME COMPLEXITY: O(N^2 log(N^2)) = O(N^2 log N).
SPACE COMPLEXITY: O(N^2) to hold all sums.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<Integer> maxCombinations(int N, int K, int[] A, int[] B) {
        List<Integer> allSums = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                allSums.add(A[i] + B[j]);
            }
        }
        allSums.sort(Collections.reverseOrder());
        return allSums.subList(0, Math.min(K, allSums.size()));
    }
}
