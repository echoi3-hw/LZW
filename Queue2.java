import java.util.*;
import java.io.*;
import java.util.LinkedList;

public class Queue2 {
	private static LinkedList<Integer> list = new LinkedList<Integer>();

	public Queue2 (LinkedList<Integer> list2) 
	{
		list = list2;
	}

	public static void pushToBack(int recent) 
	{
		list.add(list.remove(list.indexOf(recent)));
	}
	
	public static void add (int current) {
		list.add(current);
	}
	
	public static void remove () {
		list.remove();
	}
	
}
