/*
 Test for the LinkedList class from CS136 Lab 5
 (c) 2016 Bill Jannen
*/

import structure5.*;
import java.util.*;

public class TestLinkedList {

    // a simple helper method to display a list
    // pre: list != null
    public static <E> void compareList(String expected, LinkedList<E> list) {
	System.out.println("Expected: <LinkedList: " + expected + ">");
	System.out.println("Received: " + list.toString());
    }

    public static void main(String s[]) {
	LinkedList<Integer> list = new LinkedList<Integer>();

	for (int i = 5; i >= 0; i--)
	    list.addFirst(new Integer(i));
	compareList("0 1 2 3 4 5", list);

	list.clear();
	//This next line breaks in the default code because
	// of the edge conditions that our Lab 5 solves.
	//When you have finished implementing your code,
	// you can uncomment this line and verify that it works.
	compareList("", list);

	for (int i = 0; i <= 5; i++)
	    list.addLast(new Integer(i));
	compareList("0 1 2 3 4 5", list);

	for (int i = 0; i <= 5; i++)
	    list.add(2*i, new Integer(i));
	compareList("0 0 1 1 2 2 3 3 4 4 5 5", list);

	for (int i = 0; i <= 5; i++) {
	    list.remove(i);
	}
	compareList("0 1 2 3 4 5", list);

	for (int i = 0; i <= 5; i++) {
	    list.set(i, 0);
	}
	compareList("0 0 0 0 0 0", list);

	list.set(0, 1);
	list.set(list.size()-1, 1);
	compareList("1 0 0 0 0 1", list);

	System.out.println("Expected: 0");
	System.out.println("Received: " + list.indexOf(1));
	System.out.println("Expected: 5");
	System.out.println("Received: " + list.lastIndexOf(1));
    }
}
