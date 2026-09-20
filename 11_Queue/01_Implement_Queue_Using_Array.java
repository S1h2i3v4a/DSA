/**
 * Problem Name: Queue Using Array
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * 
 * Time Complexity: O(1) for enqueue, dequeue, peek operations.
 * Space Complexity: O(N) array capacity.
 * 
 * Approach:
 * Circular Array Queue.
 * Maintain `front`, `rear`, `count`, and `capacity`.
 * - push(x): Assign arr[rear] = x, update rear = (rear + 1) % capacity, increment count.
 * - pop(): Fetch arr[front], update front = (front + 1) % capacity, decrement count. Return -1 if count == 0.
 */

class Implement_Queue_Using_Array {

    private int[] arr;
    private int front;
    private int rear;
    private int count;
    private int capacity;

    public Implement_Queue_Using_Array(int size) {
        capacity = size;
        arr = new int[capacity];
        front = 0;
        rear = 0;
        count = 0;
    }

    public void push(int x) {
        if (count == capacity) {
            System.out.println("Queue Full");
            return;
        }
        arr[rear] = x;
        rear = (rear + 1) % capacity;
        count++;
    }

    public int pop() {
        if (count == 0) {
            return -1; // Queue Empty
        }
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

    public static void main(String[] args) {
        Implement_Queue_Using_Array queue = new Implement_Queue_Using_Array(5);
        queue.push(5);
        queue.push(15);
        queue.push(25);
        System.out.println("Front element: " + queue.peek());
        System.out.println("Popped element: " + queue.pop());
        System.out.println("Is empty? " + queue.isEmpty());
    }
}
