package in.explore.core.java8.streams;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic example of using stream API.
 * 
 * @author prashantsingh
 *
 */
public class BasicStreamExample1 {

	public static void main(String args[]) { 

		// Filtering content from the list

		List<Integer> list = createList();
		// Dumping all even number from the stream to the console
		System.out.println(" Even Numbers from the stream");
		list.stream().filter(i -> i % 2 == 0).forEach(System.out::println);

		System.out.println(" Double of all numbers");
		// Double every number and print on the console
		list.stream().map(i -> i * 2).forEachOrdered((s) -> System.out.println(s));

	}

	/**
	 * Utility Method to create a list of pre-populated Integers
	 * 
	 * @author prasingh26
	 * @return List<Integer> list of pre-populated Integers
	 */
	private static List<Integer> createList() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 20; i++) {
			list.add(i);
		}
		return list;
	}

}
