/*
QUESTION:
Generate the nth term of the count-and-say sequence.
countAndSay(1) = "1".
countAndSay(n) is run-length encoding of countAndSay(n-1).

APPROACH:
- Pure recursion: recursively evaluate countAndSay(n - 1) and encode it.

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public String countAndSay(int n) {
        if (n <= 1) return "1";
        String prev = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int i = 1; i < prev.length(); i++) {
            if (prev.charAt(i) == prev.charAt(i - 1)) {
                count++;
            } else {
                sb.append(count).append(prev.charAt(i - 1));
                count = 1;
            }
        }
        sb.append(count).append(prev.charAt(prev.length() - 1));

        return sb.toString();
    }
}
