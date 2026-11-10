/*
QUESTION:
There are `n` children standing in a line. Each child is assigned a rating value given in the integer array `ratings`.
Each child must have at least one candy. Children with a higher rating get more candies than their neighbors.
Return the minimum number of candies you need to have to distribute the candies to the children.

APPROACH:
- Initialize each child's candy count to 1.
- Continuously sweep through array:
  - If `ratings[i] > ratings[i - 1]` and `candies[i] <= candies[i - 1]`, set `candies[i] = candies[i - 1] + 1`.
  - If `ratings[i] > ratings[i + 1]` and `candies[i] <= candies[i + 1]`, set `candies[i] = candies[i + 1] + 1`.
- Repeat until no candy updates occur.

TIME COMPLEXITY: O(N^2) in worst case (strictly decreasing sequence).
SPACE COMPLEXITY: O(N) for candies array.
*/

import java.util.Arrays;

class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);

        boolean hasChanged = true;
        while (hasChanged) {
            hasChanged = false;
            for (int i = 0; i < n; i++) {
                if (i > 0 && ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                    hasChanged = true;
                }
                if (i < n - 1 && ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                    hasChanged = true;
                }
            }
        }

        int total = 0;
        for (int c : candies) total += c;
        return total;
    }
}
