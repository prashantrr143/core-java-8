package in.explore.core.java8.generics;

public class MechanicalEngineer extends Employee {

	public MechanicalEngineer(String fName, String lName, int id) {
		super(fName, lName, id);
		// TODO Auto-generated constructor stub
	}
	
	public void calledByMechanicalEngineer(){
		System.out.println("Called by Mechanical Engineer " + this);
	}

}
