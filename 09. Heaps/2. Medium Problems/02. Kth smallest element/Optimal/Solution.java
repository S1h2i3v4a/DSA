/*
QUESTION:
Find the `k`th smallest element in `arr[l...r]` in O(N) average time.

APPROACH:
- QuickSelect algorithm targeting index `l + k - 1`.
- Partition around a pivot:
  - If pivot index == target, return `arr[pivot]`.
  - If pivot index < target, search right half.
  - Else search left half.

TIME COMPLEXITY: O(N) average case, O(N^2) worst case.
SPACE COMPLEXITY: O(1) iterative partitioning.
*/

class Solution {
    public static int kthSmallest(int[] arr, int l, int r, int k) {
        int target = l + k - 1;
        while (l <= r) {
            int pivotIdx = partition(arr, l, r);
            if (pivotIdx == target) {
                return arr[pivotIdx];
            } else if (pivotIdx < target) {
                l = pivotIdx + 1;
            } else {
                r = pivotIdx - 1;
            }
        }
        return -1;
    }

    private static int partition(int[] arr, int l, int r) {
        int pivot = arr[r];
        int i = l;
        for (int j = l; j < r; j++) {
            if (arr[j] <= pivot) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
        int temp = arr[i];
        arr[i] = arr[r];
        arr[r] = temp;
        return i;
    }
}
