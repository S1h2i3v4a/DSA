/**
 * Problem Name: Implement Stack using Queues
 * Platform: LeetCode (225)
 * Difficulty: Easy
 * 
 * Time Complexity: O(N) for push(x), O(1) for pop(), top(), empty().
 * Space Complexity: O(N) queue storage.
 * 
 * Approach:
 * Single Queue Rotation.
 * On push(x): Enqueue x. Then rotate the queue `size - 1` times by polling front and re-offering to back.
 * This positions the newly pushed element at the front of the queue, mimicking LIFO order.
 */

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
