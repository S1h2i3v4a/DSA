/**
 * Problem Name: Implement Queue using Stacks
 * Platform: LeetCode (232)
 * Difficulty: Easy
 * 
 * Time Complexity: O(1) amortized for pop() and peek(), O(1) for push(x) and empty().
 * Space Complexity: O(N) two stacks storage.
 * 
 * Approach:
 * Two Stacks (`input` and `output`).
 * - push(x): Push directly to `input` stack.
 * - pop() / peek(): If `output` stack is empty, transfer all elements from `input` to `output`.
 *   Then pop / peek from `output` stack.
 */

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
