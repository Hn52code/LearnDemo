package com.zhn.demo.basic.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        list.add(4,"E");

        list.remove(1);
        list.get(1);
        Iterator<String> iterator = list.iterator();
    }

}
