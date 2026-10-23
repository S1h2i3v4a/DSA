/**
 * Problem Name: Longest Word with All Prefixes (Complete String)
 * Platform: Coding Ninjas / GeeksforGeeks
 * Difficulty: Hard
 *
 * Time Complexity: O(Total characters across all words)
 * Space Complexity: O(Total characters * 26)
 *
 * Approach:
 *   - Find the longest string in array `words` such that EVERY prefix of that string is also present
 *     as an independent complete word in `words`.
 *   - If there is a tie in length, return the lexicographically smallest string.
 *   - If no such string exists, return "None".
 *   - Algorithm:
 *     1. Insert all words into a Trie, setting isEnd = true for terminal nodes.
 *     2. For each word in `words`, traverse the Trie and verify that every single character
 *        node has `node.isEnd == true` (meaning each prefix exists).
 *     3. Update the longest complete string (favoring greater length, then smaller lexicographical order).
 */

class LongestStringWithAllPrefixes {

    static class Node {
        Node[] children = new Node[26];
        boolean isEnd = false;
    }

    static class Trie {
        Node root = new Node();

        public void insert(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new Node();
                }
                node = node.children[idx];
            }
            node.isEnd = true;
        }

        public boolean checkIfAllPrefixesExist(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) return false;
                node = node.children[idx];
                if (!node.isEnd) return false; // Prefix missing!
            }
            return true;
        }
    }

    public static String completeString(int n, String[] a) {
        Trie trie = new Trie();
        for (String word : a) {
            trie.insert(word);
        }

        String longest = "";

        for (String word : a) {
            if (trie.checkIfAllPrefixesExist(word)) {
                if (word.length() > longest.length()) {
                    longest = word;
                } else if (word.length() == longest.length() && word.compareTo(longest) < 0) {
                    longest = word;
                }
            }
        }

        return longest.isEmpty() ? "None" : longest;
    }

    public static void main(String[] args) {
        // Test Case 1: words = ["n", "ni", "nin", "ninj", "ninja", "ninga"] -> Expected: "ninja"
        String[] a1 = {"n", "ni", "nin", "ninj", "ninja", "ninga"};
        System.out.println("Test 1: " + completeString(a1.length, a1)); // "ninja"

        // Test Case 2: words = ["ab", "bc"] -> Expected: "None" (Prefix 'a' missing)
        String[] a2 = {"ab", "bc"};
        System.out.println("Test 2: " + completeString(a2.length, a2)); // "None"
    }
}
