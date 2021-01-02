package com.matrix;

import java.util.ArrayList;

/**
 * @author boyuangong created on 9/22/19 at 19:59
 */
public class MyLinkedList {

    private Node head;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        this.head = null;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        Node cur_head = head;
        if(index < 0) {
            return -1;
        }
        while(index > 0 && cur_head != null) {
            index --;
            cur_head = cur_head.next;
        }
        if(index > 0) {
            return -1;
        }

        if(cur_head == null) {
            return -1;
        }

        return cur_head.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        if(head == null) {
            this.head = new Node(val);
        } else {
            Node cur_head = this.head;
            this.head = new Node(val);
            this.head.next = cur_head;
        }
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node head_prev = head;
        Node head_cur = head;
        while(head_cur != null) {
            head_prev = head_cur;
            head_cur = head_cur.next;
        }
        head_prev.next = new Node(val);
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index == 0) {
            addAtHead(val);
            return;
        }
        if(head == null && index > 0) {
            return;
        }
        if(index < 0) {
            return;
        }

        Node cur_head = head;
        Node prev_head = cur_head;
        while(index > 0 && cur_head != null) {
            prev_head = cur_head;
            cur_head = cur_head.next;
            index --;
        }

        if(index > 0) {
            // index greater than length;
            return;
        }

        if(cur_head == null) {
            prev_head.next = new Node(val);
            return;
        }

        // add before cur_head;
        prev_head.next = new Node(val);
        prev_head.next.next = cur_head;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(this.head == null) {
            return;
        }
        if(index == 0) {
            head = head.next;
        }
        if(index < 0) {
            return;
        }

        Node cur_head = head;
        Node prev_head = cur_head;
        while(index > 0 && cur_head != null) {
            index --;
            prev_head = cur_head;
            cur_head = cur_head.next;
        }
        if(index > 0 || cur_head == null) {
            return;
        }
        prev_head.next = cur_head.next;
    }

    public String toString() {
        ArrayList<Integer> list = new ArrayList<>();
        Node cur_node = head;
        while(cur_node != null) {
            list.add(cur_node.val);
            cur_node = cur_node.next;
        }
        return list.toString();
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
//        list.addAtHead(5);
//        list.addAtHead(3);
        list.addAtIndex(-1, 0);
        System.out.println(list.toString());
        System.out.println(list.get(0));
//        list.addAtTail(3);
        System.out.println(list.toString());
//        list.addAtIndex(1, 2);
        System.out.println(list.toString());
        list.deleteAtIndex(2);
        System.out.println(list.toString());
    }

    public class Node {
        int val;
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
