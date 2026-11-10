/*
QUESTION:
Given `n` non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it can trap after raining.

APPROACH:
- Precompute two arrays:
  - `leftMax[i]`: maximum height from index 0 to i.
  - `rightMax[i]`: maximum height from index i to n-1.
- Water trapped at index `i` is `min(leftMax[i], rightMax[i]) - height[i]`.

TIME COMPLEXITY: O(N) with three linear passes.
SPACE COMPLEXITY: O(N) for `leftMax` and `rightMax` arrays.
*/

class Solution {
    public static int trap(int[] height) {
        int n = height.length;
        if (n == 0) return 0;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            totalWater += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return totalWater;
    }
}
