/*
QUESTION:
Given weights and values of N items, we need to put these items in a knapsack of capacity W to get the maximum total value.
Items can be broken (fractional knapsack).

APPROACH:
- Sort items by their value-to-weight ratio in descending order (`value / weight`).
- Iterate through sorted items:
  - If item weight <= remaining capacity W: take full item, `loot += item.value`, `W -= item.weight`.
  - Else: take fraction `((double) W / item.weight) * item.value`, and break loop (capacity full).
- Return total loot.

TIME COMPLEXITY: O(N log N) for sorting items by ratio.
SPACE COMPLEXITY: O(1) auxiliary space.
*/

import java.util.Arrays;

class Item {
    int value, weight;
    Item(int x, int y) {
        this.value = x;
        this.weight = y;
    }
}

class Solution {
    public double fractionalKnapsack(int w, Item[] arr, int n) {
        Arrays.sort(arr, (a, b) -> {
            double r1 = (double) a.value / (double) a.weight;
            double r2 = (double) b.value / (double) b.weight;
            return Double.compare(r2, r1);
        });

        double loot = 0.0;
        int remainingCapacity = w;

        for (int i = 0; i < n; i++) {
            if (arr[i].weight <= remainingCapacity) {
                loot += arr[i].value;
                remainingCapacity -= arr[i].weight;
            } else {
                loot += ((double) remainingCapacity / arr[i].weight) * arr[i].value;
                break;
            }
        }
        return loot;
    }
}
