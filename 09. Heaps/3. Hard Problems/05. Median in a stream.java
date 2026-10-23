/*
Question:
The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.

Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.*;

class Find_Median_From_Data_Stream {
    private PriorityQueue<Integer> maxHeap; // Lower half
    private PriorityQueue<Integer> minHeap; // Upper half

    public Find_Median_From_Data_Stream() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());

        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        Find_Median_From_Data_Stream medianFinder = new Find_Median_From_Data_Stream();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println("Median after [1, 2]: " + medianFinder.findMedian()); // 1.5
        medianFinder.addNum(3);
        System.out.println("Median after [1, 2, 3]: " + medianFinder.findMedian()); // 2.0
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
