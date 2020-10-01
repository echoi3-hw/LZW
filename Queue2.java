import java.util.*;
import java.io.*;
import java.util.LinkedList;

public class Queue2 {
	private static int size;
	private static LinkedList<Integer> list = new LinkedList<Integer>();

	public Queue2 (LinkedList<Integer> list2) 
	{
		list = list2;
	}

	public static void pushToBack(int pushed) 
	{
		list.add(list.remove(list.indexOf(pushed)));
	}
	
	public static void add (int pushed) {
		list.addFirst(pushed);
	}
	
	public static void remove () {
		list.removeFirst();
	}
	
}
