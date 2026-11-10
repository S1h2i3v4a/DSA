/*
QUESTION:
Given an array `arr` which represents the inorder traversal of a binary search tree (BST),
check if it is a valid BST inorder traversal.

APPROACH:
- The inorder traversal of a valid BST must be strictly increasing.
- Iterate through array and check if `arr[i] <= arr[i - 1]`. If so, return false.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    static boolean isBSTTraversal(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= arr[i - 1]) return false;
        }
        return true;
    }
}
