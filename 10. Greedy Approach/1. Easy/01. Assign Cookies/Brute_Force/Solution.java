/*
QUESTION:
Assume you are an awesome parent and want to give your children some cookies.
Each child `i` has a greed factor `g[i]`, and each cookie `j` has a size `s[j]`.
Find the maximum number of children you can make content.

APPROACH:
- For each child, iterate through all cookies to find the smallest available cookie with `size >= greed`.
- Mark that cookie as used using a boolean array.
- Count how many children are satisfied.

TIME COMPLEXITY: O(N * M) where N is number of children and M is number of cookies.
SPACE COMPLEXITY: O(M) for boolean array of used cookies.
*/

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        boolean[] used = new boolean[s.length];
        int contentChildren = 0;

        for (int greed : g) {
            int bestCookieIdx = -1;
            for (int j = 0; j < s.length; j++) {
                if (!used[j] && s[j] >= greed) {
                    if (bestCookieIdx == -1 || s[j] < s[bestCookieIdx]) {
                        bestCookieIdx = j;
                    }
                }
            }
            if (bestCookieIdx != -1) {
                used[bestCookieIdx] = true;
                contentChildren++;
            }
        }
        return contentChildren;
    }
}
