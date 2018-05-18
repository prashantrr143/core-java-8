package in.explore.core.java8.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class GenericMethods {
	
	
	

	/**
	 * Find the max element from the collection passed as parameter
	 * 
	 * @param coll
	 * @return
	 */
	public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) {
		Iterator<? extends T> iterator = coll.iterator();
		
		T max = iterator.next();
		while (iterator.hasNext()) {
			T next = iterator.next();
			if (next.compareTo(max) > 0) {
				max = next;
			}
		}
		return max;
	}

	public static <T extends Employee> void engineer(T employee) {
		if (employee instanceof SoftwareEngineer) {
			((SoftwareEngineer) employee).calledBySoftwareEngineer();
		} else if (employee instanceof MechanicalEngineer) {
			((MechanicalEngineer) employee).calledByMechanicalEngineer();
		}
	}

	// Main Method for Testing Purpose

	public static void main(String args[]) {

		Collection<Integer> integerColl = new ArrayList<Integer>();
		integerColl.add(12);
		integerColl.add(534);
		integerColl.add(134);
		integerColl.add(121);
		integerColl.add(1754);
		integerColl.add(164);
		integerColl.add(16);
		integerColl.add(176);
		integerColl.add(1353);

		int max = GenericMethods.max(integerColl);
		System.out.println("Maximum Element is  " + max);

		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new SoftwareEngineer("Prashant", "Singh", 123));
		employees.add(new SoftwareEngineer("Prashant", "Singh", 112));
		employees.add(new SoftwareEngineer("Prashant", "Singh", 187));
		employees.add(new SoftwareEngineer("Prashant", "Singh", 132));
		employees.add(new SoftwareEngineer("Prashant", "Singh", 109));
		employees.add(new SoftwareEngineer("Prashant", "Singh", 1234));

		Employee employee = GenericMethods.max(employees);
		System.out.println("Max Employee -> " + employee);
		
		
		Employee softwareEmp = new SoftwareEngineer("Prashant", "Singh", 213);
		Employee mechanicalEmp = new MechanicalEngineer("Prashant", "Singh", 213);
		
		GenericMethods.engineer(softwareEmp);
		GenericMethods.engineer(mechanicalEmp);
		

	}
}
