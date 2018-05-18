package in.explore.core.java8.newfeatures;

import java.util.Optional;
import java.util.function.Predicate;

public class TestingOptional {

	public static String getInsuranceCompanyName(Optional<Person> person) {
		return person.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getCompanyName)
				.orElse("No Insurace Company Found");
	}

	// Rejecting Certain values using filter
	public static String getFilteredInsuranceCompanyName(Optional<Person> person) {

		Predicate<Person> personPredicate = p -> p.getName().equalsIgnoreCase("Prashant Kumar Singh");
		personPredicate = personPredicate.negate();

		return null;
	}

	public static void main(String args[]) {
		Person person = new Person();
		person.setName("Prashant Kumar Singh");

		Car car = new Car();
		car.setModel("VDI");
		car.setCompany("Maruti Suzuki");

		Insurance insurance = new Insurance();
		insurance.setCompanyName(" Bajaj Allianz Coropration");

		car.setInsurance(Optional.ofNullable(insurance));
		person.setCar(Optional.ofNullable(car));

		String insuranceName = TestingOptional.getInsuranceCompanyName(Optional.of(person));
		System.out.println("Insurance Company Name is " + insuranceName);
	}

}
