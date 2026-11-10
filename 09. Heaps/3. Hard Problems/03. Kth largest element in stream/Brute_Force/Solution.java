/*
QUESTION:
Design a class to find the `k`th largest element in a stream. Note that it is the `k`th largest element in the sorted order, not the `k`th distinct element.
Implement `KthLargest` with constructor and `add(int val)`.

APPROACH:
- Maintain an `ArrayList<Integer>` of all received numbers.
- When `add(val)` is called:
  - Add `val` to list.
  - Sort list in descending order.
  - Return `list.get(k - 1)`.

TIME COMPLEXITY: O(N log N) per `add` call.
SPACE COMPLEXITY: O(N) to store stream elements.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    private int k;
    private List<Integer> stream;

    public Solution(int k, int[] nums) {
        this.k = k;
        this.stream = new ArrayList<>();
        for (int x : nums) stream.add(x);
    }

    public int add(int val) {
        stream.add(val);
        stream.sort(Collections.reverseOrder());
        return stream.get(k - 1);
    }
}
