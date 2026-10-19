//package com.practice.ds.LinkedList;
//
//public class ReverseNodesInkGroup {
//    public static void main(String[] args) {
//        MergeKlist.Node l1 = new MergeKlist.Node(1);
//        l1.next = new MergeKlist.Node(4);
//        l1.next.next = new MergeKlist.Node(7);
//
//        l1.next.next.next = new MergeKlist.Node(5);
//        l1.next.next.next.next= new MergeKlist.Node(8);
//
//        l1.next.next.next.next.next = new MergeKlist.Node(6);
//        l1.next.next.next.next.next.next = new MergeKlist.Node(9);
//        System.out.println("reverList is "+reverseNodesInkGroup(l1,3));
//    }
//
//    private static String reverseNodesInkGroup(MergeKlist.Node node, int k) {
//        int len=1;
//        MergeKlist.Node dummy = new MergeKlist.Node(-1);
//        dummy.next=node;
//        MergeKlist.Node prevGroup = dummy;
//        MergeKlist.Node temp = dummy;
//        while(true){
//            int i;
//            for( i=0;i<k;i++){
//                temp=temp.next;
//                if(temp==null)
//                    break;
//            }
//            if(i==k){
//                MergeKlist.Node nextGroup = temp.next;
//                temp.next=null;
//                prevGroup.next = reverseList(prevGroup.next);
//
//
//
//            }
//
//
//        }
//        //StringBuffer sb = new StringBuffer();;
//
//        MergeKlist.Node newHead = dummy.next;
//        while(newHead!=null){
//            sb.append(temp.val);
//            newHead=newHead.next;
//            sb.append(",");
//        }
//        return sb.toString();
//    }
//
//    private static MergeKlist.Node reverseList(MergeKlist.Node node) {
//        MergeKlist.Node current=node,prev=null,next=null;
//        while(current!=null){
//            next = current.next;
//            current.next=prev;
//            prev=current;
//            current=next;
//
//
//        }
//        return prev;
//    }
//}
