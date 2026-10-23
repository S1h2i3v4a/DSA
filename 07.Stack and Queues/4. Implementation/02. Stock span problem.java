/*
QUESTION:
Design an algorithm to calculate the span of a stock's price for the current day based on the price history.

APPROACH:
To solve this problem, we can use a stack to store the prices along with their corresponding spans.
1. Initialize an empty stack and a variable to keep track of the current day (let's call it 'cnt').
2. For each price, do the following:
   - While the stack is not empty and the price at the top of the stack is less than or equal to the current price, pop elements from the stack.
   - Calculate the span for the current price using the following formula:
     - If the stack is empty, the span is cnt + 1.
     - Otherwise, the span is cnt - stack.top().second, where stack.top().second represents the day corresponding to the price at the top of the stack.
   - Push the current price along with its span (pair<int, int>) onto the stack.
   - Increment the 'cnt' variable.
3. Return the calculated spans.

Example:
Input: [100, 80, 60, 70, 60, 75, 85]
Output: [1, 1, 1, 2, 1, 4, 6]

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
