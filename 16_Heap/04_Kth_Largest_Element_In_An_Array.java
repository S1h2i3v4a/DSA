/**
 * Problem Name: Kth Largest Element in an Array
 * Platform: LeetCode (215)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N log K) where N is array length.
 * Space Complexity: O(K) for PriorityQueue.
 * 
 * Approach:
 * Min-Heap of Size K.
 * Maintain a Min PriorityQueue storing the `K` largest elements seen so far.
 * Iterate through `nums`:
 * - Offer `nums[i]` to `minHeap`.
 * - If `minHeap.size() > k`, poll the smallest element `minHeap.poll()`.
 * After processing all elements, `minHeap.peek()` contains the K-th largest element.
 */

import java.util.PriorityQueue;

class Kth_Largest_Element_In_An_Array {

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println(k + "-th Largest Element: " + findKthLargest(nums, k));
    }
}
