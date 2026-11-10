/*
QUESTION:
Implement a Min-Heap data structure with insertKey, extractMin, and getMin operations.

APPROACH:
- Use an array `heap` and track `size`.
- `insertKey(val)`: Place `val` at the end, heapify-up by repeatedly swapping with parent if parent > child.
- `extractMin()`: Minimum is at index 0. Replace root with last element, decrease size, and heapify-down.
- `getMin()`: Return root element `heap[0]`.

TIME COMPLEXITY:
- insertKey: O(log N)
- extractMin: O(log N)
- getMin: O(1)
SPACE COMPLEXITY: O(N) for array storage.
*/

class Solution {
    int[] heap;
    int capacity;
    int size;

    public Solution(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    public Solution() {
        this(1000);
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }

    public int getMin() {
        return size == 0 ? -1 : heap[0];
    }

    public void insertKey(int val) {
        if (size == capacity) return;
        heap[size] = val;
        int curr = size;
        size++;

        while (curr != 0 && heap[parent(curr)] > heap[curr]) {
            int temp = heap[curr];
            heap[curr] = heap[parent(curr)];
            heap[parent(curr)] = temp;
            curr = parent(curr);
        }
    }

    public int extractMin() {
        if (size <= 0) return -1;
        if (size == 1) {
            size--;
            return heap[0];
        }

        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        minHeapify(0);
        return root;
    }

    private void minHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = i;

        if (l < size && heap[l] < heap[smallest]) smallest = l;
        if (r < size && heap[r] < heap[smallest]) smallest = r;

        if (smallest != i) {
            int temp = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = temp;
            minHeapify(smallest);
        }
    }
}
