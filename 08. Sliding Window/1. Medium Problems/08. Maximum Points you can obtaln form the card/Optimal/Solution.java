/*
QUESTION:
Return the maximum score you can obtain by taking exactly `k` cards from either the beginning or the end.

APPROACH:
- Initially take all `k` cards from the left: compute `leftSum`.
- Set `maxPoints = leftSum`.
- Gradually remove 1 card from the left and add 1 card from the right:
  - For `i` from 1 to `k`:
    - `leftSum -= cardPoints[k - i]`
    - `rightSum += cardPoints[n - i]`
    - `maxPoints = Math.max(maxPoints, leftSum + rightSum)`

TIME COMPLEXITY: O(K) initial sum of k cards + k slide iterations.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public static int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int leftSum = 0;

        for (int i = 0; i < k; i++) {
            leftSum += cardPoints[i];
        }

        int maxPoints = leftSum;
        int rightSum = 0;

        for (int i = 1; i <= k; i++) {
            leftSum -= cardPoints[k - i];
            rightSum += cardPoints[n - i];
            maxPoints = Math.max(maxPoints, leftSum + rightSum);
        }
        return maxPoints;
    }
}
