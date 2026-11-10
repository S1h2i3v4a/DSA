/*
QUESTION:
Replace each element with its rank in O(N log N) using sorting and HashMap.

APPROACH:
- Clone `arr` to `sortedArr` and sort `sortedArr`.
- Use a HashMap to map each unique value to its 1-based rank.
- Replace elements in `arr` using the rank from the map.

TIME COMPLEXITY: O(N log N) for sorting.
SPACE COMPLEXITY: O(N) for sorted array and HashMap.
*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);

        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        for (int val : sortedArr) {
            if (!rankMap.containsKey(val)) {
                rankMap.put(val, rank++);
            }
        }

        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = rankMap.get(arr[i]);
        }
        return result;
    }
}
