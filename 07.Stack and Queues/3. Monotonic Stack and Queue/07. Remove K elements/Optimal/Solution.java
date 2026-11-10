/*
QUESTION:
Given string num representing a non-negative integer and an integer k,
return the smallest possible integer after removing k digits from num.

APPROACH:
- Maintain a monotonic increasing sequence using a deque / stack.
- For each digit:
  - While `k > 0`, stack is non-empty, and `stack.peek() > currentDigit`, pop from stack and decrement `k`.
  - Push current digit.
- If `k > 0` remains, pop `k` digits from the end.
- Build result string and strip leading zeros. If empty, return "0".

TIME COMPLEXITY: O(N) single pass with monotonic stack.
SPACE COMPLEXITY: O(N) for stack storage.
*/

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String removeKdig(String num, int k) {
        int n = num.length();
        if (n == k) return "0";

        Deque<Character> stack = new ArrayDeque<>();
        for (char c : num.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && stack.peekLast() > c) {
                stack.pollLast();
                k--;
            }
            stack.addLast(c);
        }

        while (k > 0 && !stack.isEmpty()) {
            stack.pollLast();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollFirst());
        }

        // Remove leading zeros
        int start = 0;
        while (start < sb.length() && sb.charAt(start) == '0') {
            start++;
        }

        String res = sb.substring(start);
        return res.isEmpty() ? "0" : res;
    }
}
