/*
QUESTION:
Given `n` non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it can trap after raining.

APPROACH:
- For each element at index `i`, find `leftMax = max(height[0...i])` and `rightMax = max(height[i...n-1])`.
- Water trapped above bar `i` is `min(leftMax, rightMax) - height[i]`.
- Sum across all indices.

TIME COMPLEXITY: O(N^2) scanning left and right for every bar.
SPACE COMPLEXITY: O(1) auxiliary space.
*/

class Solution {
    public static int trap(int[] height) {
        int n = height.length;
        int totalWater = 0;

        for (int i = 0; i < n; i++) {
            int leftMax = 0;
            for (int j = 0; j <= i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            int rightMax = 0;
            for (int j = i; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            totalWater += Math.min(leftMax, rightMax) - height[i];
        }
        return totalWater;
    }
}
