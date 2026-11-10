/*
QUESTION:
Given `n` non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it can trap after raining.

APPROACH:
- Use two pointers: `left = 0` and `right = n - 1` with `leftMax` and `rightMax`.
- If `height[left] <= height[right]`:
  - If `height[left] >= leftMax`, update `leftMax = height[left]`.
  - Else trapped water += `leftMax - height[left]`.
  - Increment `left`.
- Else:
  - If `height[right] >= rightMax`, update `rightMax = height[right]`.
  - Else trapped water += `rightMax - height[right]`.
  - Decrement `right`.

TIME COMPLEXITY: O(N) single pass with two pointers.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public static int trap(int[] height) {
        int n = height.length;
        int left = 0, right = n - 1;
        int leftMax = 0, rightMax = 0;
        int totalWater = 0;

        while (left <= right) {
            if (height[left] <= height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    totalWater += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    totalWater += rightMax - height[right];
                }
                right--;
            }
        }
        return totalWater;
    }
}
