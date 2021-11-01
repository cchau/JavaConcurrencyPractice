package com.leetcode;


import java.util.HashMap;
import java.util.Map;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


public class LinkedListUtil {
    public ListNode reverseList(ListNode head) {
        ListNode previous = null;
        ListNode current = head;

        while (current != null) {
            ListNode temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
        }

        return previous;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        LinkedListUtil util = new LinkedListUtil();
        ListNode currentL1 = l1;
        ListNode currentL2 = l2;


        int carry = 0;
        ListNode result = null;
        ListNode currentResult = null;
        while (currentL1 != null || currentL2 != null) {
            int l1Value = (currentL1 != null) ? currentL1.val : 0;
            int l2Value = (currentL2 != null) ? currentL2.val : 0;

            int sum = l1Value + l2Value + carry;
            carry = sum / 10;
            ListNode newDigit = new ListNode(sum % 10);
            if (currentResult == null) {
                result = newDigit;
                currentResult = result;
            } else {
                currentResult.next = newDigit;
                currentResult = currentResult.next;
            }

            currentL1 = (currentL1 != null) ? currentL1.next : null;
            currentL2 = (currentL2 != null) ? currentL2.next : null;
        }

        if (carry != 0) {
            currentResult.next = new ListNode(carry);
        }

        return result;


    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        LinkedListUtil util = new LinkedListUtil();
        ListNode currentL1 = util.reverseList(l1);
        ListNode currentL2 = util.reverseList(l2);

        int carry = 0;
        ListNode result = null;
        ListNode currentResult = null;
        while (currentL1 != null || currentL2 != null) {
            int l1Value = (currentL1 != null) ? currentL1.val : 0;
            int l2Value = (currentL2 != null) ? currentL2.val : 0;

            int sum = l1Value + l2Value + carry;
            carry = sum / 10;
            ListNode newDigit = new ListNode(sum % 10);
            if (currentResult == null) {
                result = newDigit;
                currentResult = result;
            } else {
                currentResult.next = newDigit;
                currentResult = currentResult.next;
            }

            currentL1 = (currentL1 != null) ? currentL1.next : null;
            currentL2 = (currentL2 != null) ? currentL2.next : null;
        }

        if (carry != 0) {
            currentResult.next = new ListNode(carry);
        }

        return util.reverseList(result);


    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode currentL1 = l1;
        ListNode currentL2 = l2;

        ListNode result = null;
        ListNode currentResult = null;

        while (currentL1 != null || currentL2 != null) {
            int l1Value = (currentL1 != null) ? currentL1.val : Integer.MAX_VALUE;
            int l2Value = (currentL2 != null) ? currentL2.val : Integer.MAX_VALUE;

            ListNode nodeToAdd = (l1Value < l2Value) ? currentL1 : currentL2;

            if (result == null) {
                result = nodeToAdd;
                currentResult = result;
            } else {
                currentResult.next = nodeToAdd;
                currentResult = currentResult.next;
            }

            if (nodeToAdd == currentL1)
                currentL1 = (currentL1 != null) ? currentL1.next : null;
            else
                currentL2 = (currentL2 != null) ? currentL2.next : null;
        }


        return result;
    }

    public boolean canMoveForward(ListNode[] lists){
        for (ListNode head : lists) {
            if (head != null){
                return true;
            }

        }
        return false;
    }

    public int findMinNode(ListNode[] lists){
        int minValue = Integer.MAX_VALUE;
        int minNodeIndex = 0;
        for (int i = 0; i < lists.length; i++) {
            if(lists[i] != null && lists[i].val <= minValue){
                minValue = lists[i].val;
                minNodeIndex = i;
            }
        }

        return  minNodeIndex;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = new ListNode(-1);

        ListNode[] currentListPointers = new ListNode[lists.length];
        for (int i = 0; i < currentListPointers.length; i++) {
            currentListPointers[i] = lists[i];
        }

        ListNode currentResult = result;
        while (canMoveForward(currentListPointers)) {
            int minNodeIndex = findMinNode(currentListPointers);
            currentResult.next = new ListNode(currentListPointers[minNodeIndex].val);
            currentResult = currentResult.next;
            currentListPointers[minNodeIndex] = currentListPointers[minNodeIndex].next;
        }


        return result.next;

    }



    public boolean hasCycle(ListNode head) {

        ListNode current = head;
        Map<ListNode, Integer> nodeValues = new HashMap<>();
        int pos = -1;
        boolean hasCycle = false;
        while (current != null) {
            pos++;
            if (nodeValues.containsKey(current)) {
                hasCycle = true;
                break;
            }
            nodeValues.put(current, pos);
            current = current.next;
        }

        return hasCycle;

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1,
                new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

        LinkedListUtil util = new LinkedListUtil();
        ListNode reversedHead = util.reverseList(head);

        ListNode posNode = new ListNode(2);
        ListNode loop = new ListNode(3);
        loop.next = posNode;
        posNode.next = new ListNode(0, new ListNode(-4, posNode));

        util.hasCycle(new ListNode(1));
        util.hasCycle(loop);

        ListNode l1 = new ListNode(2,
                new ListNode(4, new ListNode(3)));

        ListNode l2 = new ListNode(5,
                new ListNode(6, new ListNode(4)));

        util.addTwoNumbers(l1, l2);

        ListNode l1N = new ListNode(7,
                new ListNode(2, new ListNode(4, new ListNode(3))));

        ListNode l2N = new ListNode(5,
                new ListNode(6, new ListNode(4)));

        util.addTwoNumbers2(l1N, l2N);


        ListNode l1C = new ListNode(1,
                new ListNode(2, new ListNode(4)));

        ListNode l2C = new ListNode(1,
                new ListNode(3, new ListNode(4)));

        util.mergeTwoLists(l1C, l2C);

        ListNode l1K = new ListNode(1,
                new ListNode(4, new ListNode(5)));

        ListNode l2K = new ListNode(1,
                new ListNode(3, new ListNode(4)));

        ListNode l3K = new ListNode(2,
                new ListNode(6));

        ListNode[] lists = new ListNode[]{l1K, l2K, l3K};
        util.mergeKLists(lists);


        System.out.println("End!");


    }
}
