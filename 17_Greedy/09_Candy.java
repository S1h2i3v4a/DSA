/**
 * Problem Name: Candy
 * Platform: LeetCode (LC 135)
 * Difficulty: Hard
 * 
 * Time Complexity: O(N) two passes through ratings array
 * Space Complexity: O(N) to store candies assigned to each child
 * 
 * Approach:
 * Two-Pass Greedy:
 * 1. Initialize candies array of size N with 1 candy for each child.
 * 2. Left-to-Right Pass: If ratings[i] > ratings[i-1], candies[i] = candies[i-1] + 1.
 * 3. Right-to-Left Pass: If ratings[i] > ratings[i+1], candies[i] = Math.max(candies[i], candies[i+1] + 1).
 * 4. Sum up all candies in the array.
 */

import java.util.*;

class Candy {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);

        // Left to Right
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Right to Left
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int totalCandies = 0;
        for (int c : candies) {
            totalCandies += c;
        }

        return totalCandies;
    }

    public static void main(String[] args) {
        Candy solver = new Candy();
        int[] ratings1 = {1, 0, 2};
        System.out.println("Total candies for [1, 0, 2]: " + solver.candy(ratings1)); // Expected: 5

        int[] ratings2 = {1, 2, 2};
        System.out.println("Total candies for [1, 2, 2]: " + solver.candy(ratings2)); // Expected: 4
    }
}
