/*
QUESTION:
Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day in amortized O(1) time.

APPROACH:
- Use a monotonic decreasing stack storing pairs of `(price, span)`.
- When `next(price)` is called:
  - Initialize `span = 1`.
  - While stack is non-empty and `stack.peek().price <= price`:
    - Add `stack.pop().span` to `span`.
  - Push `(price, span)` onto stack.
  - Return `span`.

TIME COMPLEXITY: Amortized O(1) per call, since each price is pushed and popped at most once.
SPACE COMPLEXITY: O(N) for stack storage.
*/

import java.util.Stack;

class Solution {
    private static class StockPair {
        int price;
        int span;
        StockPair(int price, int span) {
            this.price = price;
            this.span = span;
        }
    }

    private Stack<StockPair> stack;

    public Solution() {
        stack = new Stack<>();
    }

    public int next(int price) {
        int span = 1;
        while (!stack.isEmpty() && stack.peek().price <= price) {
            span += stack.pop().span;
        }
        stack.push(new StockPair(price, span));
        return span;
    }
}
