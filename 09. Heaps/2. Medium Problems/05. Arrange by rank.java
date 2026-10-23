/*
QUESTION:
Given an array of integers arr, replace each element with its rank.

APPROACH:
To assign ranks to the elements in the array, we can use a min-heap (priority queue) to sort the elements in ascending order along with their indices.
1. Create a min-heap to store pairs of (element, index) in ascending order.
2. Push each element along with its index into the min-heap.
3. Initialize the rank as 0 and the previous element as INT_MIN (an arbitrary value that never matches any element).
4. While the min-heap is not empty, extract the minimum element from the min-heap.
5. If the current element is different from the previous element, increment the rank.
6. Assign the rank to the element at its corresponding index in the result array.
7. Repeat steps 4-6 until all elements are processed.

The arrayRankTransform function implements this approach. It uses a min-heap to assign ranks to the elements in the array.

TIME COMPLEXITY:
The time complexity of the arrayRankTransform function is O(N log N), where N is the size of the array. Inserting elements into the min-heap and extracting the minimum element take O(log N) time, and we do this for N elements. The overall time complexity is dominated by the heap operations.

SPACE COMPLEXITY:
The space complexity is O(N) as we need to store N elements in the min-heap and the result array.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        Map<Integer, Integer> rank = new HashMap<>();
        for (int x : sorted) rank.putIfAbsent(x, rank.size() + 1);
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) ans[i] = rank.get(arr[i]);
        return ans;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
