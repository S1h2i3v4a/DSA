/*
QUESTION:
Minimum candies in O(N) time and O(1) space using the Slope (Peak and Valley) approach.

APPROACH:
- Track peak count and down count as we traverse the ratings slope:
  - If increasing (`ratings[i] > ratings[i - 1]`): `peak++`, reset `down = 0`, add `peak` to sum.
  - If equal: reset `peak = 1`, `down = 0`, add 1 to sum.
  - If decreasing: `down++`, add `down` to sum. If `down >= peak`, add extra 1 for the peak.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        if (n == 0) return 0;

        int total = 1;
        int peak = 1;
        int down = 0;

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                peak++;
                down = 0;
                total += peak;
            } else if (ratings[i] == ratings[i - 1]) {
                peak = 1;
                down = 0;
                total += 1;
            } else {
                down++;
                total += down;
                if (down >= peak) {
                    total++;
                }
            }
        }
        return total;
    }
}
