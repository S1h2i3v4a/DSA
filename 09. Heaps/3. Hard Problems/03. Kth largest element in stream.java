/*
Question:
Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Implement KthLargest class:
- KthLargest(int k, int[] nums): Initializes the object with the integer k and the stream of integers nums.
- int add(int val): Appends the integer val to the stream and returns the element representing the kth largest element in the stream.

Example:
Input:
["KthLargest", "add", "add", "add", "add", "add"]
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
Output:
[null, 4, 5, 5, 8, 8]

Explanation:
KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
kthLargest.add(3);   // return 4
kthLargest.add(5);   // return 5
kthLargest.add(10);  // return 5
kthLargest.add(9);   // return 8
kthLargest.add(4);   // return 8

Approach:
- Use a min heap (priority queue) to store the k largest elements.
- Initialize the min heap with the first k elements from the stream (nums) in the constructor.
- For each subsequent element in the stream, add it to the min heap.
- If the size of the min heap exceeds k, remove the smallest element.
- The top element of the min heap represents the kth largest element.

Complexity Analysis:
- The time complexity of adding an element is O(log k) due to the insertion and removal operations in the min heap.
- The space complexity is O(k) to store the k largest elements in the min heap.

Code:
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.*;

class Kth_Largest_Element_In_A_Stream {
    private PriorityQueue<Integer> minHeap;
    private int k;

    public Kth_Largest_Element_In_A_Stream(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        minHeap.offer(val);
        if (minHeap.size() > k) {
            minHeap.poll();
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 8, 2};
        Kth_Largest_Element_In_A_Stream kthLargest = new Kth_Largest_Element_In_A_Stream(3, nums);
        System.out.println("Add 3: " + kthLargest.add(3));   // Returns 4
        System.out.println("Add 5: " + kthLargest.add(5));   // Returns 5
        System.out.println("Add 10: " + kthLargest.add(10)); // Returns 5
        System.out.println("Add 9: " + kthLargest.add(9));   // Returns 8
        System.out.println("Add 4: " + kthLargest.add(4));   // Returns 8
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
