/*
QUESTION:-
Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Approach:
1. We use a disjoint-set data structure to group accounts that belong to the same person based on their common emails.
2. Create a disjoint-set and an unordered map to store email to account index mapping.
3. Iterate through the list of accounts, for each account, iterate through its emails, and add them to the unordered map with the corresponding account index as the value.
4. Iterate through the unordered map and union accounts that share common emails using the disjoint-set.
5. Create a set for each group of accounts and add the emails of each account to the corresponding set.
6. Iterate through the disjoint-set and create the final merged accounts list by grouping emails for each account based on their root in the disjoint-set.

Complexity Analysis:
- Let n be the number of accounts and m be the average number of emails per account.
- The time complexity of this approach is O(n*m*α(n)), where α(n) is the inverse Ackermann function, which grows very slowly and is nearly constant.
- The space complexity is O(n*m) to store the emails and their corresponding account indices in the unordered map, and O(n*m) to store the merged accounts in the final result.
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
