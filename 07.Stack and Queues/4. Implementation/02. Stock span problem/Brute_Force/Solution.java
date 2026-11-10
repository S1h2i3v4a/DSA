/*
QUESTION:
Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.
The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and going backward)
for which the stock price was less than or equal to the price of that day.

APPROACH:
- Store all incoming prices in a dynamic list.
- On each call `next(price)`:
  - Add `price` to list.
  - Iterate backwards from current day, counting consecutive days with price <= today's price.
  - Return the count.

TIME COMPLEXITY: O(N) per call, leading to O(N^2) total across N calls.
SPACE COMPLEXITY: O(N) to store list of all prices.
*/

import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<Integer> prices;

    public Solution() {
        prices = new ArrayList<>();
    }

    public int next(int price) {
        prices.add(price);
        int span = 0;
        for (int i = prices.size() - 1; i >= 0; i--) {
            if (prices.get(i) <= price) {
                span++;
            } else {
                break;
            }
        }
        return span;
    }
}
