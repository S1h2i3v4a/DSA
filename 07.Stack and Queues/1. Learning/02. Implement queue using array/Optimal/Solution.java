/*
QUESTION:
Implement a Queue using an Array. The Queue should support push (enqueue), pop (dequeue), peek, and isEmpty operations.

APPROACH:
- Use a circular array with pointers `front`, `rear`, and a `count` variable.
- `push(x)`: Place `x` at `rear` and update `rear = (rear + 1) % capacity`.
- `pop()`: Retrieve `arr[front]`, update `front = (front + 1) % capacity`, decrement `count`.
- `peek()`: Return `arr[front]`.
- `isEmpty()`: Check if `count == 0`.

TIME COMPLEXITY: O(1) for all operations (push, pop, peek, isEmpty).
SPACE COMPLEXITY: O(N) where N is the maximum capacity of the queue.
*/

class Solution {
    int[] arr;
    int front;
    int rear;
    int count;
    int capacity;

    public Solution(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }

    public Solution() {
        this(1000);
    }

    public void push(int x) {
        if (count == capacity) return;
        arr[rear] = x;
        rear = (rear + 1) % capacity;
        count++;
    }

    public int pop() {
        if (count == 0) return -1;
        int val = arr[front];
        front = (front + 1) % capacity;
        count--;
        return val;
    }

    public int peek() {
        if (count == 0) return -1;
        return arr[front];
    }

    public boolean isEmpty() {
        return count == 0;
    }
}
