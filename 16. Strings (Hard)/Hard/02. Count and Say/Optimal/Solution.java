/*
QUESTION:
Generate the nth term of the count-and-say sequence.

APPROACH:
- Iterative simulation:
- Start with s = "1", perform n - 1 iterations of run-length encoding using StringBuilder.

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(1) auxiliary space beyond result string.
*/

class Solution {
    public String countAndSay(int n) {
        if (n <= 0) return "";
        String s = "1";

        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (int j = 1; j < s.length(); j++) {
                if (s.charAt(j) == s.charAt(j - 1)) {
                    count++;
                } else {
                    sb.append(count).append(s.charAt(j - 1));
                    count = 1;
                }
            }
            sb.append(count).append(s.charAt(s.length() - 1));
            s = sb.toString();
        }

        return s;
    }
}
