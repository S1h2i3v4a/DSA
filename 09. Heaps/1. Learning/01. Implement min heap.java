/*
QUESTION:
Implement a min heap with all the required methods.

APPROACH:
A min heap is a binary tree-based data structure where the value of each parent node is less than or equal to the values of its children. The heapify operation is used to maintain the heap property, where a node is compared with its children and swapped if necessary.

In the given code, the minHeap class is implemented using a vector to store the elements. The heapify function is used to maintain the heap property by comparing the node with its left and right children and swapping if necessary. The bottomUpHeapify function is used to fix the heap property from a child node to its parent node.

The insert function adds an element to the heap by appending it to the vector and performing bottom-up heapification. The removeMin function removes the minimum element from the heap by replacing it with the last element, removing the last element, and then performing heapify. The minElement function returns the minimum element of the heap.

COMPLEXITY ANALYSIS:
- The heapify operation takes O(log n) time complexity, where n is the number of elements in the heap.
- The bottomUpHeapify operation also takes O(log n) time complexity.
- The insert operation takes O(log n) time complexity.
- The removeMin operation takes O(log n) time complexity.
- The minElement operation takes O(1) time complexity.

Overall, the time complexity of the minHeap class methods is O(log n), where n is the number of elements in the heap.

CODE:-
*/

import java.util.*;
import java.io.*;

// CODE:-
class Implement_Min_Heap {

    private int[] heap;
    private int capacity;
    private int heapSize;

    public Implement_Min_Heap(int capacity) {
        this.capacity = capacity;
        this.heapSize = 0;
        this.heap = new int[capacity];
    }

    public int parent(int i) {
        return (i - 1) / 2;
    }

    public int leftChild(int i) {
        return 2 * i + 1;
    }

    public int rightChild(int i) {
        return 2 * i + 2;
    }

    public int getMin() {
        if (heapSize <= 0) return -1;
        return heap[0];
    }

    public void insertKey(int val) {
        if (heapSize == capacity) {
            System.out.println("Heap Overflow");
            return;
        }

        heapSize++;
        int i = heapSize - 1;
        heap[i] = val;

        // Heapify Up
        while (i != 0 && heap[parent(i)] > heap[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public int extractMin() {
        if (heapSize <= 0) return -1;
        if (heapSize == 1) {
            heapSize--;
            return heap[0];
        }

        int root = heap[0];
        heap[0] = heap[heapSize - 1];
        heapSize--;

        minHeapify(0);

        return root;
    }

    private void minHeapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int smallest = i;

        if (left < heapSize && heap[left] < heap[smallest]) {
            smallest = left;
        }
        if (right < heapSize && heap[right] < heap[smallest]) {
            smallest = right;
        }

        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static void main(String[] args) {
        Implement_Min_Heap minHeap = new Implement_Min_Heap(10);
        minHeap.insertKey(3);
        minHeap.insertKey(2);
        minHeap.insertKey(15);
        minHeap.insertKey(5);
        minHeap.insertKey(4);
        minHeap.insertKey(45);

        System.out.println("Extracted Min: " + minHeap.extractMin()); // 2
        System.out.println("Current Min: " + minHeap.getMin()); // 3
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
