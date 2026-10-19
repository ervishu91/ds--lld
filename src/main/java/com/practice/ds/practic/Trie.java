package com.practice.ds.practic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Trie {

    Node root ;

    static class Node{
        Node[] child;
        String word=null;

        Node(){
            child = new Node[26];

        }
    }

    public static void main(String[] args) {
        Trie trie= new Trie();
        trie.root= new Node();

        trie.insert("car");
        trie.insert("card");
        trie.insert("care");
        trie.insert("cat");
        trie.insert("dog");

        System.out.println("match prefix words are:"+ Arrays.toString(trie.autoComplete("ca")));
        System.out.println(trie.search("car") );
        System.out.println(trie.search("cam") );

    }

    private String[] autoComplete(String prefix) {
        char[] wordArray = prefix.toCharArray();
        Node temp =root;
        for(char ch : wordArray){
            if(temp.child[ch-'a']==null)
                return new String[0];
            temp = temp.child[ch-'a'];
        }
        List<String> result= new ArrayList<>();
        findAllString(temp, result);

        return result.toArray(new String[result.size()]);
    }

    private void findAllString(Node temp, List<String> result) {
        if(temp.word!=null){
            result.add(temp.word);
        }
        for(Node node : temp.child){
            if(node !=null)
                findAllString(node, result);
        }
    }

    private String search(String word) {
        char[] wordArray = word.toCharArray();
        Node temp =root;
        for(char ch : wordArray){
            if(temp.child[ch-'a']==null)
                return null;
            temp = temp.child[ch-'a'];


        }
        return temp.word;
    }

    private void insert(String word) {
        char[] wordArray = word.toCharArray();
        Node temp =root;
        for(char ch : wordArray){
            if(temp.child[ch-'a']==null)
                temp.child[ch-'a']= new Node();
            temp = temp.child[ch-'a'];
        }
        temp.word = word;
    }
}
