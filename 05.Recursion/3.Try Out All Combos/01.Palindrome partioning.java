/*QUESTION:
Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

Example:
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]

Approach:
We can use a recursive backtracking approach to generate all possible palindrome partitioning.
1. Create a helper function called 'solve' that takes the current index, the input string 's', a temporary vector to store the current partition, and the vector to store all partitions.
2. In the 'solve' function:
   - If the current index is greater than or equal to the size of the input string 's', add the current partition to the vector of partitions and return.
   - Iterate through all possible substrings starting from the current index to the end of the string.
   - Check if the current substring is a palindrome by using a helper function 'isPalindrome'.
   - If the current substring is a palindrome:
     - Add the current substring to the temporary partition vector.
     - Recursively call the 'solve' function with the updated index by incrementing it, the same input string 's', the updated temporary partition vector, and the vector of partitions.
     - Remove the last added substring from the temporary partition vector (backtracking).
3. Create the main function called 'partition' that calculates and returns the vector of partitions.
   - Initialize an empty vector to store the partitions.
   - Initialize an empty temporary vector to store the current partition.
   - Call the 'solve' function with the initial index as 0, the input string 's', the temporary partition vector, and the vector of partitions.
   - Return the vector of partitions.

Time Complexity: O(N * 2^N), where N is the length of the input string 's'. In the worst case, we can have 2^N possible partitions, and for each partition, we need to check if each substring is a palindrome, which takes O(N) time.
Space Complexity: O(N), where N is the length of the input string 's'. The space is used for storing the temporary partition vector and the vector of partitions.

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.ArrayList;
import java.util.List;

class Palindrome_Partitioning {

    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(0, s, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int start, String s, List<String> currentPath, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(currentPath));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                currentPath.add(s.substring(start, end + 1));
                backtrack(end + 1, s, currentPath, result);
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> partitions = partition(s);
        System.out.println("Palindrome Partitions for \"" + s + "\": " + partitions);
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
