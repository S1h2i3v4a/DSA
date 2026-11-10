/*
QUESTION:
The median is the middle value in an ordered integer list.
Design a data structure that supports `addNum(int num)` and `findMedian()`.

APPROACH:
- Maintain a list in sorted order by using binary search / insertion sort on `addNum(num)`.
- On `findMedian()`:
  - If odd size: return element at `size / 2`.
  - If even size: return average of `(size / 2 - 1)` and `(size / 2)`.

TIME COMPLEXITY:
- addNum: O(N) due to insertion into list.
- findMedian: O(1).
SPACE COMPLEXITY: O(N) to store numbers.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    private List<Integer> list;

    public Solution() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        int idx = Collections.binarySearch(list, num);
        if (idx < 0) idx = -(idx + 1);
        list.add(idx, num);
    }

    public double findMedian() {
        int n = list.size();
        if (n % 2 != 0) {
            return list.get(n / 2);
        } else {
            return (list.get(n / 2 - 1) + list.get(n / 2)) / 2.0;
        }
    }
}
