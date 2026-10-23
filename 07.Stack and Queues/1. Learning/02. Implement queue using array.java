/*
Question:
Implement a Queue using an Array. Queries in the Queue are of the following type:
(i) 1 x   (a query of this type means pushing 'x' into the queue)
(ii) 2     (a query of this type means to pop an element from the queue and print the popped element)

Approach:
- We use a class `MyQueue` to represent the queue.
- The queue is implemented using an array `arr`, a front variable to keep track of the front element, and a rear variable to keep track of the next available position to insert an element.
- The class provides two member functions: `push(int)` to push an element into the queue and `pop()` to pop an element from the front of the queue.
- The `push(int)` function inserts the element at the rear index and increments the rear.
- The `pop()` function checks if the queue is empty (rear == front), in which case it returns -1. Otherwise, it retrieves the element at the front index, increments the front, and returns the element.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
