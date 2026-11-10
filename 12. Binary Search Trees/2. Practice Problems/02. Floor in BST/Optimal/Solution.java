/*
QUESTION:
Given a Binary Search Tree and a number `x`, find the Floor of `x`.
Floor(x) is the greatest value in the BST smaller than or equal to `x`. If no floor exists, return -1.

APPROACH:
- Initialize `floor = -1`.
- While `root != null`:
  - If `root.data == x`: return `x`.
  - If `root.data < x`: candidate floor found, update `floor = root.data`, and move right (`root = root.right`).
  - Else: move left (`root = root.left`).
- Return `floor`.

TIME COMPLEXITY: O(H) where H is tree height.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Node {
    int data;
    Node left, right;
    Node(int val) { data = val; }
}

class Solution {
    public static int floor(Node root, int x) {
        int floor = -1;
        while (root != null) {
            if (root.data == x) {
                return x;
            }
            if (root.data < x) {
                floor = root.data;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return floor;
    }
}
