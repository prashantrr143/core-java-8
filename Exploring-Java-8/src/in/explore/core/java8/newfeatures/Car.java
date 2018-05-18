package in.explore.core.java8.newfeatures;

import java.util.Optional;

public class Car {

	private String model;
	private String company;

	/**
	 * Modeling an Insurance as Optional to represent that its a possibility
	 * that a car may not have insurance
	 * 
	 */
	private Optional<Insurance> insurance;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Optional<Insurance> getInsurance() {
		return insurance;
	}

	public void setInsurance(Optional<Insurance> insurance) {
		this.insurance = insurance;
	}

}
