/*
QUESTION:
You are visiting a farm that has a single row of fruit trees arranged from left to right.
You only have two baskets, and each basket can only hold a single type of fruit.
Return the maximum total number of fruits you can pick.

APPROACH:
- Check all subarrays `fruits[i...j]`.
- Count unique fruit types using a Set.
- If size of Set <= 2, update `maxFruits = Math.max(maxFruits, j - i + 1)`.

TIME COMPLEXITY: O(N^2) inspecting all subarrays.
SPACE COMPLEXITY: O(1) Set holds at most 3 distinct values.
*/

import java.util.HashSet;
import java.util.Set;

class Solution {
    public static int totalFruit(int[] fruits) {
        int n = fruits.length;
        int maxFruits = 0;

        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(fruits[j]);
                if (set.size() <= 2) {
                    maxFruits = Math.max(maxFruits, j - i + 1);
                } else {
                    break;
                }
            }
        }
        return maxFruits;
    }
}
