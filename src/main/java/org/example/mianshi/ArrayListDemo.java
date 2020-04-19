package org.example.mianshi;

import javax.swing.event.TreeSelectionEvent;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayListDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> list1 = Collections.synchronizedList(new ArrayList<>());
        List<String> list2 = new CopyOnWriteArrayList<>();
        List<String> list3 = new LinkedList<>();

        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(100);
        treeSet.add(15);
        treeSet.add(5);
        treeSet.add(7);
        System.out.println(treeSet);

        LinkedHashMap linkedHashMap = new LinkedHashMap();



    }
}
