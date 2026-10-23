/*
Question:
Implement a Queue using two stacks s1 and s2.

Approach:
- We use the class `Queue` to represent the queue.
- The queue is implemented using two stacks `input` and `output`.
- The `enqueue(int)` function inserts an element into `input` stack.
- The `dequeue()` function transfers elements from `input` stack to `output` stack if `output` is empty, and returns the top element from `output` stack as the dequeued element.
- To transfer elements from `input` to `output`, we pop each element from the top of `input` and push it into `output`.
- After transferring elements, we pop the top element from `output` as the dequeued element.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.Stack;

class Implement_Queue_Using_Stacks {

    private Stack<Integer> input;
    private Stack<Integer> output;

    public Implement_Queue_Using_Stacks() {
        input = new Stack<>();
        output = new Stack<>();
    }

    public void push(int x) {
        input.push(x);
    }

    public int pop() {
        peek();
        return output.pop();
    }

    public int peek() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
        return output.peek();
    }

    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }

    public static void main(String[] args) {
        Implement_Queue_Using_Stacks queue = new Implement_Queue_Using_Stacks();
        queue.push(10);
        queue.push(20);
        System.out.println("Front element: " + queue.peek()); // 10
        System.out.println("Popped element: " + queue.pop()); // 10
        System.out.println("Is empty? " + queue.empty()); // false
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
