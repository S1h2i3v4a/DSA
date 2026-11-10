/*
QUESTION:
Given an integer 'N', determine the maximum number of nodes present on the 'Nth' level in a binary tree.

APPROACH:
- In a binary tree, the number of nodes at each level doubles from the previous level.
- Level 1 has 2^0 = 1 node.
- Level N has 2^(N - 1) nodes.
- We can compute this using bit shift `1 << (n - 1)`.

TIME COMPLEXITY: O(1) bitwise operation.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    static int countNodes(int i) {
        return 1 << (i - 1);
    }
}
