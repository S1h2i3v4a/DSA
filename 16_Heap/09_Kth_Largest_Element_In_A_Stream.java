/**
 * Problem Name: Kth Largest Element in a Stream
 * Platform: LeetCode (LC 703)
 * Difficulty: Easy
 * 
 * Time Complexity:
 *   - Constructor: O(N log K) where N is the initial array size
 *   - add: O(log K)
 * Space Complexity: O(K) to store the min-heap elements
 * 
 * Approach:
 * Maintain a Min-Heap of size at most K.
 * The root of the min-heap always stores the K-th largest element encountered so far.
 * When adding a new element, push it into the heap and if heap size exceeds K, poll the minimum element.
 */

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
