/**
 * Problem Name: Implement Stack using Array
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * 
 * Time Complexity: O(1) for push, pop, peek, isEmpty operations.
 * Space Complexity: O(N) array storage where N is fixed capacity.
 * 
 * Approach:
 * LIFO Array Implementation.
 * Maintain integer pointer `top` initialized to -1.
 * - push(x): Increment `top` and assign arr[top] = x.
 * - pop(): Return arr[top] and decrement `top`. Return -1 if top == -1 (underflow).
 */

class Implement_Stack_Using_Array {

    private int[] arr;
    private int top;
    private int capacity;

    public Implement_Stack_Using_Array(int size) {
        arr = new int[size];
        capacity = size;
        top = -1;
    }

    public void push(int x) {
        if (top == capacity - 1) {
            System.out.println("Stack Overflow");
            return;
        }
        arr[++top] = x;
    }

    public int pop() {
        if (top == -1) {
            return -1; // Stack Underflow
        }
        return arr[top--];
    }

    public int peek() {
        if (top == -1) return -1;
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public static void main(String[] args) {
        Implement_Stack_Using_Array stack = new Implement_Stack_Using_Array(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Top element: " + stack.peek());
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Is empty? " + stack.isEmpty());
    }
}
