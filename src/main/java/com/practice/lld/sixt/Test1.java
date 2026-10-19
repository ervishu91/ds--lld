package com.practice.lld.sixt;

//Given an integer array nums and an integer k, return the k most frequent elements within the array.
//The test cases are generated such that the answer is always unique.
//You may return the output in any order.
//        Example 1:
//Input: nums = [1,2,2,3,3,3],N k = 2 Output: [2,3]
//Example 2:
//Input: nums = [7,7], k = 1Output: [7]
//Constraints:
//        1 <= nums.length <= 10^4.
//        -1000 <= nums[i] <= 1000
//        1 <= k <= number of distinct elements in nums.



//Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
//        k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
//        You may not alter the values in the list's nodes, only nodes themselves may be changed.
//
//        Example 1:
//        Input: head = [1,2,3,4,5], k = 2  Output: [2,1,4,3,5]
//        Example 2:
//        Input: head = [1,2,3,4,5], k = 3 Output: [3,2,1,4,5]
//
//        Constraints:
//        The number of nodes in the list is n.
//        1 <= k <= n <= 5000
//        0 <= Node.val <= 1000
//



import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Test1 {


    static  class Node{
        int val;
        Node next=null;
        Node(int val){
            this.val=val;
        }
    }

    public static void main(String[] args) {
        //System.out.println("max freq ele are ::"+ Arrays.toString(maxFreqEele(new int[]{7,7},1)));

        Node root = new Node(1);
        root.next = new Node(2);
        root.next.next= new Node(3);
        root.next.next.next= new Node(4);
        root.next.next.next.next= new Node(5);
        root.next.next.next.next.next= new Node(6);
        print(reverseKLis(root,3));
    }

    private static int[] maxFreqEele(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer,Integer> freqmap = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)-> freqmap.get(a)- freqmap.get(b));
        for(int num : nums){
            freqmap.put(num, freqmap.getOrDefault(num,0)+1);
        }
        for(int num : freqmap.keySet()){
            minHeap.offer(num);
            if(minHeap.size()>k){
                minHeap.poll();
            }
        }
        int i=0;
        while(!minHeap.isEmpty()){
            res[i++]=minHeap.poll();
        }
        return res;

    }
    //
    //
    // h-1-2-3-4-5-6,k=3
    //h-3-2-1-4

    //h-1-2-3-null
    //h-3-2-1

    private static void print(Node root){
        while(root!=null){
            System.out.println(root.val+",");
            root = root.next;
        }
    }
    private static Node reverseKLis(Node root, int k) {

        if (root == null || k <= 1) {
            return root;
        }

        Node head = new Node(-1);
        head.next = root;

        Node prevGroup = head;

        while (true) {

            // Find the kth node
            Node temp = prevGroup;

            for (int i = 0; i < k; i++) {
                temp = temp.next;

                if (temp == null) {
                    return head.next;
                }
            }

            // Node after current group
            Node nextGroup = temp.next;

            // First node becomes the tail after reversal
            Node groupTail = prevGroup.next;

            // Reverse current group
            Node prev = null;
            Node present = prevGroup.next;

            while (present != nextGroup) {
                Node next = present.next;

                present.next = prev;

                prev = present;
                present = next;
            }
            groupTail.next = nextGroup;
            // Connect previous group to new head
            prevGroup.next = prev;

            // Move to next group
            prevGroup = groupTail;
        }
    }


}
