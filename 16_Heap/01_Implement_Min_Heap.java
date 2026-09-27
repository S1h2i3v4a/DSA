/**
 * Problem Name: Implement Min Heap
 * Platform: GeeksforGeeks / Theory
 * Difficulty: Easy
 * 
 * Time Complexity: O(log N) for insertKey and extractMin, O(1) for getMin.
 * Space Complexity: O(N) array capacity.
 * 
 * Approach:
 * Complete Binary Tree Array Representation.
 * - For node at index `i`: Left Child = `2*i + 1`, Right Child = `2*i + 2`, Parent = `(i - 1)/2`.
 * - `insertKey(val)`: Append at end of array, heapifyUp while `parent > current`.
 * - `extractMin()`: Save root value `heap[0]`, replace `heap[0]` with last element, heapifyDown from root.
 */

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
