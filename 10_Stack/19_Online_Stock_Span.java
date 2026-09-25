/**
 * Problem Name: Online Stock Span
 * Platform: LeetCode (901)
 * Difficulty: Medium
 * 
 * Time Complexity: O(1) amortized per next() call.
 * Space Complexity: O(N) for monotonic stack storage.
 * 
 * Approach:
 * Monotonic Decreasing Stack storing (price, span) pairs.
 * For each new price:
 * - Initialize `span = 1`.
 * - While `stack.peek().price <= price`, pop and add `span += stack.pop().span`.
 * - Push `Pair(price, span)` onto stack and return `span`.
 */

import java.util.ArrayDeque;
import java.util.Deque;

class Online_Stock_Span {

    private static class Pair {
        int price;
        int span;

        Pair(int price, int span) {
            this.price = price;
            this.span = span;
        }
    }

    private Deque<Pair> stack;

    public Online_Stock_Span() {
        stack = new ArrayDeque<>();
    }

    public int next(int price) {
        int span = 1;

        while (!stack.isEmpty() && stack.peek().price <= price) {
            span += stack.pop().span;
        }

        stack.push(new Pair(price, span));
        return span;
    }

    public static void main(String[] args) {
        Online_Stock_Span stockSpanner = new Online_Stock_Span();
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        System.out.println("Stock prices: [100, 80, 60, 70, 60, 75, 85]");
        System.out.print("Spans: [");
        for (int p : prices) {
            System.out.print(stockSpanner.next(p) + " ");
        }
        System.out.println("]");
    }
}
