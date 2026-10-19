
package com.practice.ds.practic;

import java.util.ArrayList;
import java.util.List;

public class ROuting {

    static class Node {
        Node[] octet = new Node[256];
        List<Route> routes = new ArrayList<>();
    }

    static class Route {
        int partialBits;
        int mask;
        int networkOctet;
        String route;
        int nwBits;

        Route(int partialBits, int mask, int networkOctet,
              String route, int nwBits) {
            this.partialBits = partialBits;
            this.mask = mask;
            this.networkOctet = networkOctet;
            this.route = route;
            this.nwBits = nwBits;
        }
    }

    static Node root;

    public static void main(String[] args) {
        root = new Node();

        String[] interfaces = {
                "192.168.0.0/16",
                "192.168.1.0/24",
                "192.168.1.128/25",
                "10.0.0.0/8"
        };

        System.out.println(routing(interfaces, "192.168.1.200"));
    }

    private static String routing(String[] interfaces, String ip) {
        root = new Node(); // Reset trie for each routing table
        constructTrie(interfaces);
        return findInterface(ip);
    }

    private static void constructTrie(String[] interfaces) {
        for (String interf : interfaces) {
            String[] parts = interf.split("/");
            int prefix = Integer.parseInt(parts[1]);
            String[] octets = parts[0].split("\\.");

            Node temp = root;

            int completeBytes = prefix / 8;
            int partialBits = prefix % 8;

            // Traverse only the fully matched octets.
            for (int i = 0; i < completeBytes; i++) {
                int value = Integer.parseInt(octets[i]);

                if (temp.octet[value] == null) {
                    temp.octet[value] = new Node();
                }

                temp = temp.octet[value];
            }

            // Store the route at the current node.
            // For /0, this is the root.
            // For /25, this is the node after 3 full octets.
            int networkOctet = 0;
            int mask = 0;

            if (partialBits > 0) {
                networkOctet =
                        Integer.parseInt(octets[completeBytes]);
                mask = (0xFF << (8 - partialBits)) & 0xFF;
            }

            temp.routes.add(new Route(
                    partialBits, mask, networkOctet, interf, prefix
            ));
        }
    }

    private static String findInterface(String ip) {
        String[] octets = ip.split("\\.");

        Node temp = root;
        String bestRoute = "0.0.0.0/0";
        int bestPrefix = -1;

        // Check /0 routes stored at the root.
        for (Route route : temp.routes) {
            if (route.nwBits > bestPrefix) {
                bestPrefix = route.nwBits;
                bestRoute = route.route;
            }
        }

        for (int i = 0; i < 4; i++) {
            int ipOctet = Integer.parseInt(octets[i]);

            if (temp.octet[ipOctet] == null) {
                break;
            }

            temp = temp.octet[ipOctet];

            for (Route route : temp.routes) {
                boolean match =
                        route.partialBits == 0 ||
                                (ipOctet & route.mask) ==
                                        (route.networkOctet & route.mask);

                if (match && route.nwBits > bestPrefix) {
                    bestPrefix = route.nwBits;
                    bestRoute = route.route;
                }
            }
        }

        return bestRoute;
    }
}
