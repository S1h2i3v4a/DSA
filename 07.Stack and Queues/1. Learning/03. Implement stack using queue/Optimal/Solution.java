/*
QUESTION:
Implement a last-in-first-out (LIFO) stack using a single queue.
Implement the functions: push, pop, top, and empty.

APPROACH:
- Use a single queue `q`.
- On `push(x)`:
  1. Get the current size `s = q.size()`.
  2. Add `x` to the queue.
  3. Dequeue and re-enqueue `s` times so `x` comes to the front of the queue.
- `pop()` and `top()` simply access the front of the queue in O(1).

TIME COMPLEXITY:
- push: O(N)
- pop, top, empty: O(1)
SPACE COMPLEXITY: O(N) using a single queue.
*/

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private Queue<Integer> q;

    public Solution() {
        q = new LinkedList<>();
    }

    public void push(int x) {
        q.add(x);
        int size = q.size();
        for (int i = 0; i < size - 1; i++) {
            q.add(q.poll());
        }
    }

    public int pop() {
        return q.isEmpty() ? -1 : q.poll();
    }

    public int top() {
        return q.isEmpty() ? -1 : q.peek();
    }

    public boolean empty() {
        return q.isEmpty();
    }
}
