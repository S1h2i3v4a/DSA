/*
QUESTION:
Given a Binary Search Tree and a number `X`, find the Ceil of `X`.
Ceil(X) is a number that is either equal to `X` or is the smallest number greater than `X` in the BST.
If no ceil exists, return -1.

APPROACH:
- Initialize `ceil = -1`.
- While `root != null`:
  - If `root.data == key`: return `key`.
  - If `key > root.data`: move to right subtree (`root = root.right`).
  - Else (`key < root.data`): candidate ceil found, update `ceil = root.data`, and move left (`root = root.left`).
- Return `ceil`.

TIME COMPLEXITY: O(H) where H is tree height.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Node {
    int data;
    Node left, right;
    Node(int val) { data = val; }
}

class Solution {
    int findCeil(Node root, int key) {
        int ceil = -1;
        while (root != null) {
            if (root.data == key) {
                return key;
            }
            if (key > root.data) {
                root = root.right;
            } else {
                ceil = root.data;
                root = root.left;
            }
        }
        return ceil;
    }
}
