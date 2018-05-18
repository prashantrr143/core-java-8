package in.explore.core.java8.generics;

public class SoftwareEngineer extends Employee {

	public SoftwareEngineer(String fName, String lName, int id) {
		super(fName, lName, id);
		// TODO Auto-generated constructor stub
	}
	public void calledBySoftwareEngineer(){
		System.out.println("Called by Software Engineer " + this);
	}
	

}
