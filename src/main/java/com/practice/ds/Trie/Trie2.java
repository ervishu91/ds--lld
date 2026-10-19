package com.practice.ds.Trie;


public class Trie2 {

    static Node root;

    static class Node{
        Node[] child;
        String word;
        int freq=0;

        Node(){
            child = new Node[26];
        }

    }

    public static void main(String[] args) {
         root = new Node();

        insertWord("apple");
        insertWord("apple");
        insertWord("app");
        insertWord("application");

        System.out.println(countWordsEqualTo("apple"));       // 2
        System.out.println(countWordsStartingWith("app"));    // 4

        erase("apple");

        System.out.println(countWordsEqualTo("apple"));       // 1
        System.out.println(countWordsStartingWith("app"));    // 3

    }

    static void insertWord(String word ){
        Node temp = root;
        for(char ch : word.toCharArray()){
            if(temp.child[ch-'a']==null){
                temp.child[ch-'a'] = new Node();
            }
            temp = temp.child[ch-'a'];
        }
        temp.word=word;
        temp.freq+=1;

    }
    static void erase(String word){
        Node temp = root;
        for(char ch : word.toCharArray()){
            if(temp.child[ch-'a']==null){
                return ;
            }
            temp = temp.child[ch-'a'];
        }
        if(temp.word!=null) {
            temp.freq -= 1;
            if(temp.freq==0)
                temp.word = null;

        }


    }
    static int countWordsEqualTo(String word){
        Node temp = root;
        for(char ch : word.toCharArray()){
            if(temp.child[ch-'a']==null){
                return 0;
            }
            temp = temp.child[ch-'a'];
        }
        if(temp.word!=null) {

            return temp.freq;
        }
        return 0;
    }
    static int countWordsStartingWith(String prefix){
        Node temp = root;
        int count=0;
        for(char ch : prefix.toCharArray()){
            if(temp.child[ch-'a']==null){
                return 0;
            }
            temp = temp.child[ch-'a'];
        }
        for(Node node : temp.child){
            count+=dfs(node);
        }
        if(temp.word!=null)
            return temp.freq+count;
        return count;
    }
    static int dfs(Node node){
        if (node == null)
            return 0;
        int count =0;

        for(Node child : node.child){
            count+=dfs(child);
        }
        if(node.word!=null)
            return node.freq+count;
        else
            return count;
    }




}
