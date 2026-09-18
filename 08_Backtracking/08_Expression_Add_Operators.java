/**
 * Problem Name: Expression Add Operators
 * Platform: LeetCode (282)
 * Difficulty: Hard
 * 
 * Time Complexity: O(4^N) where N is length of string num.
 * Space Complexity: O(N) recursion stack.
 * 
 * Approach:
 * String Partition Backtracking.
 * Try forming operands of varying lengths from index `index`.
 * Track:
 * 1. `eval`: cumulative evaluated value so far
 * 2. `prev`: last operand value (used to handle multiplication precedence: eval - prev + prev * curr)
 * Skip numbers starting with '0' if length > 1 to prevent leading zeros.
 */

import java.util.ArrayList;
import java.util.List;

class Expression_Add_Operators {

    public static List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.length() == 0) return result;
        backtrack(0, num, target, 0, 0, "", result);
        return result;
    }

    private static void backtrack(int index, String num, int target, long eval, long prev, 
                                 String path, List<String> result) {
        if (index == num.length()) {
            if (eval == target) {
                result.add(path);
            }
            return;
        }

        for (int i = index; i < num.length(); i++) {
            // Leading zeros check
            if (i != index && num.charAt(index) == '0') break;

            long curr = Long.parseLong(num.substring(index, i + 1));

            if (index == 0) {
                backtrack(i + 1, num, target, curr, curr, "" + curr, result);
            } else {
                // Addition
                backtrack(i + 1, num, target, eval + curr, curr, path + "+" + curr, result);
                // Subtraction
                backtrack(i + 1, num, target, eval - curr, -curr, path + "-" + curr, result);
                // Multiplication (subtract prev, add prev * curr)
                backtrack(i + 1, num, target, eval - prev + prev * curr, prev * curr, path + "*" + curr, result);
            }
        }
    }

    public static void main(String[] args) {
        String num = "232";
        int target = 8;
        List<String> expressions = addOperators(num, target);
        System.out.println("Expressions for num = \"" + num + "\", target = " + target + ": " + expressions);
    }
}
