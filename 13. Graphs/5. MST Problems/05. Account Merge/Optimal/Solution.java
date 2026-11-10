/*
QUESTION:
Given a list of `accounts` where each element `accounts[i]` is a list of strings,
the first element `accounts[i][0]` is a name, and the rest are emails.
Merge accounts belonging to the same person.

APPROACH:
- Map each unique email to the account index `i` that owns it.
- If an email has been seen in account `j`, union `i` and `j` in DSU.
- Group all emails by their root account index.
- Sort emails and prepend account name.

TIME COMPLEXITY: O(N * K log(N * K)) where N is accounts and K is emails.
SPACE COMPLEXITY: O(N * K).
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    private static class DisjointSet {
        int[] parent;
        DisjointSet(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU != rootV) parent[rootU] = rootV;
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);
        Map<String, Integer> emailToAcc = new HashMap<>();

        for (int i = 0; i < n; i++) {
            List<String> acc = accounts.get(i);
            for (int j = 1; j < acc.size(); j++) {
                String email = acc.get(j);
                if (!emailToAcc.containsKey(email)) {
                    emailToAcc.put(email, i);
                } else {
                    ds.union(i, emailToAcc.get(email));
                }
            }
        }

        Map<Integer, List<String>> merged = new HashMap<>();
        for (String email : emailToAcc.keySet()) {
            int root = ds.find(emailToAcc.get(email));
            merged.computeIfAbsent(root, k -> new ArrayList<>()).add(email);
        }

        List<List<String>> result = new ArrayList<>();
        for (int root : merged.keySet()) {
            List<String> emails = merged.get(root);
            Collections.sort(emails);
            List<String> entry = new ArrayList<>();
            entry.add(accounts.get(root).get(0));
            entry.addAll(emails);
            result.add(entry);
        }
        return result;
    }
}
