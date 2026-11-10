/*
QUESTION:
Given an array of integers `heights` representing the histogram's bar height where the width of each bar is 1,
return the area of the largest rectangle in the histogram.

APPROACH:
- For each bar at index `i`, expand to the left and right to find how far the rectangle can extend with height `heights[i]`.
- Find first index to left `l` where `heights[l] < heights[i]`.
- Find first index to right `r` where `heights[r] < heights[i]`.
- Width is `r - l - 1`. Area is `heights[i] * width`.

TIME COMPLEXITY: O(N^2) scanning left and right for every bar.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            int left = i;
            while (left >= 0 && heights[left] >= heights[i]) {
                left--;
            }
            int right = i;
            while (right < n && heights[right] >= heights[i]) {
                right++;
            }
            int width = right - left - 1;
            maxArea = Math.max(maxArea, heights[i] * width);
        }
        return maxArea;
    }
}
