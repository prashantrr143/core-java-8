package in.explore.core.java8.streams.domain;

import java.util.Calendar;
import java.util.Date;

public class Person {

	private String fName;
	private String lName;
	private int age;
	private Date dateOfBirth;
	private long id;
	
	

	public Person(String fName, String lName, int age, Date dateOfBirth, long id) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.age = age;
		this.dateOfBirth = dateOfBirth;
		this.id = id;
	}

	public static Date getDate(int day, int month, int year) {
		Calendar calendar = Calendar.getInstance();
		if (day > 31)
			throw new IllegalArgumentException("Day value can not be greater than 31");
		if (month > 12)
			throw new IllegalArgumentException("Month value can not be greater than 12");
		if (year > 2018)
			throw new IllegalArgumentException("Year value can not be greater than 2018");
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		return calendar.getTime();

	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Person [fName=" + fName + ", lName=" + lName + ", age=" + age + ", dateOfBirth=" + dateOfBirth + ", id="
				+ id + "]";
	}

}
