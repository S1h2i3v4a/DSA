/*
QUESTION:
Find the maximum number of children you can make content using a Greedy approach with two pointers.

APPROACH:
- Sort the greed array `g` and the cookie size array `s` in ascending order.
- Maintain two pointers: `child` for `g` and `cookie` for `s`.
- While `child < g.length` and `cookie < s.length`:
  - If `s[cookie] >= g[child]`: child is satisfied, increment `child`.
  - Always increment `cookie` to consider the next cookie.
- Return `child`.

TIME COMPLEXITY: O(N log N + M log M) to sort both arrays.
SPACE COMPLEXITY: O(1) auxiliary space.
*/

import java.util.Arrays;

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int child = 0, cookie = 0;
        while (child < g.length && cookie < s.length) {
            if (s[cookie] >= g[child]) {
                child++;
            }
            cookie++;
        }
        return child;
    }
}
