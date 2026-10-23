/*
Question:
Given weights and values of N items, we need to put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
Note: Unlike 0/1 knapsack, you are allowed to break the item.

Example:
Input:
N = 3, W = 50
values[] = {60, 100, 120}
weight[] = {10, 20, 30}
Output:
240.00
Explanation: Total maximum value of items
we can have is 240.00 from the given
capacity of the sack.

Approach:
1. We will define a comparator function that compares items based on their value/weight ratio in descending order.
2. We will sort the items array based on the comparator function.
3. Initialize a variable loot to keep track of the total value in the knapsack.
4. Iterate over the items array, starting from the item with the highest value/weight ratio.
5. If the weight of the current item is greater than the remaining capacity W, we take a fraction of the item to fill the remaining capacity.
   The fraction is determined by the remaining capacity divided by the weight of the current item.
6. Add the fraction of the item's value to the loot.
7. Reduce the remaining capacity by the weight of the item.
8. Repeat steps 5-7 until the remaining capacity becomes zero or all items are considered.
9. Finally, return the loot, which represents the maximum total value in the knapsack.

Complexity Analysis:
- The sorting step takes O(n log n) time, where n is the number of items.
- The iteration over the items takes O(n) time.
- Therefore, the overall time complexity is O(n log n).

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
