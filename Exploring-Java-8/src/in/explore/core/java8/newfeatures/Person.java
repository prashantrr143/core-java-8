package in.explore.core.java8.newfeatures;

import java.util.Optional;

public class Person {

	/**
	 * Name of the person
	 */
	private String name;

	/**
	 * Car is mapped Optional because its a possibility that a person may or may
	 * not have a car
	 */
	private Optional<Car> car;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Optional<Car> getCar() {
		return car;
	}

	public void setCar(Optional<Car> car) {
		this.car = car;
	}

}
