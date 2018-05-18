package in.explore.core.java8.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import in.explore.core.java8.streams.domain.Person;

/**
 * Class to check methods of Comparator
 * 
 * @author prashantsingh
 *
 */
public class TestComparator {

	/**
	 * Method to create a list of Persons
	 * 
	 * @return List of Persons
	 */
	public static List<Person> getListOfPersons() {
		// Creating a List of Persons
		List<Person> listOfPersons = new ArrayList<Person>();

		Person p1 = new Person("Prashant", "Kumar", 30, Person.getDate(21, 10, 1988), 21L);
		Person p2 = new Person("Mandaeep", "Singh", 20, Person.getDate(11, 11, 1987), 19L);
		Person p3 = new Person("Utkarsh", "Jain", 28, Person.getDate(15, 8, 1990), 23L);
		Person p4 = new Person("Sandeep", "Sharma", 35, Person.getDate(9, 8, 1984), 27L);
		Person p5 = new Person("Virat", "Kohli", 30, Person.getDate(21, 10, 1988), 21L);

		// Add all persons to the list
		listOfPersons.add(p1);
		listOfPersons.add(p2);
		listOfPersons.add(p3);
		listOfPersons.add(p4);
		listOfPersons.add(p5);

		// returning the list
		return listOfPersons;
	}

	public List<Person> sortListByFirstName(List<Person> list) {
		list.sort((p1, p2) -> p1.getfName().compareTo(p2.getfName()));
		return list;
	}

	public static void dumpListToConsole(List<Person> list) {
		list.stream().forEach(System.out::println);
	}

	// create comparator

	public void sortUsingId(List<Person> list) {
		// Creating a comparator using Id
		// Using static method comparing
		list.sort(Comparator.comparing(Person::getId));
		System.out.println(" After Sorting by ID \n");
		dumpListToConsole(list);
	}

	public static <T, U extends Comparable<? super U>> void compareUsing(Function<? super T, ? extends U> keyExtractor,
			List<T> list) {
		Comparator<T> comparator = (Comparator<T> & Serializable) (c1, c2) -> keyExtractor.apply(c1)
				.compareTo(keyExtractor.apply(c2));
		list.sort(comparator);
	}

	// Method to test the methods
	public static void main(String args[]) {
		List<Person> list = getListOfPersons();
		System.out.println("*** Before Sorting *** \n ");
		dumpListToConsole(list);
		TestComparator testComparator = new TestComparator();
		testComparator.sortListByFirstName(list);
		System.out.println("\n*** After Sorting *** ");
		dumpListToConsole(list);

		testComparator.sortUsingId(list);
		System.out.println("Sorting based on Date of Birth");
		TestComparator.compareUsing(Person::getDateOfBirth, list);
		dumpListToConsole(list);
	}

}
