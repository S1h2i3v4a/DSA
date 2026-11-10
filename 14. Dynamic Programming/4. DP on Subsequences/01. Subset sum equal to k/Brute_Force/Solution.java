/*
QUESTION:
Given an array of non-negative integers and an integer k, determine if there exists a subset with sum equal to k.

APPROACH:
- Pure recursion: at index i, choose to pick or not pick arr[i].
- solve(i, target) = solve(i - 1, target) || solve(i - 1, target - arr[i]).

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public boolean isSubsetSum(int[] arr, int target) {
        return solve(arr.length - 1, target, arr);
    }

    private boolean solve(int index, int target, int[] arr) {
        if (target == 0) return true;
        if (index == 0) return arr[0] == target;
        boolean notPick = solve(index - 1, target, arr);
        boolean pick = false;
        if (arr[index] <= target) {
            pick = solve(index - 1, target - arr[index], arr);
        }
        return notPick || pick;
    }
}
