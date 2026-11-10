/*
QUESTION:
Given string num representing a non-negative integer and an integer k,
return the smallest possible integer after removing k digits from num.

APPROACH:
- In each of the `k` steps, find the first digit that is greater than the next digit (`num[i] > num[i+1]`) and remove it.
- If all digits are in non-decreasing order, remove the last digit.
- Strip leading zeros at the end.

TIME COMPLEXITY: O(N * K) where N is length of string num.
SPACE COMPLEXITY: O(N) due to string reconstructions.
*/

class Solution {
    public String removeKdig(String num, int k) {
        if (num.length() == k) return "0";

        StringBuilder sb = new StringBuilder(num);
        for (int step = 0; step < k; step++) {
            int i = 0;
            while (i < sb.length() - 1 && sb.charAt(i) <= sb.charAt(i + 1)) {
                i++;
            }
            sb.deleteCharAt(i);
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
