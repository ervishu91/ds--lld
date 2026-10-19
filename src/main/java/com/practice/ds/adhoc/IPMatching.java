package com.practice.ds.adhoc;

import com.practice.lld.SplitWise.Group;

import java.util.ArrayList;
import java.util.List;

public class IPMatching {


    static class Node {
        Node[] child;
        List<Route> routes;

        Node(){
            child = new Node[256];
            routes= new ArrayList<>();
        }
    }
    static class Route{
        int partialByte;
        int mask;
        String route;
        int prefix;

        public Route(int partialByte, int mask, String route, int prefix) {
            this.partialByte = partialByte;
            this.mask = mask;
            this.route = route;
            this.prefix = prefix;
        }
    }
    public static void main(String[] args) {
       System.out.println("route is ::"+ipMatching(new String[]{"10.0.0.0/8","10.1.0.0/16","10.1.2.0/24","10.1.2.128/25"},"10.1.2.150"));
        System.out.println("route is ::"+ipMatchingbyTrie(new String[]{"10.0.0.0/8","10.1.0.0/16","10.1.2.0/24","10.1.2.128/25"},"10.1.2.150"));

    }

    private static String ipMatching(String[] interfaces, String ip) {
        int nwBits=0;
        String nw="0.0.0.0/0";
        int ipInt= ipToInt(ip);
        for(String route : interfaces){
            String[] parts= route.split("/");
            int prefix = Integer.parseInt(parts[1]);
            int interfaceIp = ipToInt(parts[0]);
            int mask = -1 << (32-prefix);
            boolean match =false;
            if((ipInt & mask) == (interfaceIp & mask)){
                match=true;
            }
            if(match && nwBits<prefix){
                nwBits = prefix;
                nw=route;
            }

        }
      return nw;
    }


    private static int ipToInt(String part) {

        int ip =0;
        String[] octet = part.split("\\.");
        for(int i=0;i<4;i++){
            int ipOCtet = Integer.parseInt(octet[i]);
            ip = ip << 8 | ipOCtet;
        }
        return ip;
    }
    private static String ipMatchingbyTrie(String[] interfaces, String ip){
        Node root = populateTrie(interfaces);
        return match(ip,root);

    }
    static String match(String ip,Node root){
        String[] octet = ip.split("\\.");
        Node temp =root;
        int nwBits=0;
        String interf="";
        for(int i=0;i<4;i++){
            int ipOctet = Integer.parseInt(octet[i]);
            if(temp.child[ipOctet]==null){
                return interf;
            }
            temp = temp.child[ipOctet];
            boolean match =true;
            for(Route  route : temp.routes){
                if(route.mask>0){
                    if((ipOctet & route.mask) != (route.partialByte & route.mask)){
                        match =false;
                    }
                }
                if(match && nwBits<route.prefix){
                    nwBits= route.prefix;
                    interf= route.route;
                }
            }
        }
        return interf;
    }
    static Node populateTrie(String[] interfaces){
        Node root = new Node();
        Node temp = root;
        for(String route : interfaces){
            temp=root;
            String[] parts= route.split("/");
            int prefix = Integer.parseInt(parts[1]);
            String[] octet = parts[0].split("\\.");
            int completebyte = prefix/8;
            int partialByte = prefix%8;
            for(int i=0;i<completebyte;i++){
                int ipoctet = Integer.parseInt(octet[i]);
                if(temp.child[ipoctet]==null){
                    temp.child[ipoctet] = new Node();
                }
                temp = temp.child[ipoctet];
            }
            int mask =0;

            if(partialByte>0){
                int ipoctet = Integer.parseInt(octet[completebyte]);
                if(temp.child[ipoctet]==null){
                    temp.child[ipoctet] = new Node();
                }
                temp = temp.child[ipoctet];
                 mask = 0xFF << (8-partialByte) & 0xFF;

            }
            temp.routes.add(new Route(Integer.parseInt(octet[completebyte]),mask,route,prefix));
        }
        return root;
    }
}
