/**
 * Problem Name: Check if an Array Represents a Min Heap
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * 
 * Time Complexity: O(N)
 * Space Complexity: O(1) auxiliary space.
 * 
 * Approach:
 * Heap Property Array Validation.
 * Iterate through all non-leaf nodes from index `0` to `(N - 2) / 2`.
 * For each index `i`:
 * - Check left child `2*i + 1`: if `arr[i] > arr[2*i + 1]`, return false.
 * - Check right child `2*i + 2`: if `2*i + 2 < N` and `arr[i] > arr[2*i + 2]`, return false.
 * Return true if all parent-child relationships satisfy the Min-Heap condition.
 */

class Check_If_Array_Is_Min_Heap {

    public static boolean isMinHeap(int[] arr) {
        int n = arr.length;

        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && arr[i] > arr[left]) {
                return false;
            }

            if (right < n && arr[i] > arr[right]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 15, 20, 30, 25, 40};
        System.out.println("Is arr1 Min Heap? " + isMinHeap(arr1));

        int[] arr2 = {10, 30, 20, 15, 25, 40};
        System.out.println("Is arr2 Min Heap? " + isMinHeap(arr2));
    }
}
