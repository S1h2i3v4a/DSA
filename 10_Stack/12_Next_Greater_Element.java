/**
 * Problem Name: Next Greater Element I
 * Platform: LeetCode (496) / GeeksforGeeks
 * Difficulty: Easy / Medium
 * 
 * Time Complexity: O(N) where N is length of array.
 * Space Complexity: O(N) monotonic stack & result array.
 * 
 * Approach:
 * Monotonic Decreasing Stack (Right-to-Left Traversal).
 * Traverse array from index N-1 down to 0:
 * 1. Pop stack while `stack.peek() <= arr[i]`.
 * 2. If stack is empty, next greater element is -1; else `stack.peek()`.
 * 3. Push `arr[i]` onto stack.
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Next_Greater_Element {

    public static int[] nextGreaterElement(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }

            stack.push(arr[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 25, 7, 8};
        int[] nge = nextGreaterElement(arr);
        System.out.println("Input Array: " + Arrays.toString(arr));
        System.out.println("Next Greater Elements: " + Arrays.toString(nge));
    }
}
