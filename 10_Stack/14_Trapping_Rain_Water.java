/**
 * Problem Name: Trapping Rain Water
 * Platform: LeetCode (42)
 * Difficulty: Hard
 * 
 * Time Complexity: O(N) single pass.
 * Space Complexity: O(1) auxiliary space using Two Pointers.
 * 
 * Approach:
 * Two Pointers Optimal Strategy.
 * Maintain `left = 0`, `right = N - 1`, `leftMax = 0`, `rightMax = 0`.
 * At each step, process the smaller boundary `height[left]` vs `height[right]`:
 * - If `height[left] <= height[right]`:
 *   - If `height[left] >= leftMax`, update `leftMax = height[left]`.
 *   - Else, trapped water at left is `leftMax - height[left]`.
 *   - Increment `left`.
 * - Else:
 *   - If `height[right] >= rightMax`, update `rightMax = height[right]`.
 *   - Else, trapped water at right is `rightMax - height[right]`.
 *   - Decrement `right`.
 */

class Trapping_Rain_Water {

    public static int trap(int[] height) {
        int n = height.length;
        if (n == 0) return 0;

        int left = 0, right = n - 1;
        int leftMax = 0, rightMax = 0;
        int waterTrapped = 0;

        while (left <= right) {
            if (height[left] <= height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    waterTrapped += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    waterTrapped += rightMax - height[right];
                }
                right--;
            }
        }

        return waterTrapped;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Total Trapped Rain Water: " + trap(height));
    }
}
