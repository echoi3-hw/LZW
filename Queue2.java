import java.util.*;
import java.io.*;
import java.util.LinkedList;

public class Queue2 {
	private static int maxSize;
	private static LinkedList<Integer> list = new LinkedList<Integer>();

	public Queue2 (LinkedList<Integer> list2) 
	{
		list = list2;
	}


	public static void pushToBack(int pushed) 
	{
		list.addLast(pushed);
	}
}
