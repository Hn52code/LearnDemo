package com.zhn.demo.basic.collection.list;

import java.util.*;

public class LinkedListTest {

    public static void main(String[] args) {

        System.out.println(4 >> 1);

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");
        linkedList.add("E");
        linkedList.add("F");
        linkedList.add("G");
        linkedList.add("H");
        linkedList.add("I");
        linkedList.add("J");
        linkedList.add("K");
        linkedList.remove(4);
        linkedList.add(4, "4");
        linkedList.remove();
        linkedList.indexOf("A");
        linkedList.contains("B");
        linkedList.get(4);
        Iterator<String> iterator = linkedList.iterator();

        linkedList.clear();

    }

}
