package com.practice.interview;


public class IpSubnet {

    private static Node root;
    static class Node{
        Node[] child ;
        String route;
        Integer prefix;
        Node(){
            child= new Node[2];

        }
    }

    public static void main(String[] args) {
         root = new Node();
        String ip = "10.1.2.5";
        String[] subnet = {"10.0.0.0/8","10.1.0.0/16","10.1.2.0/24","192.168.0.0/16"};
        createBinarytrie(subnet);
        System.out.println("route="+findMatch("10.1.2.5"));
        System.out.println("route="+findMatch("10.1.9.5"));
        System.out.println("route="+findMatch("10.2.1.1"));
        System.out.println("route="+findMatch("172.16.0.1"));
    }

    private static String findMatch(String ip){
        int ipInt = ipToInt(ip);
        int prefix=0;
        String route="";
        Node temp =root;
        for(int i=0;i<32;i++){
            int bit = (ipInt >>> (31-i)) & 1;
            if( temp.child[bit] ==null)
                return  route;
            temp = temp.child[bit];
            if(temp.prefix!=null && prefix<temp.prefix){
                prefix= temp.prefix;
                route= temp.route+"/"+prefix;
            }
        }
        return route;
    }

    private static void createBinarytrie(String[] subnet) {

        for(String route : subnet){
            String[] parts= route.split("/");
            int ip = ipToInt(parts[0]);
            int prefix = Integer.parseInt(parts[1]);
            Node temp=root;
            for(int i=0;i<prefix;i++){
                int bits = (ip >>> (31 -i)) & 1;

                if( temp.child[bits] ==null)
                    temp.child[bits]= new Node();
                temp = temp.child[bits];
            }
            temp.route = parts[0];
            temp.prefix= prefix;
        }

    }
    private static int ipToInt(String ip){
        String[] octet = ip.split("\\.");
        int result=0;
        for(String oct :octet){
            result = (result<< 8 ) | Integer.parseInt(oct);
        }
        return result;
    }


}
