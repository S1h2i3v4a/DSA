/*
QUESTION:
Minimum candies using two-pass approach.

APPROACH:
- Left-to-right pass: if `ratings[i] > ratings[i - 1]`, `left[i] = left[i - 1] + 1`, else 1.
- Right-to-left pass: if `ratings[i] > ratings[i + 1]`, `right[i] = right[i + 1] + 1`, else 1.
- Final candy for child `i` is `Math.max(left[i], right[i])`.

TIME COMPLEXITY: O(N) two linear passes.
SPACE COMPLEXITY: O(N) for candy arrays.
*/

import java.util.Arrays;

class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }

        int total = 0;
        for (int i = 0; i < n; i++) {
            total += Math.max(left[i], right[i]);
        }
        return total;
    }
}
