/**
 * Problem Name: Fractional Knapsack
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(N log N) due to sorting items by value/weight ratio
 * Space Complexity: O(N) to store item objects for sorting
 * 
 * Approach:
 * 1. Calculate value-to-weight ratio for each item (value / weight).
 * 2. Sort items in descending order of their value-to-weight ratio.
 * 3. Iterate through sorted items:
 *    - If remaining capacity >= item weight: take full item.
 *    - Else: take fraction of item = (remaining capacity / item weight) * item value, then stop.
 */

import java.util.*;

class Fractional_Knapsack {
    static class Item {
        int value;
        int weight;

        Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    public double fractionalKnapsack(int w, Item[] arr, int n) {
        // Sort items by value-to-weight ratio in descending order
        Arrays.sort(arr, (a, b) -> {
            double r1 = (double) a.value / (double) a.weight;
            double r2 = (double) b.value / (double) b.weight;
            return Double.compare(r2, r1);
        });

        double totalValue = 0.0;
        int currentWeight = 0;

        for (int i = 0; i < n; i++) {
            if (currentWeight + arr[i].weight <= w) {
                currentWeight += arr[i].weight;
                totalValue += arr[i].value;
            } else {
                int remain = w - currentWeight;
                totalValue += ((double) arr[i].value / (double) arr[i].weight) * (double) remain;
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Fractional_Knapsack solver = new Fractional_Knapsack();
        Item[] arr = {
            new Item(60, 10),
            new Item(100, 20),
            new Item(120, 30)
        };
        int w = 50;
        int n = arr.length;
        System.out.printf("Maximum value in Knapsack = %.2f\n", solver.fractionalKnapsack(w, arr, n)); // Expected: 240.00
    }
}
