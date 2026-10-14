/**
 * Problem Name: Accounts Merge
 * Platform: LeetCode (LC 721) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(N * K * log(N * K) * alpha(N)) where N is number of accounts, K is max emails per account
 * Space Complexity: O(N * K) for DSU structures and hash maps
 *
 * Approach:
 *   - Each account index (0 to n - 1) is treated as a node in a Disjoint Set Union (DSU) structure.
 *   - We maintain a HashMap<String, Integer> mapping each email to the account index that first introduced it.
 *   - When traversing an account `i`:
 *     For each email, if it was previously seen in account `prevAccount`, we union(i, prevAccount).
 *     Otherwise, we register `email -> i`.
 *   - After processing all accounts, we group all unique emails by their representative root account index:
 *     `root = dsu.find(i)`.
 *   - Finally, for each non-empty root group:
 *     Sort the merged emails lexicographically, prefix with the account owner's name,
 *     and append to the final merged accounts list.
 */

import java.util.*;

class AccountsMerge {

    static class DisjointSet {
        int[] parent;
        int[] size;

        public DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int node) {
            if (node == parent[node]) {
                return node;
            }
            return parent[node] = find(parent[node]); // Path compression
        }

        public void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU == rootV) return;

            if (size[rootU] < size[rootV]) {
                parent[rootU] = rootV;
                size[rootV] += size[rootU];
            } else {
                parent[rootV] = rootU;
                size[rootU] += size[rootV];
            }
        }
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);
        Map<String, Integer> emailToAccount = new HashMap<>();

        // Step 1: Union accounts sharing the same email
        for (int i = 0; i < n; i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                if (emailToAccount.containsKey(email)) {
                    ds.union(i, emailToAccount.get(email));
                } else {
                    emailToAccount.put(email, i);
                }
            }
        }

        // Step 2: Group emails under their ultimate parent account root
        Map<Integer, TreeSet<String>> mergedEmails = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailToAccount.entrySet()) {
            String email = entry.getKey();
            int root = ds.find(entry.getValue());
            mergedEmails.computeIfAbsent(root, k -> new TreeSet<>()).add(email);
        }

        // Step 3: Construct the final output list with person name + sorted emails
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<Integer, TreeSet<String>> entry : mergedEmails.entrySet()) {
            int root = entry.getKey();
            List<String> mergedAccount = new ArrayList<>();
            // Account name is at index 0 of the representative account
            mergedAccount.add(accounts.get(root).get(0));
            mergedAccount.addAll(entry.getValue());
            result.add(mergedAccount);
        }

        return result;
    }

    public static void main(String[] args) {
        // Test Case 1:
        // accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],
        //             ["John","johnsmith@mail.com","john00@mail.com"],
        //             ["Mary","mary@mail.com"],
        //             ["John","johnnybravo@mail.com"]]
        List<List<String>> accounts1 = new ArrayList<>();
        accounts1.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts1.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts1.add(Arrays.asList("Mary", "mary@mail.com"));
        accounts1.add(Arrays.asList("John", "johnnybravo@mail.com"));

        List<List<String>> merged1 = accountsMerge(accounts1);
        System.out.println("Test Case 1 Merged Accounts:");
        for (List<String> acc : merged1) {
            System.out.println(acc);
        }

        // Test Case 2:
        // accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],
        //             ["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],
        //             ["Cassidy","Cassidy4@m.co","Cassidy0@m.co"],
        //             ["Gabe","Gabe0@m.co","Gabe2@m.co"]]
        List<List<String>> accounts2 = new ArrayList<>();
        accounts2.add(Arrays.asList("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe1@m.co"));
        accounts2.add(Arrays.asList("Kevin", "Kevin3@m.co", "Kevin5@m.co", "Kevin0@m.co"));
        accounts2.add(Arrays.asList("Cassidy", "Cassidy4@m.co", "Cassidy0@m.co"));
        accounts2.add(Arrays.asList("Gabe", "Gabe0@m.co", "Gabe2@m.co"));

        List<List<String>> merged2 = accountsMerge(accounts2);
        System.out.println("\nTest Case 2 Merged Accounts:");
        for (List<String> acc : merged2) {
            System.out.println(acc);
        }
    }
}
