/*
QUESTION:
Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
return the area of the largest rectangle in the histogram.

Example:
Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where the width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.ArrayDeque;
import java.util.Deque;

class Largest_Rectangle_In_Histogram {

    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && heights[stack.peek()] >= currentHeight) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println("Largest Rectangle Area: " + largestRectangleArea(heights));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
