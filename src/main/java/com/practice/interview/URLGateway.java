package com.practice.interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class URLGateway {

    static Node root;

    static class Node {

    Map<String,Node> child;
    boolean allowed;
Node(){
    child = new HashMap<>();
}

    }public static void main(String[] args) {

        root= new Node();
        String[] allowed= {"/public/*","/api/health","/api/v1/status"};
        String[] notAllowed= {"/api/*","/admin/*"};
        String[] inComingUrl = {"/api/users","/api/v1/status","/admin/users"};
        registerRule(allowed, true);
        registerRule(notAllowed, false);
        System.out.println(Arrays.toString(matchRule(inComingUrl)));
    }

    private static boolean[] matchRule(String[] inComingUrl) {
        boolean[] allowed =  new boolean[inComingUrl.length];
        Node temp =root;
        for(int i=0;i<inComingUrl.length;i++){
            allowed[i] = isURLAllowed(inComingUrl[i].split("/"),1,temp);
        }

        return allowed;
    }

    private static boolean isURLAllowed(String[] pathSeg,int index,Node temp) {
        if(index>=pathSeg.length)
            return temp.allowed;

        String seg = pathSeg[index];
        if(temp.child.get(seg)!=null){
            if(isURLAllowed(pathSeg,index+1,temp.child.get(seg)))
                return true;
        }
        if(temp.child.get("*")!=null){
            if(isURLAllowed(pathSeg,index+1,temp.child.get("*")))
                return true;
        }

return false;

    }

    private static void registerRule(String[] paths, boolean allow) {
        for(String path : paths){
            Node temp =root;
            String[] pathSeg = path.split("/");
            for(int i=1;i<pathSeg.length;i++){
                String seg = pathSeg[i].startsWith("{")?"*":pathSeg[i];
                temp.child.computeIfAbsent(seg, k -> new Node());
                temp = temp.child.get(seg);
            }
            temp.allowed = allow;
        }
    }
}
