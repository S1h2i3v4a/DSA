/*
Question:
Implement a Stack using one queue.

Approach:
- We use the class `QueueStack` to represent the stack.
- The stack is implemented using one queue `q`.
- The `push(int)` function inserts an element into `q`.
- The `pop()` function transfers `n-1` elements from front of `q` to it's back and returns the last element from `q` as the popped element.


Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.LinkedList;
import java.util.Queue;

class Implement_Stack_Using_Queues {

    private Queue<Integer> queue;

    public Implement_Stack_Using_Queues() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.add(x);
        int size = queue.size();
        for (int i = 0; i < size - 1; i++) {
            queue.add(queue.poll());
        }
    }

    public int pop() {
        if (empty()) return -1;
        return queue.poll();
    }

    public int top() {
        if (empty()) return -1;
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        Implement_Stack_Using_Queues stack = new Implement_Stack_Using_Queues();
        stack.push(1);
        stack.push(2);
        System.out.println("Top element: " + stack.top()); // 2
        System.out.println("Popped element: " + stack.pop()); // 2
        System.out.println("Is empty? " + stack.empty()); // false
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
