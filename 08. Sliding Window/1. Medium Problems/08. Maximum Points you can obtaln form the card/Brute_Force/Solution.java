/*
QUESTION:
There are several cards arranged in a row, and each card has an associated number of points.
In one step, you can take one card from the beginning or from the end of the row. You have to take exactly `k` cards.
Your score is the sum of the points of the cards you have taken. Return the maximum score you can obtain.

APPROACH:
- Try all possible choices: take `i` cards from the left and `k - i` cards from the right (where `0 <= i <= k`).
- Compute sum for each choice and track maximum.

TIME COMPLEXITY: O(K^2) or O(N) computing prefix and suffix sums for all i from 0 to k.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public static int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int maxPoints = 0;

        for (int leftCount = 0; leftCount <= k; leftCount++) {
            int rightCount = k - leftCount;
            int currentSum = 0;

            for (int i = 0; i < leftCount; i++) {
                currentSum += cardPoints[i];
            }
            for (int i = 0; i < rightCount; i++) {
                currentSum += cardPoints[n - 1 - i];
            }
            maxPoints = Math.max(maxPoints, currentSum);
        }
        return maxPoints;
    }
}
