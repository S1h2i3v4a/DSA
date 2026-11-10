/*
QUESTION:
Implement a first-in-first-out (FIFO) queue using two stacks with amortized O(1) operations.
Implement push, pop, peek, and empty.

APPROACH:
- Use two stacks: `input` and `output`.
- `push(x)`: Always push onto `input` in O(1).
- `pop()` / `peek()`:
  - If `output` stack is empty, transfer all elements from `input` to `output`.
  - Then the top of `output` is the front element.
- Each element is pushed and popped at most twice throughout its lifetime.

TIME COMPLEXITY:
- push: O(1)
- pop, peek: Amortized O(1)
- empty: O(1)
SPACE COMPLEXITY: O(N) across two stacks.
*/

import java.util.Stack;

class Solution {
    private Stack<Integer> input;
    private Stack<Integer> output;

    public Solution() {
        input = new Stack<>();
        output = new Stack<>();
    }

    public void push(int x) {
        input.push(x);
    }

    public int pop() {
        peek();
        return output.isEmpty() ? -1 : output.pop();
    }

    public int peek() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
        return output.isEmpty() ? -1 : output.peek();
    }

    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }
}
