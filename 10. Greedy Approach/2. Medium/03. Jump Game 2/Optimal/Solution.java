/*
QUESTION:
Find minimum jumps to reach the last index in O(N) using Greedy BFS intervals.

APPROACH:
- View jumps as levels in a BFS search tree.
- Maintain current window `[currStart, currEnd]` and `farthest`.
- For each `i` in current window:
  - `farthest = Math.max(farthest, i + nums[i])`.
  - When reaching `currEnd`, we must take a jump: increment `jumps`, update `currEnd = farthest`.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        int jumps = 0;
        int currEnd = 0;
        int farthest = 0;

        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == currEnd) {
                jumps++;
                currEnd = farthest;
            }
        }
        return jumps;
    }
}
