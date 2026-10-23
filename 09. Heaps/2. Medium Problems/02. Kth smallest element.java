/*
QUESTION:
Given an array arr[] and an integer K, where K is smaller than the size of the array, the task is to find the Kth smallest element in the given array. It is given that all array elements are distinct.

APPROACH:
To find the Kth smallest element in the array, we can use a max-heap of size K.
1. Initialize a max-heap (priority queue) of size K.
2. Insert the first K elements of the array into the max-heap.
3. For the remaining elements in the array, if an element is smaller than the top element of the max-heap, replace the top element with the current element and heapify to maintain the max-heap property.
4. After processing all the elements, the top element of the max-heap will be the Kth smallest element in the array.

The findKthLargest function implements this approach. It uses a max-heap (priority queue) to find the Kth smallest element in the array without sorting the entire array.

TIME COMPLEXITY:
The time complexity of the findKthLargest function is O(N log K), where N is the size of the array. Inserting elements into the max-heap and heapifying take O(log K) time, and we do this for N-K elements. The overall time complexity is dominated by the heap operations.

SPACE COMPLEXITY:
The space complexity is O(K) as we need to store K elements in the max-heap.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static int kthSmallest(int[] arr, int l, int r, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = l; i <= r; i++) {
            maxHeap.add(arr[i]);
            if (maxHeap.size() > k) maxHeap.poll();
        }
        return maxHeap.peek();
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
