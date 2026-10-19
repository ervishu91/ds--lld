package com.practice.ds.UnionFind;

import java.util.*;

public class AccountsMerge {

    private static int[] parent;

    public static void main(String[] args) {

        System.out.println(
                Arrays.deepToString(
                        accountsMerge(new String[][]{
                                {"John", "johnsmith@mail.com", "john00@mail.com"},
                                {"John", "johnnybravo@mail.com"},
                                {"John", "johnsmith@mail.com", "john_newyork@mail.com"},
                                {"Mary", "mary@mail.com"}
                        })
                )
        );
    }

    private static String[][] accountsMerge(String[][] accounts) {

        Map<String, Integer> accountMap = new HashMap<>();

        int n = accounts.length;

        parent = new int[n];

        // Initialize DSU
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // Find accounts that should be merged
        for (int k = 0; k < n; k++) {

            String[] account = accounts[k];

            for (int i = 1; i < account.length; i++) {

                String email = account[i];

                if (accountMap.containsKey(email)) {
                    union(k, accountMap.get(email));
                } else {
                    accountMap.put(email, k);
                }
            }
        }

        // root -> emails
        Map<Integer, Set<String>> respMap = new HashMap<>();

        for (int k = 0; k < n; k++) {

            int root = parent[k];

            Set<String> emailSet =
                    respMap.computeIfAbsent(root, x -> new HashSet<>());

            String[] account = accounts[k];

            for (int i = 1; i < account.length; i++) {
                emailSet.add(account[i]);
            }
        }

        // Build result
        List<List<String>> respList = new ArrayList<>();

        for (int root : respMap.keySet()) {

            List<String> emails =
                    new ArrayList<>(respMap.get(root));

            Collections.sort(emails);

            List<String> list = new ArrayList<>();
            list.add(accounts[root][0]);
            list.addAll(emails);

            respList.add(list);
        }

        return respList.stream()
                .map(list -> list.toArray(new String[0]))
                .toArray(String[][]::new);
    }

    private static void union(int u, int v) {

        int pu = find(u);
        int pv = find(v);

        if (pu == pv)
            return;

        parent[pv] = pu;
    }

    // Path compression
    private static int find(int k) {

        if (parent[k] != k) {
            parent[k] = find(parent[k]);
        }

        return parent[k];
    }
}