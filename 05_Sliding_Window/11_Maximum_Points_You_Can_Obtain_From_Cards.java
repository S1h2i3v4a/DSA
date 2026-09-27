/**
 * Problem Name: Maximum Points You Can Obtain from Cards
 * Platform: LeetCode (1423)
 * Difficulty: Medium
 * 
 * Time Complexity: O(K) where K is number of cards to pick.
 * Space Complexity: O(1) auxiliary space.
 * 
 * Approach:
 * Circular Prefix-Suffix Window.
 * 1. Compute initial sum of first `k` cards from left (`leftSum`).
 * 2. Shift window by removing 1 card from left and adding 1 card from right (`rightSum`).
 * 3. Track maximum sum `maxPoints = max(maxPoints, leftSum + rightSum)`.
 */

class Maximum_Points_You_Can_Obtain_From_Cards {

    public static int maxScore(int[] cardPoints, int k) {
        int leftSum = 0;
        int rightSum = 0;
        int maxPoints = 0;

        for (int i = 0; i < k; i++) {
            leftSum += cardPoints[i];
        }

        maxPoints = leftSum;

        int rightIndex = cardPoints.length - 1;
        for (int i = k - 1; i >= 0; i--) {
            leftSum -= cardPoints[i];
            rightSum += cardPoints[rightIndex--];
            maxPoints = Math.max(maxPoints, leftSum + rightSum);
        }

        return maxPoints;
    }

    public static void main(String[] args) {
        int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;
        System.out.println("Maximum Score from Cards: " + maxScore(cardPoints, k));
    }
}
