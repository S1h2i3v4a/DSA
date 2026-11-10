/*
QUESTION:
Find length of longest bitonic subsequence (first strictly increases, then strictly decreases).

APPROACH:
- Pure recursion testing every peak index i, finding LIS up to i and LDS from i onwards.

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int longestBitonicSequence(int n, int[] nums) {
        int maxBitonic = 0;
        for (int i = 0; i < n; i++) {
            maxBitonic = Math.max(maxBitonic, lis(i, nums) + lds(i, nums) - 1);
        }
        return maxBitonic;
    }

    private int lis(int end, int[] nums) {
        return solveLis(end, -1, nums);
    }

    private int solveLis(int i, int prevIdx, int[] nums) {
        if (i < 0) return 0;
        int notTake = solveLis(i - 1, prevIdx, nums);
        int take = 0;
        if (prevIdx == -1 || nums[i] < nums[prevIdx]) {
            take = 1 + solveLis(i - 1, i, nums);
        }
        return Math.max(notTake, take);
    }

    private int lds(int start, int[] nums) {
        return solveLds(start, -1, nums);
    }

    private int solveLds(int i, int prevIdx, int[] nums) {
        if (i == nums.length) return 0;
        int notTake = solveLds(i + 1, prevIdx, nums);
        int take = 0;
        if (prevIdx == -1 || nums[i] < nums[prevIdx]) {
            take = 1 + solveLds(i + 1, i, nums);
        }
        return Math.max(notTake, take);
    }
}
