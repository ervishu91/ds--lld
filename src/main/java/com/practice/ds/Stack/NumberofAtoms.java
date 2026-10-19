package com.practice.ds.Stack;

import java.util.*;

public class NumberofAtoms {
    public static void main(String[] args) {
        System.out.println("no of atoms are "+ numberofAtoms("K4(ON(SO3)12)2"));
        System.out.println("no of atoms are "+ numberofAtoms("H2O"));
        System.out.println("no of atoms are "+ numberofAtoms("Mg(OH)2"));
        System.out.println("no of atoms are "+ numberofAtoms("K4(ON(SO3)2)2"));
        System.out.println("no of atoms are "+ numberofAtoms("K4(ON(SO3)12)2"));
        System.out.println("no of atoms are "+ numberofAtoms("Na"));
    }

    private static String numberofAtoms(String formula) {
        System.out.println("formula::"+formula);
        Map<String,Integer> atomMap = new HashMap<>();
        Stack<Map<String,Integer>> atomStack = new Stack<>();
        atomStack.push(atomMap);
        int index=0,n= formula.length();


        while(index<formula.length()){
            if(Character.isUpperCase(formula.charAt(index))){
                String chem = ""+formula.charAt(index++);
                if(index < n && Character.isLowerCase(formula.charAt(index))){
                    chem+=formula.charAt(index++);
                }
                //int num = findDigitFrom(index,formula);
                String str="";
                while(index < n && Character.isDigit(formula.charAt(index))){
                    str+=formula.charAt(index++);
                }
                int num =  str.isEmpty()?1:Integer.parseInt(str);
                atomStack.peek().put(chem,atomStack.peek().getOrDefault(chem,0)+num);

            }else if(formula.charAt(index)=='('){
                atomStack.push(new HashMap<String,Integer>());
                index++;

            }else if(index < n && formula.charAt(index)==')'){
                index++;
                String str="";
                while(index < n && Character.isDigit(formula.charAt(index))){
                    str+=formula.charAt(index++);
                }
                int num =  str.isEmpty()?1:Integer.parseInt(str);
                Map<String,Integer> localMap = atomStack.pop();
                for(String key : localMap.keySet()){
                    atomStack.peek().put(key,atomStack.peek().getOrDefault(key,0)+(localMap.get(key)*num));
                }

            }
        }

        Map<String,Integer> global = atomStack.pop();
        List<String> chelList = new ArrayList<>(global.keySet());
        Collections.sort(chelList);
        StringBuilder ans= new StringBuilder();
        for(String key : chelList){
            ans.append(" ").append(key).append(global.get(key));
        }
        return ans.toString();
    }

//    private static int findDigitFrom(int index, String formula) {
//
//    }
}
