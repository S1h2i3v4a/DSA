/*
QUESTION:
Given n balloons with values nums[i], burst all balloons to maximize coins collected:
bursting balloon i earns nums[left] * nums[i] * nums[right].

APPROACH:
- Pure recursion thinking backwards: choose the LAST balloon to burst in range (i, j).

TIME COMPLEXITY: O(N!).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        System.arraycopy(nums, 0, arr, 1, n);
        return solve(1, n, arr);
    }

    private int solve(int i, int j, int[] arr) {
        if (i > j) return 0;
        int maxi = Integer.MIN_VALUE;

        for (int k = i; k <= j; k++) {
            int coins = arr[i - 1] * arr[k] * arr[j + 1] + solve(i, k - 1, arr) + solve(k + 1, j, arr);
            maxi = Math.max(maxi, coins);
        }

        return maxi;
    }
}
